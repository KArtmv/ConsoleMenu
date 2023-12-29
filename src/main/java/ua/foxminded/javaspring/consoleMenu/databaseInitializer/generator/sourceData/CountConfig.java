package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:AmountLimits.properties")
public class CountConfig {

    @Value("${maxCountCoursesOfStudent}")
    private Integer maxCountCoursesOfStudent;

    @Value("${maxCountOfStudent}")
    private Integer maxCountOfStudent;

    public Integer getMaxCountCoursesOfStudent() {
        return maxCountCoursesOfStudent;
    }

    public Integer getMaxCountOfStudents() {
        return maxCountOfStudent;
    }
}