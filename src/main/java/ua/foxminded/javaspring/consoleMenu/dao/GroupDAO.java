package ua.foxminded.javaspring.consoleMenu.dao;

import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;

import java.util.List;

public interface GroupDAO {
    List<CounterStudentsAtGroup> counterStudentsAtGroups(int count);
}
