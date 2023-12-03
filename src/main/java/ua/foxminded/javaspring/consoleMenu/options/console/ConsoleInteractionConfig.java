package ua.foxminded.javaspring.consoleMenu.options.console;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
import ua.foxminded.javaspring.consoleMenu.options.console.input.NewStudentData;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.options.menu.Menu;
import ua.foxminded.javaspring.consoleMenu.options.menu.MenuInteraction;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

@Component
public class ConsoleInteractionConfig {

    @Bean
    public ConsoleInput consoleInput() {
        return new ConsoleInput();
    }

    @Bean
    public ItemID<Group> inputGroupID(ConsoleInput consoleInput, DAO<Group> dao) {
        return new ItemID<>(consoleInput, dao);
    }

    @Bean
    public ItemID<Course> inputCourseID(ConsoleInput consoleInput, DAO<Course> dao) {
        return new ItemID<>(consoleInput, dao);
    }

    @Bean
    public ItemID<Student> inputStudentID(ConsoleInput consoleInput, DAO<Student> dao) {
        return new ItemID<>(consoleInput, dao);
    }

    @Bean
    public ItemID<StudentAtCourse> enrollmentID(ConsoleInput consoleInput, DAO<StudentAtCourse> dao) {
        return new ItemID<>(consoleInput, dao);
    }

    @Bean
    public NewStudentData newStudentData(ConsoleInput consoleInput, ItemID<Group> itemID, ConsolePrinter consolePrinter){
        return new NewStudentData(consoleInput, itemID, consolePrinter);
    }

    @Bean
    public MenuInteraction menuInteraction(Menu menu, ConsoleInput consoleInput, StudentService studentService, GroupService groupService, CourseService courseService) {
        return new MenuInteraction(menu, consoleInput, studentService, courseService, groupService);
    }

    @Bean
    public ConsolePrinter consolePrinter(DAO<Group> groupDAO, DAO<Course> courseDAO){
        return new ConsolePrinter(groupDAO, courseDAO);
    }
}
