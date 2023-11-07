package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseGenerator {

    private ResourcesFilesDatabaseData resourcesFiles;

    private List<Course> courses = new ArrayList<>();

    @Autowired
    public CourseGenerator(ResourcesFilesDatabaseData resourcesFiles) {
        this.resourcesFiles = resourcesFiles;
    }

    public List<Course> generate() {
        List<String> coursesName = resourcesFiles.getCourses();

        Long courseID = 1L;

        for (String string : coursesName) {
            String[] courseData = string.split("_");

            courses.add(new Course(courseID, courseData[0], courseData[1]));
            courseID++;
        }
        return courses;
    }
}
