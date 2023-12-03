package ua.foxminded.javaspring.consoleMenu.options.console.output;

import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.List;

public class ConsolePrinter {

    public void viewAllCoursesOfStudent(List<StudentAtCourse> allStudentCourses) {
        System.out.printf("Student: %s %s, studies at next courses:\n",
                allStudentCourses.get(0).getStudent().getFirstName(), allStudentCourses.get(0).getStudent().getLastName());

        allStudentCourses.forEach(studentAtCourse -> System.out.printf(
                "ID: %d, course: %s,\n   Description of course: %s.\n",
                studentAtCourse.getEnrollmentID(),
                studentAtCourse.getCourse().getCourseName(),
                studentAtCourse.getCourse().getCourseDescription()));
    }

    public void viewAllStudentsFromCourse(List<StudentAtCourse> studentsFromCourse) {
        System.out.printf("At course: %s, study next students:\n", studentsFromCourse.get(0).getCourse().getCourseName());
        studentsFromCourse.forEach(student -> System.out.printf("%s %s\n", student.getStudent().getFirstName(), student.getStudent().getLastName()));
    }

    public void viewAmountStudentAtGroup(List<CounterStudentsAtGroup> studentsAtGroups) {
        studentsAtGroups.forEach(studentsAtGroup ->
                System.out.printf("%d of students at group: %s.\n",
                        studentsAtGroup.getStudentsCount(), studentsAtGroup.getGroupName()));
    }
}
