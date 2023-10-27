package ua.foxminded.javaspring.consoleMenu.model;

public class StudentAtCourse {

    private Long enrollmentID;
    private Student student;
    private Course course;

    public StudentAtCourse(Student student) {
        this.student = student;
    }

    public StudentAtCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public StudentAtCourse(Long enrollmentID, Student student, Course course) {
        this.enrollmentID = enrollmentID;
        this.student = student;
        this.course = course;
    }

    public StudentAtCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Long getEnrollmentID() {
        return enrollmentID;
    }
}
