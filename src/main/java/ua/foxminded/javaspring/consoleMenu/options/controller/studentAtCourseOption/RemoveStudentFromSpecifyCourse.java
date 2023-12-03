package ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveStudentFromSpecifyCourse {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveStudentFromSpecifyCourse.class);

    private ItemID<StudentAtCourse> studentAtCourseInputID;
    private ItemID<Student> studentInputID;
    private StudentService studentService;

    @Autowired
    public RemoveStudentFromSpecifyCourse(ItemID<StudentAtCourse> studentAtCourseInputID, ItemID<Student> studentInputID, StudentService studentService) {
        this.studentAtCourseInputID = studentAtCourseInputID;
        this.studentInputID = studentInputID;
        this.studentService = studentService;
    }

    public void removeByEnrollmentID() {
        try {
            Student student = getStudentID();
            List<StudentAtCourse> allStudentCourses = studentService.allCoursesOfStudent(student);

            if (!CollectionUtils.isEmpty(allStudentCourses)) {
                studentService.removeStudentFromCourse(chooseEnrollmentID(viewAllCoursesOfStudent(allStudentCourses)));
            } else {
                Student studentWithoutCourses = studentService.getStudentByID(student);
                System.out.printf("The student: %s %s have not relate to any course.\n", studentWithoutCourses.getFirstName(), studentWithoutCourses.getLastName());
            }
        } catch (InvalidIdException e) {
            LOGGER.info("Error getting ID: {}", e.getMessage());
        }
    }

    private Student getStudentID() throws InvalidIdException {
        System.out.println("Input the ID of student which should be remove from course. Then press enter.");
        return new Student(studentInputID.inputID());
    }

    private List<Long> viewAllCoursesOfStudent(List<StudentAtCourse> studentAtCourses) {
        Student student = studentAtCourses.get(0).getStudent();
        System.out.printf("Student: %s %s, studies at next courses:\n", student.getFirstName(), student.getLastName());
        List<Long> availableEnrollmentCourse = new ArrayList<>();
        for (StudentAtCourse studentAtCourse : studentAtCourses) {
            availableEnrollmentCourse.add(studentAtCourse.getEnrollmentID());

                    System.out.printf(
                    "ID: %d, course: %s,\n   Description of course: %s.\n",
                            studentAtCourse.getEnrollmentID(),
                            studentAtCourse.getCourse().getCourseName(),
                            studentAtCourse.getCourse().getCourseDescription());
        }
        return availableEnrollmentCourse;
    }

    private StudentAtCourse chooseEnrollmentID(List<Long> availableEnrollmentCourse) throws InvalidIdException {
        System.out.println("Choose enrollment ID from the list to wish remove and press enter.");
        Long receivedID = studentAtCourseInputID.inputID();
        if (availableEnrollmentCourse.contains(receivedID)){
            return new StudentAtCourse(receivedID);
        } else {
            throw new InvalidIdException("The received enrollment ID does not relate to the student's courses.");
        }
    }
}
