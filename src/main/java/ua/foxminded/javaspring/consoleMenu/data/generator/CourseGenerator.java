package ua.foxminded.javaspring.consoleMenu.data.generator;

import ua.foxminded.javaspring.consoleMenu.data.ReadResourcesFile;
import ua.foxminded.javaspring.consoleMenu.data.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseGenerator {

    private ReadResourcesFile readFile;
    private ResourcesFilesDatabaseData resourcesFiles;

    private List<Course> courses = new ArrayList<>();

    public CourseGenerator(ReadResourcesFile dataFile, ResourcesFilesDatabaseData resourcesFiles) {
        this.readFile = dataFile;
        this.resourcesFiles = resourcesFiles;
    }

    public List<Course> generate() {
        List<String> coursesName = readFile.getData(resourcesFiles.getCoursesFilePath());

        Long courseID = 1L;

        for (String string : coursesName) {
            String[] courseData = string.split("_");

            courses.add(new Course(courseID, courseData[0], courseData[1]));
            courseID++;
        }
        return courses;
    }
}
