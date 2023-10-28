package ua.foxminded.javaspring.consoleMenu.data.generator;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.data.RandomNumber;
import ua.foxminded.javaspring.consoleMenu.data.ReadResourcesFile;
import ua.foxminded.javaspring.consoleMenu.data.generator.sourceData.CountConfig;
import ua.foxminded.javaspring.consoleMenu.data.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StudentGenerator {

    private RandomNumber randomNumber;

    private ResourcesFilesDatabaseData resourcesFiles;

    private CountConfig countConfig;

    private int maxCountOfStudents;

    private HashSet<Student> studentsNames = new HashSet<>();

    @Autowired
    public StudentGenerator(RandomNumber randomNumber, ResourcesFilesDatabaseData resourcesFiles, CountConfig countConfig) {
        this.randomNumber = randomNumber;
        this.resourcesFiles = resourcesFiles;
        this.countConfig = countConfig;
    }

    public List<Student> generate(List<Group> groups) {
        maxCountOfStudents = countConfig.getMaxCountOfStudents();

        studentNameRandomCombiner();
        int countOfGroups = groups.size();

        return addRandomGroup(countOfGroups);
    }

    private void studentNameRandomCombiner() {
        List<String> firstNames = resourcesFiles.getFirstNameFilePath();
        List<String> lastNames = resourcesFiles.getLastNameFilePath();

        int countFirstNames = firstNames.size();
        int countLastNames = lastNames.size();

        while (studentsNames.size() < maxCountOfStudents) {
            int randomFirstNameIndex = randomNumber.generateBetweenOneAndInputNumber(countFirstNames);
            int randomLastNameIndex = randomNumber.generateBetweenOneAndInputNumber(countLastNames);

            String firstName = firstNames.get(randomFirstNameIndex - 1);
            String lastName = lastNames.get(randomLastNameIndex - 1);

            if (!firstName.equals(lastName)) {
                studentsNames.add(new Student(firstName, lastName));
            }
        }
    }

    private List<Student> addRandomGroup(int countOfGroups) {
        int randomGroupIndex;

        Long studentID = 1L;

        List<Student> generatedStudents = new ArrayList<>();

        for (Student student : studentsNames) {

            randomGroupIndex = randomNumber.generateBetweenOneAndInputNumber(countOfGroups);

            generatedStudents.add(new Student(studentID, student.getFirstName(), student.getLastName(),
                    Long.valueOf(randomGroupIndex)));
            studentID++;
        }
        return generatedStudents;
    }
}
