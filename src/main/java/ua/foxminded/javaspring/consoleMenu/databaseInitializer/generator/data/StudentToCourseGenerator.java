package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.RandomNumber;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.CountConfig;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentToCourseGenerator implements DataGenerator<StudentAtCourse> {

    private RandomNumber randomNumber;
    private int countCourses;
    private int maxCountCoursesOfStudent;
    private DataConduct dataConduct;

    private List<StudentAtCourse> studentAtCourses = new ArrayList<>();

    @Autowired
    public StudentToCourseGenerator(RandomNumber randomNumber, CountConfig countConfig, DataConduct dataConduct) {
        this.dataConduct = dataConduct;
        this.randomNumber = randomNumber;
        this.maxCountCoursesOfStudent = countConfig.getMaxCountCoursesOfStudent();
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

    private void getCoursesCount(){
        countCourses = dataConduct.getCourses().size();
    }
}
