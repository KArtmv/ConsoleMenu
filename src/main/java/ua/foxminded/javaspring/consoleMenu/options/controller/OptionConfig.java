package ua.foxminded.javaspring.consoleMenu.options.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputID;
import ua.foxminded.javaspring.consoleMenu.options.console.output.OutputListOfCourses;
import ua.foxminded.javaspring.consoleMenu.options.console.output.OutputListOfGroup;
import ua.foxminded.javaspring.consoleMenu.options.controller.courseOptions.AllStudentsFromCourse;
import ua.foxminded.javaspring.consoleMenu.options.controller.groupOption.OutputStudentsAtGroupByCount;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption.AddStudentToCourse;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption.RemoveStudentFromSpecifyCourse;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentOption.AddNewStudent;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentOption.DeleteStudentByID;
import ua.foxminded.javaspring.consoleMenu.options.menu.Menu;
import ua.foxminded.javaspring.consoleMenu.options.menu.MenuInteraction;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;
import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

@Component
public class OptionConfig {

    @Bean
    public MenuInteraction menuInteraction(Menu menu, OutputStudentsAtGroupByCount atGroupByCount, AllStudentsFromCourse studentsFromCourse,
                                           AddNewStudent newStudent, ConsoleInput consoleInput, DeleteStudentByID deleteStudentByID,
                                           AddStudentToCourse addStudentToCourse, RemoveStudentFromSpecifyCourse removeStudentFromSpecifyCourse) {
        return new MenuInteraction(menu, atGroupByCount, studentsFromCourse, newStudent, consoleInput, deleteStudentByID, addStudentToCourse, removeStudentFromSpecifyCourse);
    }

    @Bean
    public AddNewStudent newStudent(InputID<Group> inputID, ConsoleInput consoleInput, StudentService studentService, OutputListOfGroup listOfGroup) {
        return new AddNewStudent(inputID, consoleInput, studentService, listOfGroup);
    }

    @Bean
    public DeleteStudentByID deleteStudentByID(InputID<Student> inputID, StudentService studentService, StudentAtCourseService enrollmentService, ConsoleInput consoleInput) {
        return new DeleteStudentByID(inputID, studentService, enrollmentService, consoleInput);
    }

    @Bean
    public AddStudentToCourse addStudentToCourse(OutputListOfCourses courses, StudentAtCourseService studentAtCourseService, InputID<Course> courseInputID, InputID<Student> studentInputID) {
        return new AddStudentToCourse(courses, studentAtCourseService, courseInputID, studentInputID);
    }

    @Bean
    public RemoveStudentFromSpecifyCourse removeStudentFromSpecifyCourse(StudentAtCourseService atCourseService, InputID<StudentAtCourse> atCourseInputID, InputID<Student> studentInputID, StudentService studentService) {
        return new RemoveStudentFromSpecifyCourse(atCourseService, atCourseInputID, studentInputID, studentService);
    }

    @Bean
    public OutputStudentsAtGroupByCount atGroupByCount(GroupService groupService, ConsoleInput consoleInput) {
        return new OutputStudentsAtGroupByCount(groupService, consoleInput);
    }

    @Bean
    public AllStudentsFromCourse studentsFromCourse(StudentAtCourseService studentAtCourseService, InputID<Course> inputID, OutputListOfCourses listOfCourses) {
        return new AllStudentsFromCourse(studentAtCourseService, inputID, listOfCourses);
    }
}
