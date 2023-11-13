package ua.foxminded.javaspring.consoleMenu.options.controller.studentOption;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.input.InputID;
import ua.foxminded.javaspring.consoleMenu.options.output.OutputListOfGroup;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

public class AddNewStudent {

    private InputID<Student> inputID;
    private ConsoleInput consoleInput;
    private StudentService studentService;
    private OutputListOfGroup outputListOfGroup;

    @Autowired
    public AddNewStudent(InputID<Student> inputID, ConsoleInput consoleInput, StudentService studentService, OutputListOfGroup outputListOfGroup) {
        this.inputID = inputID;
        this.consoleInput = consoleInput;
        this.studentService = studentService;
        this.outputListOfGroup = outputListOfGroup;
    }

    public void add(){
        System.out.println("Input data of a student.");
        handleAddResult(studentService.saveStudent(new Student(getFirstName(), getLastName(), getGroup())));
    }

    private String getFirstName(){
        System.out.println("Input student first name and press enter.");
        return consoleInput.inputCharacters();
    }

    private String getLastName(){
        System.out.println("Input student last name and press enter.");
        return consoleInput.inputCharacters();
    }

    private Long getGroup(){
        System.out.println("Now you should choose a group from list to which should add student.\n Input ID and press enter.");
        outputListOfGroup.viewAllGroups();
        return inputID.inputID();
    }

    private void handleAddResult(Boolean isAdd){
        if (isAdd){
            System.out.println("Success, student had been added");
        } else {
            System.out.println("Failed to add");
        }
    }
}
