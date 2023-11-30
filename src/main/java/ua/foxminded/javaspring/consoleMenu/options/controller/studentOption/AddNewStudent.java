package ua.foxminded.javaspring.consoleMenu.options.controller.studentOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.options.console.input.NewStudentData;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.InputMismatchException;

public class AddNewStudent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddNewStudent.class);

    private StudentService studentService;
    private NewStudentData newStudentData; 

    @Autowired
    public AddNewStudent(StudentService studentService, NewStudentData newStudentData) {
        this.newStudentData = newStudentData;
        this.studentService = studentService;
    }

    public void add() {
        System.out.println("Input data of a student.");
        try {
            studentService.saveStudent(newStudentData.get());
            System.out.println("Success, student had been added");
        } catch (InputMismatchException | InvalidIdException e) {
            LOGGER.info("Failed getting student data:" + e.getMessage());
        }
    }
}
