package ua.foxminded.javaspring.consoleMenu.data.generator;

import ua.foxminded.javaspring.consoleMenu.data.RandomNumber;
import ua.foxminded.javaspring.consoleMenu.data.generator.sourceData.CountConfig;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentToCourseGenerator {

    private RandomNumber randomNumber;
    private CountConfig countConfig;
    private int countCourses;

    private int maxCountCoursesOfStudent;

    private List<StudentAtCourse> studentAtCourses = new ArrayList<>();

    public StudentToCourseGenerator(RandomNumber randomNumber, CountConfig countConfig) {
        this.randomNumber = randomNumber;
        this.countConfig = countConfig;
    }

    public List<StudentAtCourse> addStudentToCourse(List<Student> students, int coursesCount) {
        countCourses = coursesCount;
        maxCountCoursesOfStudent = countConfig.getMaxCountCoursesOfStudent();

        students.forEach(this::addToCourseByIndex);

        return studentAtCourses;
    }

    private void addToCourseByIndex(Student student) {
        Set<Integer> courseIndices = randomlyCoursesIndex();

        for (Integer courseIndex : courseIndices) {
            studentAtCourses.add(new StudentAtCourse(student, new Course(Long.valueOf(courseIndex))));
        }
    }

    private Set<Integer> randomlyCoursesIndex() {
        Set<Integer> indicesCoursesOfStudent = new HashSet<>();

        int randomlyQuantityCoursesOfStudent = randomNumber.generateBetweenOneAndInputNumber(maxCountCoursesOfStudent);

        while (indicesCoursesOfStudent.size() < randomlyQuantityCoursesOfStudent) {
            indicesCoursesOfStudent.add(randomNumber.generateBetweenOneAndInputNumber(countCourses));
        }
        return indicesCoursesOfStudent;
    }
}
