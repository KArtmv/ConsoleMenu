package ua.foxminded.javaspring.consoleMenu.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AmountLimit {

    @Value("${maxCountCoursesOfStudent}")
    private int maxCountCoursesOfStudent;

    @Value("${maxCountOfStudent}")
    private int maxCountOfStudents;

    public int getMaxCountCoursesOfStudent() {
        return maxCountCoursesOfStudent;
    }

    public int getMaxCountOfStudents() {
        return maxCountOfStudents;
    }
}
