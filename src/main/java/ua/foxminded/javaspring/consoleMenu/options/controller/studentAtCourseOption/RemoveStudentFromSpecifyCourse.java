package ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputID;
import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;

import java.util.List;

public class RemoveStudentFromSpecifyCourse {

    private StudentAtCourseService studentAtCourseService;
    private InputID<StudentAtCourse> studentAtCourseInputID;
    private InputID<Student> studentInputID;
    private StudentDAO studentDAO;

    @Autowired
    public RemoveStudentFromSpecifyCourse(StudentAtCourseService studentAtCourseService, InputID<StudentAtCourse> studentAtCourseInputID, InputID<Student> studentInputID, StudentDAO studentDAO) {
        this.studentAtCourseService = studentAtCourseService;
        this.studentAtCourseInputID = studentAtCourseInputID;
        this.studentInputID = studentInputID;
        this.studentDAO = studentDAO;

    }

    public void removeByEnrollmentID() {
        viewAllCoursesOfStudent(getStudentCourses(getStudentID()));

        if (remove(chooseEnrollmentID())) {
            System.out.println("Success, the student was suspended from the course.");
        } else {
            System.out.println("Failed, student remained on course.");
        }
    }

    private Student getStudentID() {
        System.out.println("Input the ID of student which should be remove from course. Then press enter.");
        return new Student(studentInputID.inputID());
    }

    private List<StudentAtCourse> getStudentCourses(Student student) {
        return studentDAO.studentCourses(student);
    }

    private void viewAllCoursesOfStudent(List<StudentAtCourse> studentAtCourses) {
        if (!CollectionUtils.isEmpty(studentAtCourses)) {
            Student student = studentAtCourses.get(0).getStudent();
            System.out.printf("Student: %s %s, studies at next courses:\n", student.getFirstName(), student.getLastName());

            for (StudentAtCourse studentAtCourse : studentAtCourses) {
                System.out.printf("ID: %d, course: %s,\n   Description of course: %s.\n", studentAtCourse.getEnrollmentID(),
                        studentAtCourse.getCourse().getCourseName(), studentAtCourse.getCourse().getCourseDescription());
            }
        }
    }

    private StudentAtCourse chooseEnrollmentID() {
        System.out.println("Choose enrollment ID from the list to wish remove.");
        return new StudentAtCourse(studentAtCourseInputID.inputID());
    }

    private boolean remove(StudentAtCourse studentAtCourse) {
        return studentAtCourseService.removeStudentFromCourse(studentAtCourse);
    }
}
