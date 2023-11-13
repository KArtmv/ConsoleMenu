package ua.foxminded.javaspring.consoleMenu.options.console;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.dao.CourseDAO;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.dao.GroupDAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputID;
import ua.foxminded.javaspring.consoleMenu.options.console.output.OutputListOfCourses;
import ua.foxminded.javaspring.consoleMenu.options.console.output.OutputListOfGroup;

@Component
public class ConsoleInteractionConfig {

    @Bean
    public ConsoleInput consoleInput(){
        return new ConsoleInput();
    }

    @Bean
    public InputID<Group> inputGroupID(ConsoleInput consoleInput, DAO<Group> dao){
        return new InputID<>(consoleInput, dao);
    }

    @Bean
    public InputID<Course> inputCourseID(ConsoleInput consoleInput, DAO<Course> dao){
        return new InputID<>(consoleInput, dao);
    }

    @Bean
    public InputID<Student> inputStudentID(ConsoleInput consoleInput, DAO<Student> dao){
        return new InputID<>(consoleInput, dao);
    }
    @Bean
    public InputID<StudentAtCourse> enrollmentID(ConsoleInput consoleInput, DAO<StudentAtCourse> dao){
        return new InputID<>(consoleInput, dao);
    }

    @Bean
    public OutputListOfCourses listOfCourses(CourseDAO courseDAO){
        return new OutputListOfCourses(courseDAO);
    }

    @Bean
    public OutputListOfGroup listOfGroup(GroupDAO groupDAO) {
        return new OutputListOfGroup(groupDAO);
    }
}
