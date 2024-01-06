package ua.foxminded.javaspring.consoleMenu.options.console;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.controller.CourseController;
import ua.foxminded.javaspring.consoleMenu.controller.GroupController;
import ua.foxminded.javaspring.consoleMenu.controller.StudentController;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputHandler;
import ua.foxminded.javaspring.consoleMenu.options.console.input.MyScanner;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.options.menu.Menu;
import ua.foxminded.javaspring.consoleMenu.options.menu.MenuInteraction;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

@Component
public class ConsoleInteractionConfig {

    @Bean
    public MyScanner scanner() {
        return new MyScanner();
    }

    @Bean
    public MenuInteraction menuInteraction(ConsolePrinter consolePrinter, InputHandler consoleInput, StudentController studentController, GroupController groupController, CourseController courseController) {
        return new MenuInteraction(consoleInput, studentController, courseController, groupController, consolePrinter);
    }

    @Bean
    public ConsolePrinter consolePrinter(CourseService courseService, GroupService groupService, Menu menu) {
        return new ConsolePrinter(groupService, courseService, menu);
    }

    @Bean
    public InputHandler input(MyScanner scanner, ConsolePrinter consolePrinter, StudentService studentService){
        return new InputHandler(scanner, consolePrinter, studentService);
    }
}
