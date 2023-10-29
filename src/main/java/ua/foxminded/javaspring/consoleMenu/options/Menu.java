package ua.foxminded.javaspring.consoleMenu.options;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Menu {

    @Value("${options.menu}")
    private String menu;

    public String getOptions(){
        return menu;
    }
}
