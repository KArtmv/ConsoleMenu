package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.RandomNumber;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.CountConfig;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StudentGenerator implements DataGenerator<Student> {

    private RandomNumber randomNumber;
    private ResourcesFilesDatabaseData resourcesFiles;
    private CountConfig countConfig;
    private Integer maxCountOfStudents;
    private DataConduct dataConduct;
    private HashSet<Student> studentsNames = new HashSet<>();

    @Autowired
    public StudentGenerator(RandomNumber randomNumber, ResourcesFilesDatabaseData resourcesFiles, CountConfig countConfig, DataConduct dataConduct) {
        this.dataConduct = dataConduct;
        this.randomNumber = randomNumber;
        this.resourcesFiles = resourcesFiles;
        this.countConfig = countConfig;
    }

    @Override
    public List<Student> generate() {
        maxCountOfStudents = countConfig.getMaxCountOfStudents();

        studentNameRandomCombiner();

        return addRandomGroup();
    }

    private void studentNameRandomCombiner() {
        List<String> firstNames = resourcesFiles.getFirstNames();
        List<String> lastNames = resourcesFiles.getLastNames();

        int countFirstNames = firstNames.size();
        int countLastNames = lastNames.size();

        while (studentsNames.size() < maxCountOfStudents) {
            int randomFirstNameIndex = randomNumber.generateBetweenOneAndInputNumber(countFirstNames);
            int randomLastNameIndex = randomNumber.generateBetweenOneAndInputNumber(countLastNames);

            String firstName = firstNames.get(randomFirstNameIndex - 1);
            String lastName = lastNames.get(randomLastNameIndex - 1);

            Student student = new Student(firstName, lastName);
            if (!firstName.equals(lastName) && !studentsNames.contains(student)) {
                studentsNames.add(student);
            }
        }
    }

    private List<Student> addRandomGroup() {
        int countOfGroups = dataConduct.getGroups().size();
        int randomGroupIndex;

        Long studentID = 1L;

        List<Student> generatedStudents = new ArrayList<>();

        for (Student student : studentsNames) {

            randomGroupIndex = randomNumber.generateBetweenOneAndInputNumber(countOfGroups);

            generatedStudents.add(new Student(studentID, student.getFirstName(), student.getLastName(),
                    Long.valueOf(randomGroupIndex)));
            studentID++;
        }
        dataConduct.setStudents(generatedStudents);
        return generatedStudents;
    }
}
