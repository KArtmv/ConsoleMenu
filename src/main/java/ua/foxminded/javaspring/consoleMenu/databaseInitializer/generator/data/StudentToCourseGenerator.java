package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.util.AmountLimit;
import ua.foxminded.javaspring.consoleMenu.util.MyRandom;

import java.util.*;

public class StudentToCourseGenerator implements DataGenerator<StudentAtCourse> {
    private int maxCountCoursesOfStudent;
    private int countCourses;
    private DataConduct dataConduct;
    private AmountLimit amountLimits;
    private MyRandom random;
    private Set<Long> INDICES_COURSES_OF_STUDENT = new HashSet<>();
    private List<StudentAtCourse> studentAtCourses = new ArrayList<>();

    @Autowired
    public StudentToCourseGenerator(DataConduct dataConduct, AmountLimit amountLimits, MyRandom random) {
        this.dataConduct = dataConduct;
        this.amountLimits = amountLimits;
        this.random = random;
    }

    @Override
    public List<StudentAtCourse> generate() {
        initVariables();
        List<Student> students = dataConduct.getStudents();
        students.forEach(this::addToCourseByIndex);

        return studentAtCourses;
    }

    private void addToCourseByIndex(Student student) {
        randomlyCoursesIndex();

        INDICES_COURSES_OF_STUDENT.forEach(index ->
                studentAtCourses.add(new StudentAtCourse(student, new Course(index))));

        INDICES_COURSES_OF_STUDENT.clear();
    }

    private void randomlyCoursesIndex() {
        int amountStudentCourses = random.getInt(maxCountCoursesOfStudent);

        while (INDICES_COURSES_OF_STUDENT.size() < amountStudentCourses) {
            INDICES_COURSES_OF_STUDENT.add(random.getLong(countCourses));
        }
    }

    private void initVariables() {
        countCourses = dataConduct.getCourses().size();
        maxCountCoursesOfStudent = amountLimits.getMaxCountCoursesOfStudent();
    }
}
