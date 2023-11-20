package ua.foxminded.javaspring.consoleMenu.options.controller.studentOption;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputID;
import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

public class DeleteStudentByID {

    private InputID<Student> inputID;
    private StudentService studentService;
    private StudentAtCourseService enrollmentService;
    private ConsoleInput consoleInput;

    @Autowired
    public DeleteStudentByID(InputID<Student> inputID, StudentService studentService, StudentAtCourseService enrollmentService, ConsoleInput consoleInput) {
        this.inputID = inputID;
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
        this.consoleInput = consoleInput;
    }

    public void remove() {
        Student student = new Student(getStudentID());

        if (isStudentCorrect(student)) {
            removing(student);
        } else {
            System.out.println("Removal canceled.");
        }
    }

    private Long getStudentID() {
        System.out.println("Enter the ID of the student you want to remove.");
        return inputID.inputID();
    }

    private boolean isStudentCorrect(Student student) {
        Student selectedStudent = studentService.getStudentByID(student);
        System.out.printf("Received ID of student which should to remove: %s %s.\n",
                selectedStudent.getFirstName(), selectedStudent.getLastName());
        System.out.print("Confirm removal (yes/no): ");

        return consoleInput.inputCharacters().equalsIgnoreCase("yes");
    }

    private void removing(Student student) {
        if (hasStudentCourses(student)) {
            enrollmentService.removeStudentFromAllTheirCourses(student);
        }
        System.out.println(studentService.deleteStudent(student)
                ? "Success, the student had been removed."
                : "Failed to remove the student.");
    }

    private boolean hasStudentCourses(Student student) {
        return !studentService.allCoursesOfStudent(student).isEmpty();
    }
}
