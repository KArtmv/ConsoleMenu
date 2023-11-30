package ua.foxminded.javaspring.consoleMenu.options.print;

import ua.foxminded.javaspring.consoleMenu.model.Group;

import java.util.List;

public class GroupPrinter implements Print<Group> {

    @Override
    public void printItems(List<Group> groups) {
        groups.forEach(group -> System.out.printf("ID: %d,  group name: %s.\n", group.getGroupID(), group.getGroupName()));
    }
}
