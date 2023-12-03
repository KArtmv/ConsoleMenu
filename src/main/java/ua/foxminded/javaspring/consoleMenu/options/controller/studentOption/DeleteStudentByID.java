//package ua.foxminded.javaspring.consoleMenu.options.controller.studentOption;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
//import ua.foxminded.javaspring.consoleMenu.model.Student;
//import ua.foxminded.javaspring.consoleMenu.options.StudentConfirmationHandler;
//import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
////import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;
//import ua.foxminded.javaspring.consoleMenu.service.StudentService;
//
//public class DeleteStudentByID {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteStudentByID.class);
//
//    private StudentService studentService;
//    private ItemID<Student> inputID;
//    private StudentConfirmationHandler verifyValidStudent;
//
//    @Autowired
//    public DeleteStudentByID(ItemID<Student> inputID, StudentService studentService, StudentConfirmationHandler verifyValidStudent) {
//        this.inputID = inputID;
//        this.studentService = studentService;
//        this.verifyValidStudent = verifyValidStudent;
//    }
//}
