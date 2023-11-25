package ua.foxminded.javaspring.consoleMenu.options.console.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.dao.GroupDAO;
import ua.foxminded.javaspring.consoleMenu.model.Group;

import java.util.List;

public class OutputListOfGroup {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutputListOfGroup.class);

    private GroupDAO groupDAO;

    @Autowired
    public OutputListOfGroup(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public void viewAllGroups() {
        List<Group> groups = groupDAO.listOfItems();
        if (!CollectionUtils.isEmpty(groups)) {
            output(groups);
        } else {
            LOGGER.info("Failed to get list of groups.");
        }
    }

    private void output(List<Group> groups) {
        groups.forEach(group -> System.out.printf("ID: %d,  group name: %s.\n", group.getGroupID(), group.getGroupName()));
    }
}
