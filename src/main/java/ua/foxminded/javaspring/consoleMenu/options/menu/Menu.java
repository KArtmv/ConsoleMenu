package ua.foxminded.javaspring.consoleMenu.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.ReadResourcesFile;

@Component
public class Menu {

    private ReadResourcesFile readFile;

    @Value("${options.menu}")
    private String MENU_FILE_PATH;

    @Autowired
    public Menu(ReadResourcesFile readFile) {
        this.readFile = readFile;
    }

    public String getOptions() {
        return readFile.getScript(MENU_FILE_PATH);
    }
}
