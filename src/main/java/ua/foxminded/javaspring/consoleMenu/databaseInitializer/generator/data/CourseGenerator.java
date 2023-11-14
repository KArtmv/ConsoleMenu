package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseGenerator implements DataGenerator<Course> {

    private ResourcesFilesDatabaseData resourcesFiles;
    private DataConduct dataConduct;

    private List<Course> courses = new ArrayList<>();

    @Autowired
    public CourseGenerator(ResourcesFilesDatabaseData resourcesFiles, DataConduct dataConduct) {
        this.resourcesFiles = resourcesFiles;
        this.dataConduct = dataConduct;
    }

    @Override
    public List<Course> generate() {
        List<String> coursesName = resourcesFiles.getCourses();

        Long courseID = 1L;

        for (String string : coursesName) {
            String[] courseData = string.split("_");

            courses.add(new Course(courseID, courseData[0], courseData[1]));
            courseID++;
        }
        dataConduct.setCourses(courses);
        return courses;
    }
}
