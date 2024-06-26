package ua.foxminded.javaspring.consoleMenu.options.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.ReadResourcesFile;

@Component
@PropertySource("classpath:SourceDataFilepath.properties")
public class Menu {

    private ReadResourcesFile readFile;

    @Value("${filepath.menuFile}")
    private String menuFilePath;

    @Autowired
    public Menu(ReadResourcesFile readFile) {
        this.readFile = readFile;
    }

    public String getOptions() {
        return readFile.getScript(menuFilePath);
    }
}
