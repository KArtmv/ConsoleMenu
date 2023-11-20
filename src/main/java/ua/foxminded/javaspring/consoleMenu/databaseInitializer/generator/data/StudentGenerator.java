package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.RandomNumber;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.CountConfig;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentGenerator implements DataGenerator<Student> {

    private RandomNumber randomNumber;
    private ResourcesFilesDatabaseData resourcesFiles;
    private Integer maxCountOfStudents;
    private DataConduct dataConduct;
    private Integer countOfGroups;

    private Map<String, Student> studentMap = new HashMap<>();

    @Autowired
    public StudentGenerator(RandomNumber randomNumber, ResourcesFilesDatabaseData resourcesFiles, CountConfig countConfig, DataConduct dataConduct) {
        this.dataConduct = dataConduct;
        this.randomNumber = randomNumber;
        this.resourcesFiles = resourcesFiles;
        this.maxCountOfStudents = countConfig.getMaxCountOfStudents();
    }

    @Override
    public List<Student> generate() {
        getGroupCount();

        List<String> firstNames = resourcesFiles.getFirstNames();
        List<String> lastNames = resourcesFiles.getLastNames();

        int countFirstNames = firstNames.size();
        int countLastNames = lastNames.size();

        while (studentMap.size() < maxCountOfStudents) {
            String firstName = firstNames.get(randomNumber.generateInRange(countFirstNames) - 1);
            String lastName = lastNames.get(randomNumber.generateInRange(countLastNames) - 1);

            String key = firstName + lastName;
            if (!firstName.equals(lastName) && !studentMap.containsKey(key)) {
                studentMap.put(key, new Student(
                        getNextStudentId(),
                        firstName,
                        lastName,
                        generateRandomGroupId()));
            }
        }
        List<Student> generatedStudents = new ArrayList<>(studentMap.values());

        dataConduct.setStudents(generatedStudents);

        return generatedStudents;
    }

    private Long generateRandomGroupId() {
        return (long) randomNumber.generateInRange(countOfGroups);
    }

    private void getGroupCount(){
        countOfGroups = dataConduct.getGroups().size();
    }

    private Long getNextStudentId() {
        return (long) studentMap.size() + 1;
    }
}
