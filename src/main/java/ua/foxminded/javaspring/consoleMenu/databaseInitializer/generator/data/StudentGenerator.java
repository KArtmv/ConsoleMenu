package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Student;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class StudentGenerator implements DataGenerator<Student> {

    private static final Random RANDOM = new Random();
    @Value("${maxCountOfStudent}")
    private int maxCountOfStudents;
    private ResourcesFilesDatabaseData resourcesFiles;
    private DataConduct dataConduct;
    private int countOfGroups;
    private Map<String, Student> studentMap = new HashMap<>();

    @Autowired
    public StudentGenerator(ResourcesFilesDatabaseData resourcesFiles, DataConduct dataConduct) {
        this.dataConduct = dataConduct;
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
            String firstName = firstNames.get(RANDOM.nextInt(countFirstNames - 1) + 1);
            String lastName = lastNames.get(RANDOM.nextInt(countLastNames - 1) + 1);

            String key = firstName + lastName;
            if (!firstName.equals(lastName) && !studentMap.containsKey(key)) {
                studentMap.put(key, new Student(
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
        return RANDOM.longs(1, (countOfGroups + 1)).limit(1).findFirst().getAsLong();
    }
}
