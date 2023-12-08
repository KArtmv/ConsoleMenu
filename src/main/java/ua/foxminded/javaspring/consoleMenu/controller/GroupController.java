package ua.foxminded.javaspring.consoleMenu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;

import java.util.InputMismatchException;
import java.util.List;

@Controller
public class GroupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
    private GroupService groupService;
    private ConsoleInput consoleInput;
    private ConsolePrinter consolePrinter;

    @Autowired
    public GroupController(GroupService groupService, ConsoleInput consoleInput, ConsolePrinter consolePrinter) {
        this.groupService = groupService;
        this.consoleInput = consoleInput;
        this.consolePrinter = consolePrinter;
    }

    public void counterStudentsAtGroups() {
        try {
            System.out.println("Input the number of students enrolled in a group to find groups with that number or fewer students.");
            int requestedAmountOfStudent = consoleInput.inputNumbers();
            List<CounterStudentsAtGroup> studentsAtGroups = groupService.counterStudentsAtGroups(requestedAmountOfStudent);

            if (CollectionUtils.isEmpty(studentsAtGroups)) {
                System.out.printf("Could not find a group with %d or fewer students.\n", requestedAmountOfStudent);
            } else {
                studentsAtGroups.sort((a, b) -> Long.compare(b.getStudentsCount(), a.getStudentsCount()));
                consolePrinter.viewAmountStudentAtGroup(studentsAtGroups);
            }
        } catch (InputMismatchException e) {
            LOGGER.info("Error getting numbers:" + e.getMessage());
        }
    }
}
