package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.util.AmountLimit;
import ua.foxminded.javaspring.consoleMenu.util.MyRandom;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class StudentGenerator implements DataGenerator<Student> {

    private static final AtomicLong ATOMIC_LONG = new AtomicLong(1);
    private int maxCountOfStudents;
    private AmountLimit amountLimit;
    private MyRandom random;
    private ResourcesFilesDatabaseData resourcesFiles;
    private DataConduct dataConduct;
    private int countOfGroups;
    private Map<String, Student> studentMap = new HashMap<>();

    @Autowired
    public StudentGenerator(AmountLimit amountLimit, MyRandom random, ResourcesFilesDatabaseData resourcesFiles, DataConduct dataConduct) {
        this.amountLimit = amountLimit;
        this.random = random;
        this.dataConduct = dataConduct;
        this.resourcesFiles = resourcesFiles;
    }

    @Override
    public List<Student> generate() {
        initVariables();

        List<String> firstNames = resourcesFiles.getFirstNames();
        List<String> lastNames = resourcesFiles.getLastNames();

        int countFirstNames = firstNames.size();
        int countLastNames = lastNames.size();

        while (studentMap.size() < maxCountOfStudents) {
            String firstName = firstNames.get(random.getInt(countFirstNames));
            String lastName = lastNames.get(random.getInt(countLastNames));

            String key = firstName + lastName;
            if (!firstName.equals(lastName) && !studentMap.containsKey(key)) {
                studentMap.put(key, new Student(
                                ATOMIC_LONG.getAndIncrement(),
                                firstName,
                                lastName,
                                random.getLong(countOfGroups)));
            }
        }
        List<Student> generatedStudents = new ArrayList<>(studentMap.values());

        dataConduct.setStudents(generatedStudents);

        return generatedStudents;
    }

    private void initVariables() {
        maxCountOfStudents = amountLimit.getMaxCountOfStudents();
        countOfGroups = dataConduct.getGroups().size();
    }
}
