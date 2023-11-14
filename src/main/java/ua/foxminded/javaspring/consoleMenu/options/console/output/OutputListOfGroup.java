package ua.foxminded.javaspring.consoleMenu.options.console.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.dao.GroupDAO;
import ua.foxminded.javaspring.consoleMenu.model.Group;

import java.util.List;

public class OutputListOfGroup {

    private GroupDAO groupDAO;

    @Autowired
    public OutputListOfGroup(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public void viewAllGroups() {
        List<Group> groups = groupDAO.listOfItems();
        if (!CollectionUtils.isEmpty(groups)) {
            output(groups);
        }
    }

    private void output(List<Group> groups) {
        for (Group group : groups) {
            System.out.printf("ID: %d,  group name: %s.\n", group.getGroupID(), group.getGroupName());
        }
    }
}
