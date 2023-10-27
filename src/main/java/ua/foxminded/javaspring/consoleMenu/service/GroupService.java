package ua.foxminded.javaspring.consoleMenu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.model.Group;

@Service
public interface GroupService {
    List<CounterStudentsAtGroup> counterStudentsAtGroups(int count);

    boolean isValidGroupID(Group group);
}
