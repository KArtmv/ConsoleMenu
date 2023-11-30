package ua.foxminded.javaspring.consoleMenu.options.print;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;

@Component
public class PrintConfig {
    @Bean
    public ItemPrint<Course> coursesPrint(DAO<Course> courseDAO, Print<Course> coursesPrint) {
        return new ItemPrint<Course>(courseDAO, coursesPrint);
    }

    @Bean
    public ItemPrint<Group> groupsPrint(DAO<Group> groupDAO, Print<Group> groupPrint) {
        return new ItemPrint<Group>(groupDAO, groupPrint);
    }

    @Bean
    public Print<Group> groupPrint(){
        return new GroupPrinter();
    }

    @Bean
    public Print<Course> coursePrint(){
        return new CoursePrinter();
    }
}
