package ua.foxminded.javaspring.consoleMenu.data.generator;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.data.RandomNumber;
import ua.foxminded.javaspring.consoleMenu.data.ReadResourcesFile;
import ua.foxminded.javaspring.consoleMenu.data.generator.sourceData.CountConfig;
import ua.foxminded.javaspring.consoleMenu.data.generator.sourceData.ResourcesFilesDatabaseData;

@Component
public class DataConfigInitializer {

    private ReadResourcesFile readResourcesFile;

    @Bean
    public ReadResourcesFile readFile(ResourceLoader resourceLoader) {
        readResourcesFile = new ReadResourcesFile(resourceLoader);
        return readResourcesFile;
    }

    @Bean
    public RandomNumber randomNumber() {
        return new RandomNumber();
    }

    @Bean
    public DataConduct dataConduct(StudentGenerator studentGenerator, CourseGenerator courseGenerator,
                                   GroupGenerator groupGenerator, StudentToCourseGenerator studentToCourseGenerator) {
        return new DataConduct(studentGenerator, courseGenerator, groupGenerator, studentToCourseGenerator);
    }

    @Bean
    public StudentGenerator studentGenerator(RandomNumber randomNumber, ResourcesFilesDatabaseData resourcesFiles, CountConfig countConfig) {
        return new StudentGenerator(randomNumber, resourcesFiles, countConfig);
    }

    @Bean
    public CourseGenerator courseGenerator(ResourcesFilesDatabaseData resourcesFiles) {
        return new CourseGenerator(resourcesFiles);
    }

    @Bean
    public GroupGenerator groupGenerator(ResourcesFilesDatabaseData resourcesFiles) {
        return new GroupGenerator(resourcesFiles);
    }

    @Bean
    public StudentToCourseGenerator studentToCourseGenerator(RandomNumber randomNumber, CountConfig countConfig) {
        return new StudentToCourseGenerator(randomNumber, countConfig);
    }
}