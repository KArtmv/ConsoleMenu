package ua.foxminded.javaspring.consoleMenu.options.console;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;

@Component
public class ConsoleInteractionConfig {

    @Bean
    public ConsoleInput consoleInput() {
        return new ConsoleInput();
    }

    @Bean
    public ItemID<Group> inputGroupID(ConsoleInput consoleInput, DAO<Group> dao) {
        return new ItemID<>(consoleInput, dao);
    }

    @Bean
    public ItemID<Course> inputCourseID(ConsoleInput consoleInput, DAO<Course> dao) {
        return new ItemID<>(consoleInput, dao);
    }

    @Bean
    public ItemID<Student> inputStudentID(ConsoleInput consoleInput, DAO<Student> dao) {
        return new ItemID<>(consoleInput, dao);
    }

    @Bean
    public ItemID<StudentAtCourse> enrollmentID(ConsoleInput consoleInput, DAO<StudentAtCourse> dao) {
        return new ItemID<>(consoleInput, dao);
    }

//    @Bean
//    public OutputListOfCourses listOfCourses(CourseDAO courseDAO) {
//        return new OutputListOfCourses(courseDAO);
//    }
//
//    @Bean
//    public OutputListOfGroup listOfGroup(GroupDAO groupDAO) {
//        return new OutputListOfGroup(groupDAO);
//    }
}
