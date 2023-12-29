package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.RandomNumber;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@PropertySource("classpath:AmountLimits.properties")
public class StudentToCourseGenerator implements DataGenerator<StudentAtCourse> {

    @Value("${maxCountCoursesOfStudent}")
    private int maxCountCoursesOfStudent;
    private RandomNumber randomNumber;
    private int countCourses;
    private DataConduct dataConduct;

    private List<StudentAtCourse> studentAtCourses = new ArrayList<>();

    @Autowired
    public StudentToCourseGenerator(RandomNumber randomNumber, DataConduct dataConduct) {
        this.dataConduct = dataConduct;
        this.randomNumber = randomNumber;
    }

    @Override
    public List<StudentAtCourse> generate() {
        getCoursesCount();

        List<Student> students = dataConduct.getStudents();
        students.forEach(this::addToCourseByIndex);

        return studentAtCourses;
    }

    private void addToCourseByIndex(Student student) {
        Set<Integer> courseIndices = randomlyCoursesIndex();
        courseIndices.forEach(index ->
                studentAtCourses.add(new StudentAtCourse(student, new Course(Long.valueOf(index)))));
    }

    private Set<Integer> randomlyCoursesIndex() {
        Set<Integer> indicesCoursesOfStudent = new HashSet<>();

        int randomlyQuantityCoursesOfStudent = randomNumber.generateInRange(maxCountCoursesOfStudent);

        while (indicesCoursesOfStudent.size() < randomlyQuantityCoursesOfStudent) {
            indicesCoursesOfStudent.add(randomNumber.generateInRange(countCourses));
        }
        return indicesCoursesOfStudent;
    }

    private void getCoursesCount() {
        countCourses = dataConduct.getCourses().size();
    }
}
