package ua.foxminded.javaspring.consoleMenu.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AmountLimit {

    @Value("${maxCountCoursesOfStudent}")
    public int maxCountCoursesOfStudent;

    @Value("${maxCountOfStudent}")
    public int maxCountOfStudents;
}
