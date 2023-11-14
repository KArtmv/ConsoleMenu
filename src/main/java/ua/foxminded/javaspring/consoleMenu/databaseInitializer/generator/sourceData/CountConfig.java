package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CountConfig {

    @Value("${sourceData.countConfig.MAX_COUNT_COURSES_OF_STUDENT}")
    private Integer MAX_COUNT_COURSES_OF_STUDENT;

    @Value("${sourceData.countConfig.MAX_COUNT_OF_STUDENT}")
    private Integer MAX_COUNT_OF_STUDENT;

    public Integer getMaxCountCoursesOfStudent() {
        return MAX_COUNT_COURSES_OF_STUDENT;
    }

    public Integer getMaxCountOfStudents() {
        return MAX_COUNT_OF_STUDENT;
    }
}