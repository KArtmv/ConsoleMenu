package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.RandomNumber;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PropertySource("classpath:AmountLimits.properties")
public class StudentGenerator implements DataGenerator<Student> {

    @Value("${maxCountOfStudent}")
    private int maxCountOfStudents;
    private RandomNumber randomNumber;
    private ResourcesFilesDatabaseData resourcesFiles;
    private DataConduct dataConduct;
    private int countOfGroups;

    private Map<String, Student> studentMap = new HashMap<>();

    @Autowired
    public StudentGenerator(RandomNumber randomNumber, ResourcesFilesDatabaseData resourcesFiles, DataConduct dataConduct) {
        this.dataConduct = dataConduct;
        this.randomNumber = randomNumber;
        this.resourcesFiles = resourcesFiles;
    }

    @Override
    public List<Student> generate() {
        getCounters();

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

    private void getCounters() {
        countOfGroups = dataConduct.getGroups().size();
    }

    private Long generateRandomGroupId() {
        return (long) randomNumber.generateInRange(countOfGroups);
    }

    private Long getNextStudentId() {
        return (long) studentMap.size() + 1;
    }
}
