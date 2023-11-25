package ua.foxminded.javaspring.consoleMenu.options.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.controller.courseOptions.AllStudentsFromCourse;
import ua.foxminded.javaspring.consoleMenu.options.controller.groupOption.OutputStudentsAtGroupByCount;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption.AddStudentToCourse;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption.RemoveStudentFromSpecifyCourse;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentOption.AddNewStudent;
import ua.foxminded.javaspring.consoleMenu.options.controller.studentOption.DeleteStudentByID;

public class MenuInteraction {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuInteraction.class);

    private Menu menu;
    private OutputStudentsAtGroupByCount atGroupByCount;
    private AllStudentsFromCourse studentsFromCourse;
    private AddNewStudent newStudent;
    private ConsoleInput consoleInput;
    private DeleteStudentByID deleteStudentByID;
    private AddStudentToCourse addStudentToCourse;
    private RemoveStudentFromSpecifyCourse removeStudentFromSpecifyCourse;

    private boolean isExit;
    private static final String OPTION_EXIT = "x";

    @Autowired
    public MenuInteraction(Menu menu, OutputStudentsAtGroupByCount atGroupByCount, AllStudentsFromCourse studentsFromCourse,
                           AddNewStudent newStudent, ConsoleInput consoleInput, DeleteStudentByID deleteStudentByID,
                           AddStudentToCourse addStudentToCourse, RemoveStudentFromSpecifyCourse removeStudentFromSpecifyCourse) {
        this.menu = menu;
        this.atGroupByCount = atGroupByCount;
        this.studentsFromCourse = studentsFromCourse;
        this.newStudent = newStudent;
        this.consoleInput = consoleInput;
        this.deleteStudentByID = deleteStudentByID;
        this.addStudentToCourse = addStudentToCourse;
        this.removeStudentFromSpecifyCourse = removeStudentFromSpecifyCourse;
        this.isExit = false;
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
        switch (consoleInput.menuInput()) {
            case ("1"):
                atGroupByCount.groupsByCount();
                break;
            case ("2"):
                studentsFromCourse.showAllStudentsFromCourse();
                break;
            case ("3"):
                newStudent.add();
                break;
            case ("4"):
                deleteStudentByID.remove();
                break;
            case ("5"):
                addStudentToCourse.addStudentToCourse();
                break;
            case ("6"):
                removeStudentFromSpecifyCourse.removeByEnrollmentID();
                break;
            case OPTION_EXIT:
                isExit = true;
                break;
            default:
                LOGGER.info("Invalid option selected. Please try again.");
        }
    }
}
