package ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.input.InputID;
import ua.foxminded.javaspring.consoleMenu.options.output.OutputListOfCourses;
import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;

public class AddStudentToCourse {

    private OutputListOfCourses outputListOfCourses;
    private StudentAtCourseService studentAtCourseService;
    private InputID<Course> courseInputID;
    private InputID<Student> studentInputID;

    @Autowired
    public AddStudentToCourse(OutputListOfCourses outputListOfCourses, StudentAtCourseService studentAtCourseService, InputID<Course> courseInputID,
                              InputID<Student> studentInputID) {
        this.outputListOfCourses = outputListOfCourses;
        this.studentAtCourseService = studentAtCourseService;
        this.courseInputID = courseInputID;
        this.studentInputID = studentInputID;
    }

    public void addStudentToCourse(){
        add(new StudentAtCourse(getStudent(), getCourse()));
    }

    private Student getStudent(){
        System.out.println("Input student ID.\n");
        return new Student(studentInputID.inputID());
    }

    private Course getCourse(){
        outputListOfCourses.viewAllCourses();
        System.out.println("Input course ID, choose from list.\n");
        return new Course(courseInputID.inputID());
    }

    private void add(StudentAtCourse studentAtCourse){
        if (studentAtCourseService.addStudentToCourse(studentAtCourse)){
            System.out.println("Success, the student had been added to course.");
        } else {
            System.out.println("Failed to add.");
        }
    }
}
