package ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputID;
import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveStudentFromSpecifyCourse {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveStudentFromSpecifyCourse.class);

    private StudentAtCourseService studentAtCourseService;
    private InputID<StudentAtCourse> studentAtCourseInputID;
    private InputID<Student> studentInputID;
    private StudentService studentService;

    @Autowired
    public RemoveStudentFromSpecifyCourse(StudentAtCourseService studentAtCourseService, InputID<StudentAtCourse> studentAtCourseInputID, InputID<Student> studentInputID, StudentService studentService) {
        this.studentAtCourseService = studentAtCourseService;
        this.studentAtCourseInputID = studentAtCourseInputID;
        this.studentInputID = studentInputID;
        this.studentService = studentService;

    }

    public void removeByEnrollmentID() {
        try {
            Student removingStudent = getStudentID();
            List<StudentAtCourse> allStudentCourses = getStudentCourses(removingStudent);

            if (CollectionUtils.isEmpty(allStudentCourses)) {
                Student studentWithoutCourses = studentService.getStudentByID(removingStudent);
                System.out.printf("The student: %s %s have not relate to any course.\n", studentWithoutCourses.getFirstName(), studentWithoutCourses.getLastName());
            } else {
                removing(chooseEnrollmentID(viewAllCoursesOfStudent(allStudentCourses)));
            }
        } catch (InvalidIdException e) {
            LOGGER.info("Error getting ID: " + e.getMessage());
        }
    }

    private Student getStudentID() throws InvalidIdException {
        System.out.println("Input the ID of student which should be remove from course. Then press enter.");
        return new Student(studentInputID.inputID());
    }

    private List<StudentAtCourse> getStudentCourses(Student student) {
        return studentService.allCoursesOfStudent(student);
    }

    private Set<Long> viewAllCoursesOfStudent(List<StudentAtCourse> studentAtCourses) {
        Student student = studentAtCourses.get(0).getStudent();
        System.out.printf("Student: %s %s, studies at next courses:\n", student.getFirstName(), student.getLastName());
        Set<Long> availableEnrollmentCourse = new HashSet<>();
        Long enrollmentID;
        for (StudentAtCourse studentAtCourse : studentAtCourses) {
            enrollmentID = studentAtCourse.getEnrollmentID();
            availableEnrollmentCourse.add(enrollmentID);

                    System.out.printf(
                    "ID: %d, course: %s,\n   Description of course: %s.\n",
                    enrollmentID,
                    studentAtCourse.getCourse().getCourseName(),
                    studentAtCourse.getCourse().getCourseDescription());
        }
        return availableEnrollmentCourse;
    }

    private StudentAtCourse chooseEnrollmentID(Set<Long> availableEnrollmentCourse) throws InvalidIdException {
        System.out.println("Choose enrollment ID from the list to wish remove and press enter.");
        Long receivedID = studentAtCourseInputID.inputID();
        if (availableEnrollmentCourse.contains(receivedID)){
            return new StudentAtCourse(receivedID);
        } else {
            throw new InvalidIdException("The received enrollment ID does not relate to the student's courses.");
        }
    }

    private void removing(StudentAtCourse enrollmentID) {
        try {
            studentAtCourseService.removeStudentFromCourse(enrollmentID);
        } catch (Exception e) {
            LOGGER.error("Failed removing student from course: " + e.getMessage());
        }
    }
}
