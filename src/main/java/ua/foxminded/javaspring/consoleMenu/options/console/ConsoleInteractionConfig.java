package ua.foxminded.javaspring.consoleMenu.options.console;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.controller.CourseController;
import ua.foxminded.javaspring.consoleMenu.controller.GroupController;
import ua.foxminded.javaspring.consoleMenu.controller.StudentController;
import ua.foxminded.javaspring.consoleMenu.options.StudentConfirmationHandler;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
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
    public NewStudentData newStudentData(ConsoleInput consoleInput, ConsolePrinter consolePrinter) {
        return new NewStudentData(consoleInput, consolePrinter);
    }

    @Bean
    public MenuInteraction menuInteraction(Menu menu, ConsoleInput consoleInput, StudentController studentController, GroupController groupController, CourseController courseController) {
        return new MenuInteraction(menu, consoleInput, studentController, courseController, groupController);
    }

    @Bean
    public ConsolePrinter consolePrinter(CourseService courseService, GroupService groupService) {
        return new ConsolePrinter(groupService, courseService);
    }

    @Bean
    public StudentConfirmationHandler studentConfirmationHandler(StudentService studentService, ConsoleInput consoleInput) {
        return new StudentConfirmationHandler(studentService, consoleInput);
    }
}
