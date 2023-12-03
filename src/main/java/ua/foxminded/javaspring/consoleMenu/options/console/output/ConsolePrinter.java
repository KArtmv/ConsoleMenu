//package ua.foxminded.javaspring.consoleMenu.options.console.output;
//
//import ua.foxminded.javaspring.consoleMenu.model.Course;
//import ua.foxminded.javaspring.consoleMenu.model.Group;
//import ua.foxminded.javaspring.consoleMenu.model.Student;
//import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
//import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
//import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
//import ua.foxminded.javaspring.consoleMenu.service.StudentService;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class ConsolePrinter {
//
//    private StudentService studentService;
//    private ItemID<Course> courseID;
//    private ItemID<Group> groupID;
//    private ItemID<Student> studentID;
//    private ConsoleInput consoleInput;
//
//    public ConsolePrinter(StudentService studentService, ItemID<Course> courseID, ItemID<Group> groupID, ItemID<Student> studentID, ConsoleInput consoleInput) {
//        this.studentService = studentService;
//        this.courseID = courseID;
//        this.groupID = groupID;
//        this.studentID = studentID;
//        this.consoleInput = consoleInput;
//    }
//
//    private List<Long> viewAllCoursesOfStudent(Student student) {
//        List<StudentAtCourse> allStudentCourses = studentService.allCoursesOfStudent(student);
//
//        System.out.printf("Student: %s %s, studies at next courses:\n",
//                allStudentCourses.get(0).getStudent().getFirstName(), allStudentCourses.get(0).getStudent().getLastName());
//        List<Long> availableCoursesByID = new ArrayList<>();
//
//        for (StudentAtCourse studentAtCourse : allStudentCourses) {
//            availableCoursesByID.add(studentAtCourse.getEnrollmentID());
//
//            System.out.printf(
//                    "ID: %d, course: %s,\n   Description of course: %s.\n",
//                    studentAtCourse.getEnrollmentID(),
//                    studentAtCourse.getCourse().getCourseName(),
//                    studentAtCourse.getCourse().getCourseDescription());
//        }
//        return availableCoursesByID;
//    }
//}
