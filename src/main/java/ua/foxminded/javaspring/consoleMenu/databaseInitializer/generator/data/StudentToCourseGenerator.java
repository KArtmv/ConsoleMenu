package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.*;

public class StudentToCourseGenerator implements DataGenerator<StudentAtCourse> {
    private static final Random RANDOM = new Random();
    public Set<Long> INDICES_COURSES_OF_STUDENT = new HashSet<>();
    @Value("${maxCountCoursesOfStudent}")
    private int maxCountCoursesOfStudent;
    private int countCourses;
    private DataConduct dataConduct;

    private List<StudentAtCourse> studentAtCourses = new ArrayList<>();

    @Autowired
    public StudentToCourseGenerator(DataConduct dataConduct) {
        this.dataConduct = dataConduct;
    }

    @Override
    public List<StudentAtCourse> generate() {
        getCoursesCount();
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
        int amountStudentCourses = RANDOM.nextInt(maxCountCoursesOfStudent) + 1;

        while (INDICES_COURSES_OF_STUDENT.size() < amountStudentCourses) {
            INDICES_COURSES_OF_STUDENT.add(RANDOM.longs(1, (countCourses + 1)).limit(1).findFirst().getAsLong());
        }
    }

    private void getCoursesCount() {
        countCourses = dataConduct.getCourses().size();
    }
}
