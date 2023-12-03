package ua.foxminded.javaspring.consoleMenu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.dao.GroupDAO;
//import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;

import java.util.InputMismatchException;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupServiceImpl.class);

    private GroupDAO groupDAO;
    private ConsoleInput consoleInput;
    private ConsolePrinter consolePrinter;

    @Autowired
    public GroupServiceImpl(GroupDAO groupDAO, ConsoleInput consoleInput, ConsolePrinter consolePrinter) {
        this.groupDAO = groupDAO;
        this.consoleInput = consoleInput;
        this.consolePrinter = consolePrinter;
    }

    @Override
    public void counterStudentsAtGroups(Integer count) {
        try {
            System.out.println("Input the number of students enrolled in a group to find groups with that number or fewer students.");
            int receivedAmountOfStudent = consoleInput.inputNumbers();
            List<CounterStudentsAtGroup> studentsAtGroups = groupDAO.counterStudentsAtGroups(receivedAmountOfStudent);

            if (CollectionUtils.isEmpty(studentsAtGroups)) {
                System.out.printf("Could not find a group with %d or fewer students.\n", receivedAmountOfStudent);
            } else {
                studentsAtGroups.sort((a, b) -> Long.compare(b.getStudentsCount(), a.getStudentsCount()));
                consolePrinter.viewAmountStudentAtGroup(studentsAtGroups);
            }
        } catch (InputMismatchException e) {
            LOGGER.info("Error getting numbers:" + e.getMessage());
        }
    }
}
