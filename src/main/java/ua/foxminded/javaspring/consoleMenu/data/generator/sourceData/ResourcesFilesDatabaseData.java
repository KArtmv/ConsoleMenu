package ua.foxminded.javaspring.consoleMenu.data.generator.sourceData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResourcesFilesDatabaseData {

    @Value("${databaseResourseFilepath.GROUPS_FILE}")
    private String GROUPS_FILE;

    @Value("${databaseResourseFilepath.COURSES_FILE}")
    private String COURSES_FILE;

    @Value("${databaseResourseFilepath.FIRST_NAME_FILE}")
    private String FIRST_NAME_FILE;

    @Value("${databaseResourseFilepath.LAST_NAME_FILE}")
    private String LAST_NAME_FILE;

    public String getGroupsFilePath() {
        return GROUPS_FILE;
    }

    public String getCoursesFilePath() {
        return COURSES_FILE;
    }

    public String getFirstNameFilePath() {
        return FIRST_NAME_FILE;
    }

    public String getLastNameFilePath() {
        return LAST_NAME_FILE;
    }
}
