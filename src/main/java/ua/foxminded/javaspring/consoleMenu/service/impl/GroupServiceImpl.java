package ua.foxminded.javaspring.consoleMenu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.consoleMenu.dao.GroupDAO;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupDAO groupDAO;

    @Autowired
    public GroupServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public List<CounterStudentsAtGroup> counterStudentsAtGroups(int count) {
        return groupDAO.counterStudentsAtGroups(count);
    }

    @Override
    public boolean isValidGroupID(Group group) {
        return groupDAO.isValidGroupID(group);
    }
}
