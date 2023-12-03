package ua.foxminded.javaspring.consoleMenu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.StudentConfirmationHandler;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
import ua.foxminded.javaspring.consoleMenu.options.console.input.NewStudentData;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.options.print.ItemPrint;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.InputMismatchException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentDAO studentDAO;
    private StudentAtCourseDAO studentAtCourseDAO;
    private ItemID<Student> studentItemID;
    private StudentConfirmationHandler verifyValidStudent;
    private NewStudentData newStudentData;
    private ConsolePrinter consolePrinter;
    private ItemID<StudentAtCourse> studentAtCourseInputID;
    private ItemPrint<Course> printAllCourses;
    private ItemID<Course> courseItemID;


    public StudentServiceImpl(StudentDAO studentDAO, StudentAtCourseDAO studentAtCourseDAO, ItemID<Student> studentItemID,
                              StudentConfirmationHandler verifyValidStudent, NewStudentData newStudentData,
                              ConsolePrinter consolePrinter, ItemID<StudentAtCourse> studentAtCourseInputID,
                              ItemPrint<Course> printAllCourses, ItemID<Course> courseItemID) {
        this.studentDAO = studentDAO;
        this.studentAtCourseDAO = studentAtCourseDAO;
        this.studentItemID = studentItemID;
        this.verifyValidStudent = verifyValidStudent;
        this.newStudentData = newStudentData;
        this.consolePrinter = consolePrinter;
        this.studentAtCourseInputID = studentAtCourseInputID;
        this.printAllCourses = printAllCourses;
        this.courseItemID = courseItemID;
    }

    @Override
    public void addNewStudent(Student student) {
        System.out.println("Input data of a student.");
        try {
            studentDAO.addItem(newStudentData.get());
            System.out.println("Success, student had been added");
        } catch (InputMismatchException | InvalidIdException e) {
            LOGGER.info("Failed getting student data: {}", e.getMessage());
        }
    }

    @Override
    public void deleteStudent(Student studentID) {
        try {
            System.out.println("Enter the ID of the student you want to remove.");
            Student student = new Student(studentItemID.inputID());
            if (verifyValidStudent.verifyValidStudent(student)) {
                studentAtCourseDAO.removeStudentFromAllTheirCourses(student);
                studentDAO.removeStudent(student);
            }
        } catch (InvalidIdException e) {
            LOGGER.info("Error getting student ID: {}", e.getMessage());
        }
    }

    @Override
    public void addStudentToCourse(StudentAtCourse studentAtCourse) {
        try {
            System.out.println("Input student ID.");
            Student student = new Student(studentItemID.inputID());
            if (verifyValidStudent.verifyValidStudent(student)) {
                System.out.println("Input course ID, choose from list.");
                printAllCourses.printAllAvailableItems();
                studentAtCourseDAO.addItem(new StudentAtCourse(student, new Course(courseItemID.inputID())));
            }
        } catch (InvalidIdException | InputMismatchException e) {
            LOGGER.info("Error adding student to course: {}", e.getMessage());
        }
    }

    @Override
    public void removeStudentFromCourse(StudentAtCourse studentAtCourse) {
        try {
            System.out.println("Input the ID of student which should be remove from course. Then press enter.");
            Student student = new Student(studentItemID.inputID());
            List<StudentAtCourse> allStudentCourses = studentDAO.studentCourses(student);

            if (!CollectionUtils.isEmpty(allStudentCourses)) {
                System.out.println("Choose enrollment ID from the list to wish remove and press enter.");
                consolePrinter.viewAllCoursesOfStudent(allStudentCourses);
                Long receivedID = studentAtCourseInputID.inputID();

                if (allStudentCourses.stream().anyMatch(c -> c.getEnrollmentID().equals(receivedID))) {
                    studentAtCourseDAO.removeStudentFromCourse(new StudentAtCourse(receivedID));
                }
            } else {
                Student studentWithoutCourses = studentDAO.getByItemID(student).get();
                System.out.printf("The student: %s %s have not relate to any course.\n", studentWithoutCourses.getFirstName(), studentWithoutCourses.getLastName());
            }
        } catch (InvalidIdException e) {
            LOGGER.info("Error getting ID: {}", e.getMessage());
        }
    }
}
