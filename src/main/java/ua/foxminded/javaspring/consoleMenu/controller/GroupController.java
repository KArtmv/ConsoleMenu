package ua.foxminded.javaspring.consoleMenu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.options.console.input.MyScanner;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;
import ua.foxminded.javaspring.consoleMenu.util.ApplicationMessages;

import java.util.InputMismatchException;
import java.util.List;

@Controller
public class GroupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
    private GroupService groupService;
    private MyScanner scanner;
    private ConsolePrinter consolePrinter;
    private ApplicationMessages messages;

    @Autowired
    public GroupController(GroupService groupService, MyScanner scanner, ConsolePrinter consolePrinter, ApplicationMessages messages) {
        this.groupService = groupService;
        this.scanner = scanner;
        this.consolePrinter = consolePrinter;
        this.messages = messages;
    }

    public void counterStudentsAtGroups() {
        try {
            consolePrinter.print(messages.GROUPS_BY_COUNT);
            int requestedAmountOfStudent = scanner.getInt();
            List<CounterStudentsAtGroup> studentsAtGroups = groupService.counterStudentsAtGroups(requestedAmountOfStudent);

            if (CollectionUtils.isEmpty(studentsAtGroups)) {
                consolePrinter.print(String.format(messages.HAS_NOT_GROUPS_WITH_COUNT , requestedAmountOfStudent));
            } else {
                studentsAtGroups.sort((a, b) -> Long.compare(b.getStudentsCount(), a.getStudentsCount()));
                consolePrinter.viewAmountStudentAtGroup(studentsAtGroups);
            }
        } catch (InputMismatchException e) {
            LOGGER.info("Error getting numbers:" + e.getMessage());
        }
    }
}
