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
        Student student;
        do {
            student = new Student(getStudentID());
        } while (!isStudentCorrect(student));
        removing(student);
    }

    private Long getStudentID() {
        System.out.println("Enter the ID of the student you want to remove.");
        return inputID.inputID();
    }

    private boolean isStudentCorrect(Student student) {
        Student selectedStudent = studentService.getStudentByID(student);
        System.out.printf("Received ID of student which should to remove: %s %s.\n", selectedStudent.getFirstName(), selectedStudent.getLastName());
        System.out.println("If the student is correct insert: \"y\". Else insert: Any symbol.");

        return consoleInput.inputCharacters().equals("y");
    }

    private void removing(Student student) {
        if (isStudentWithoutCourses(student)) {
            removeStudent(student);
        }
    }

    private boolean isStudentWithoutCourses(Student student) {
        return enrollmentService.removeStudentFromAllTheirCourses(student) || !doesStudentRelateToAnyCourse(student);
    }

    private boolean doesStudentRelateToAnyCourse(Student student) {
        return !studentService.allCoursesOfStudent(student).isEmpty();
    }

    private void removeStudent(Student student) {
        if (studentService.deleteStudent(student)) {
            System.out.println("Success, the student had been removed.");
        } else {
            System.out.println("Failed to remove the student.");
        }
    }
}
