//package ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
//import ua.foxminded.javaspring.consoleMenu.model.Course;
//import ua.foxminded.javaspring.consoleMenu.model.Student;
//import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
//import ua.foxminded.javaspring.consoleMenu.options.StudentConfirmationHandler;
//import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
//import ua.foxminded.javaspring.consoleMenu.options.print.ItemPrint;
//import ua.foxminded.javaspring.consoleMenu.service.StudentService;
//
//import java.util.InputMismatchException;
//
//public class AddStudentToCourse {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(AddStudentToCourse.class);
//
//    private StudentService studentService;
//    private ItemPrint<Course> printAllCourses;
//    private ItemID<Course> courseInputID;
//    private ItemID<Student> studentInputID;
//    private StudentConfirmationHandler verifyValidStudent;
//
//    @Autowired
//    public AddStudentToCourse(ItemPrint<Course> printAllCourses, StudentService studentService, ItemID<Course> courseInputID,
//                              ItemID<Student> studentInputID, StudentConfirmationHandler verifyValidStudent) {
//        this.printAllCourses = printAllCourses;
//        this.studentService = studentService;
//        this.courseInputID = courseInputID;
//        this.studentInputID = studentInputID;
//        this.verifyValidStudent = verifyValidStudent;
//    }
//
//    public void addStudentToCourse() {
//        try {
//            System.out.println("Input student ID.");
//            Student student = new Student(studentInputID.inputID());
//            if (verifyValidStudent.verifyValidStudent(student)) {
//                System.out.println("Input course ID, choose from list.");
//                printAllCourses.printAllAvailableItems();
//                studentService.addStudentToCourse(new StudentAtCourse(student, new Course(courseInputID.inputID())));
//            }
//        } catch (InvalidIdException | InputMismatchException e) {
//            LOGGER.info("Error adding student to course: " + e.getMessage());
//        }
//    }
//}
