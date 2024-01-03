package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.ReadResourcesFile;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data.*;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

@Component
@PropertySource("classpath:AmountLimits.properties")
public class DataConfigInitializer {

    @Bean
    public ReadResourcesFile readFile(ResourceLoader resourceLoader) {
        return new ReadResourcesFile(resourceLoader);
    }

    @Bean
    public DataConduct dataConduct() {
        return new DataConduct();
    }

    @Bean
    public DataGenerator<Student> studentGenerator(ResourcesFilesDatabaseData resourcesFiles, DataConduct dataConduct) {
        return new StudentGenerator(resourcesFiles, dataConduct);
    }

    @Bean
    public DataGenerator<Course> courseGenerator(ResourcesFilesDatabaseData resourcesFiles, DataConduct dataConduct) {
        return new CourseGenerator(resourcesFiles, dataConduct);
    }

    @Bean
    public DataGenerator<Group> groupGenerator(ResourcesFilesDatabaseData resourcesFiles, DataConduct dataConduct) {
        return new GroupGenerator(resourcesFiles, dataConduct);
    }

    @Bean
    public DataGenerator<StudentAtCourse> studentToCourseGenerator(DataConduct dataConduct) {
        return new StudentToCourseGenerator(dataConduct);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}