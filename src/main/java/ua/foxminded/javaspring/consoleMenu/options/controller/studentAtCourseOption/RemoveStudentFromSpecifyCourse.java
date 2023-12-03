//package ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.CollectionUtils;
//import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
//import ua.foxminded.javaspring.consoleMenu.model.Student;
//import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
//import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
//import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
//import ua.foxminded.javaspring.consoleMenu.service.StudentService;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class RemoveStudentFromSpecifyCourse {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveStudentFromSpecifyCourse.class);
//
//    private ItemID<StudentAtCourse> studentAtCourseInputID;
//    private ItemID<Student> studentInputID;
//    private StudentService studentService;
//    private ConsolePrinter consolePrinter;
//
//    @Autowired
//    public RemoveStudentFromSpecifyCourse(ItemID<StudentAtCourse> studentAtCourseInputID, ItemID<Student> studentInputID, StudentService studentService) {
//        this.studentAtCourseInputID = studentAtCourseInputID;
//        this.studentInputID = studentInputID;
//        this.studentService = studentService;
//    }
//
//    public void removeByEnrollmentID() {
//        try {
//            System.out.println("Input the ID of student which should be remove from course. Then press enter.");
//            Student student = new Student(studentInputID.inputID());
//            List<StudentAtCourse> allStudentCourses = studentService.allCoursesOfStudent(student);
//
//            if (!CollectionUtils.isEmpty(allStudentCourses)) {
//                System.out.println("Choose enrollment ID from the list to wish remove and press enter.");
//                consolePrinter.viewAllCoursesOfStudent(allStudentCourses);
//                Long receivedID = studentAtCourseInputID.inputID();
//
//                if (allStudentCourses.stream().anyMatch(c ->c.getEnrollmentID().equals(receivedID))){
//                    studentService.removeStudentFromCourse(new StudentAtCourse(receivedID));
//                }
//
//            } else {
//                Student studentWithoutCourses = studentService.getStudentByID(student);
//                System.out.printf("The student: %s %s have not relate to any course.\n", studentWithoutCourses.getFirstName(), studentWithoutCourses.getLastName());
//            }
//        } catch (InvalidIdException e) {
//            LOGGER.info("Error getting ID: {}", e.getMessage());
//        }
//    }
//
////    private StudentAtCourse chooseEnrollmentID(List<Long> availableEnrollmentCourse) throws InvalidIdException {
////        Long receivedID = studentAtCourseInputID.inputID();
////        if (availableEnrollmentCourse.contains(receivedID)){
////            return new StudentAtCourse(receivedID);
////        } else {
////            throw new InvalidIdException("The received enrollment ID does not relate to the student's courses.");
////        }
////    }
//}
