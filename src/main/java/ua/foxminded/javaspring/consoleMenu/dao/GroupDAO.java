package ua.foxminded.javaspring.consoleMenu.dao;

import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.model.Group;

import java.util.List;

public interface GroupDAO {
    boolean addGroup(Group group);

    List<CounterStudentsAtGroup> counterStudentsAtGroups(int count);

    boolean isValidGroupID(Group group);

    boolean isTableExist(String sqlQuery);

    void createGroupTable(String sqlQuery);

    boolean isGroupTableEmpty();
}
