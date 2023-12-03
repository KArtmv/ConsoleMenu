package ua.foxminded.javaspring.consoleMenu.options.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.InputMismatchException;

public class MenuInteraction {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuInteraction.class);

    private Menu menu;
    private ConsoleInput consoleInput;
    private StudentService studentService;
    private CourseService courseService;
    private GroupService groupService;

    private boolean isExit;
    private static final String OPTION_EXIT = "x";

    public MenuInteraction(Menu menu, ConsoleInput consoleInput, StudentService studentService, CourseService courseService, GroupService groupService) {
        this.menu = menu;
        this.consoleInput = consoleInput;
        this.studentService = studentService;
        this.courseService = courseService;
        this.groupService = groupService;
    }

    public void startMenu() {
        do {
            viewMenu();
            chooseOption();
        } while (!isExit);
    }

    private void viewMenu() {
        System.out.println(menu.getOptions());
    }

    private void chooseOption() {
        try {
            switch (consoleInput.menuInput()) {
                case "1":
                    groupService.counterStudentsAtGroups();
                    break;
                case "2":
                    courseService.allStudentsFromCourse();
                    break;
                case "3":
                    studentService.addNewStudent();
                    break;
                case "4":
                    studentService.deleteStudent();
                    break;
                case "5":
                    studentService.addStudentToCourse();
                    break;
                case "6":
                    studentService.removeStudentFromCourse();
                    break;
                case OPTION_EXIT:
                    isExit = true;
                    break;
                default:
                    LOGGER.info("Invalid option selected. Please try again.");
                    break;
            }
        } catch (InputMismatchException e) {
            LOGGER.error("Exception: {}", e.getMessage());
        }
    }
}
