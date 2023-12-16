//package ua.foxminded.javaspring.consoleMenu.options.console.input;
//
//import ua.foxminded.javaspring.consoleMenu.model.Student;
//import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
//
//public class NewStudentData {
//
//    private ConsoleInput consoleInput;
//    private ConsolePrinter printAllGroups;
//
//    public NewStudentData(ConsoleInput consoleInput, ConsolePrinter printAllGroups) {
//        this.consoleInput = consoleInput;
//        this.printAllGroups = printAllGroups;
//    }
//
//    public Student get() {
//        return new Student(getFirstName(), getLastName(), getGroupID());
//    }
//
//    private String getFirstName() {
//        System.out.println("Input student first name and press enter.");
//        return consoleInput.inputCharacters();
//    }
//
//    private String getLastName() {
//        System.out.println("Input student last name and press enter.");
//        return consoleInput.inputCharacters();
//    }
//
//    private Long getGroupID() {
//        System.out.println("Now you should choose a group from list to which should add student.\n Input ID and press enter.");
//        printAllGroups.printAllGroups();
//        return (long) consoleInput.inputNumbers();
//    }
//}
