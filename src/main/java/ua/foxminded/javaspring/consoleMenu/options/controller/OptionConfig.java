package ua.foxminded.javaspring.consoleMenu.options.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.StudentConfirmationHandler;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
import ua.foxminded.javaspring.consoleMenu.options.console.input.NewStudentData;
import ua.foxminded.javaspring.consoleMenu.options.controller.courseOptions.AllStudentsFromCourse;
import ua.foxminded.javaspring.consoleMenu.options.controller.groupOption.OutputStudentsAtGroupByCount;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption.AddStudentToCourse;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption.RemoveStudentFromSpecifyCourse;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentOption.AddNewStudent;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentOption.DeleteStudentByID;
import ua.foxminded.javaspring.consoleMenu.options.menu.Menu;
import ua.foxminded.javaspring.consoleMenu.options.menu.MenuInteraction;
import ua.foxminded.javaspring.consoleMenu.options.print.ItemPrint;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;
//import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;
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
    public AddNewStudent newStudent(StudentService studentService, NewStudentData newStudentData) {
        return new AddNewStudent(studentService, newStudentData);
    }

    @Bean
    public DeleteStudentByID deleteStudentByID(ItemID<Student> inputID, StudentService studentService, StudentConfirmationHandler studentConfirmationHandler) {
        return new DeleteStudentByID(inputID, studentService, studentConfirmationHandler);
    }

    @Bean
    public AddStudentToCourse addStudentToCourse(ItemPrint<Course> courses, StudentService studentService, ItemID<Course> courseInputID, ItemID<Student> studentInputID, StudentConfirmationHandler studentConfirmationHandler) {
        return new AddStudentToCourse(courses, studentService, courseInputID, studentInputID, studentConfirmationHandler);
    }

    @Bean
    public RemoveStudentFromSpecifyCourse removeStudentFromSpecifyCourse(ItemID<StudentAtCourse> studentAtCourseItemID, ItemID<Student> studentInputID, StudentService studentService) {
        return new RemoveStudentFromSpecifyCourse(studentAtCourseItemID, studentInputID, studentService);
    }

    @Bean
    public OutputStudentsAtGroupByCount atGroupByCount(GroupService groupService, ConsoleInput consoleInput) {
        return new OutputStudentsAtGroupByCount(groupService, consoleInput);
    }

    @Bean
    public AllStudentsFromCourse studentsFromCourse(CourseService courseService, ItemID<Course> inputID, ItemPrint<Course> coursePrint) {
        return new AllStudentsFromCourse(courseService, inputID, coursePrint);
    }

    @Bean
    public NewStudentData newStudentData(ConsoleInput consoleInput, ItemID<Group> itemID, ItemPrint<Group> printAllGroups){
        return new NewStudentData(consoleInput, itemID, printAllGroups);
    }
}
