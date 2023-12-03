package ua.foxminded.javaspring.consoleMenu.service;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;

import java.util.List;

@Service
public interface GroupService {
    void counterStudentsAtGroups();
}
