package ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputID;
import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.List;

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
        Student removingStudent = getStudentID();
        List<StudentAtCourse> allStudentCourses = getStudentCourses(removingStudent);

        if (CollectionUtils.isEmpty(allStudentCourses)) {
            Student studentWithoutCourses = studentService.getStudentByID(removingStudent);
            System.out.printf("The student: %s %s have not relate to any course.\n", studentWithoutCourses.getFirstName(), studentWithoutCourses.getLastName());
        } else {
            viewAllCoursesOfStudent(allStudentCourses);
            removing();
        }
    }

    private Student getStudentID() {
        System.out.println("Input the ID of student which should be remove from course. Then press enter.");
        return new Student(studentInputID.inputID());
    }

    private List<StudentAtCourse> getStudentCourses(Student student) {
        return studentService.allCoursesOfStudent(student);
    }

    private void viewAllCoursesOfStudent(List<StudentAtCourse> studentAtCourses) {
        Student student = studentAtCourses.get(0).getStudent();
        System.out.printf("Student: %s %s, studies at next courses:\n", student.getFirstName(), student.getLastName());

        studentAtCourses.forEach(studentAtCourse -> System.out.printf(
                "ID: %d, course: %s,\n   Description of course: %s.\n",
                studentAtCourse.getEnrollmentID(),
                studentAtCourse.getCourse().getCourseName(),
                studentAtCourse.getCourse().getCourseDescription()));
    }

    private void removing() {
        if (studentAtCourseService.removeStudentFromCourse(chooseEnrollmentID())) {
            System.out.println("Success, the student was suspended from the course.");
        } else {
            LOGGER.info("Failed to removing, student remained on course.");
        }
    }

    private StudentAtCourse chooseEnrollmentID() {
        System.out.println("Choose enrollment ID from the list to wish remove.");
        return new StudentAtCourse(studentAtCourseInputID.inputID());
    }
}
