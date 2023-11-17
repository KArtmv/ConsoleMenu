package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CourseGenerator implements DataGenerator<Course> {

    private ResourcesFilesDatabaseData resourcesFiles;
    private DataConduct dataConduct;

//    private List<Course> courses = new ArrayList<>();

    @Autowired
    public CourseGenerator(ResourcesFilesDatabaseData resourcesFiles, DataConduct dataConduct) {
        this.resourcesFiles = resourcesFiles;
        this.dataConduct = dataConduct;
    }

    @Override
    public List<Course> generate() {
        List<String> sourceResult = resourcesFiles.getCourses();

        List<Course> courses = IntStream.range(0, sourceResult.size())
                .mapToObj(i -> {
                    String[] courseData = sourceResult.get(i).split("_");
                    return new Course((long) (i + 1), courseData[0], courseData[1]);
                }).collect(Collectors.toList());

        dataConduct.setCourses(courses);
        return courses;
    }
}
