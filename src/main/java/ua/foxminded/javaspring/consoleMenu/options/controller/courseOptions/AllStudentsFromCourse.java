package ua.foxminded.javaspring.consoleMenu.options.controller.courseOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputID;
import ua.foxminded.javaspring.consoleMenu.options.console.output.OutputListOfCourses;
import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;

import java.util.List;

public class AllStudentsFromCourse {

    private StudentAtCourseService studentAtCourseService;
    private InputID<Course> inputID;
    private OutputListOfCourses outputListOfCourses;

    @Autowired
    public AllStudentsFromCourse(StudentAtCourseService studentAtCourseService, InputID<Course> inputID, OutputListOfCourses outputListOfCourses) {
        this.studentAtCourseService = studentAtCourseService;
        this.inputID = inputID;
        this.outputListOfCourses = outputListOfCourses;
    }

    public void showAllStudentsFromCourse() {
        outputListOfCourses.viewAllCourses();
        List<StudentAtCourse> studentsFromCourse = studentAtCourseService.allStudentsFromCourse(new Course(inputID.inputID()));

        if (!CollectionUtils.isEmpty(studentsFromCourse)) {
            viewAllStudents(studentsFromCourse);
        } else {
            System.out.println("No students found for the selected course.");
        }
    }

    private void viewAllStudents(List<StudentAtCourse> studentsFromCourse) {
        System.out.printf("At course: %s, study next students:\n", studentsFromCourse.get(0).getCourse().getCourseName());
        studentsFromCourse.forEach(student -> System.out.printf("%s %s\n", student.getStudent().getFirstName(), student.getStudent().getLastName()));
    }
}
