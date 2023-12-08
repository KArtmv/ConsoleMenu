package ua.foxminded.javaspring.consoleMenu.options.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.controller.CourseController;
import ua.foxminded.javaspring.consoleMenu.controller.GroupController;
import ua.foxminded.javaspring.consoleMenu.controller.StudentController;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.InputMismatchException;

public class MenuInteraction {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuInteraction.class);

    private Menu menu;
    private ConsoleInput consoleInput;
    private StudentController studentController;
    private CourseController courseController;
    private GroupController groupController;

    private boolean isExit;
    private static final String OPTION_EXIT = "x";

    @Autowired
    public MenuInteraction(Menu menu, ConsoleInput consoleInput, StudentController studentController, CourseController courseController, GroupController groupController) {
        this.menu = menu;
        this.consoleInput = consoleInput;
        this.studentController = studentController;
        this.courseController = courseController;
        this.groupController = groupController;
    }

    public void startMenu() {
        do {
            viewMenu();
            chooseOption();
        } while (!isExit);
        consoleInput.close();
    }

    private void viewMenu() {
        System.out.println(menu.getOptions());
    }

    private void chooseOption() {
        try {
            switch (consoleInput.menuInput()) {
                case "1":
                    groupController.counterStudentsAtGroups();
                    break;
                case "2":
                    courseController.allStudentsFromCourse();
                    break;
                case "3":
                    studentController.addNewStudent();
                    break;
                case "4":
                    studentController.deleteStudent();
                    break;
                case "5":
                    studentController.addStudentToCourse();
                    break;
                case "6":
                    studentController.removeStudentFromCourse();
                    break;
                case OPTION_EXIT:
                    isExit = true;
                    break;
                default:
                    LOGGER.info("Invalid option selected.");
                    break;
            }
        } catch (InputMismatchException e) {
            LOGGER.error("Exception: {}", e.getMessage());
        }
    }
}
