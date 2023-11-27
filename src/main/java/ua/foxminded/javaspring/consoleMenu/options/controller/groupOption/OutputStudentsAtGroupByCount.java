package ua.foxminded.javaspring.consoleMenu.options.controller.groupOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;

import java.util.InputMismatchException;
import java.util.List;

public class OutputStudentsAtGroupByCount {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutputStudentsAtGroupByCount.class);

    private GroupService groupService;
    private ConsoleInput consoleInput;

    @Autowired
    public OutputStudentsAtGroupByCount(GroupService groupService, ConsoleInput consoleInput) {
        this.groupService = groupService;
        this.consoleInput = consoleInput;
    }

    public void groupsByCount() {
        try {
            System.out.println("Input the number of students enrolled in a group to find groups with that number or fewer students.");
            int receivedAmountOfStudent = consoleInput.inputNumbers();
            List<CounterStudentsAtGroup> studentsAtGroups = groupService.counterStudentsAtGroups(receivedAmountOfStudent);

            if (CollectionUtils.isEmpty(studentsAtGroups)) {
                System.out.printf("Could not find a group with %d or fewer students.\n", receivedAmountOfStudent);
            } else {
                studentsAtGroups.sort((a, b) -> Long.compare(b.getStudentsCount(), a.getStudentsCount()));
                outputResult(studentsAtGroups);
            }
        } catch (InputMismatchException e) {
            LOGGER.info("Error getting numbers:" + e.getMessage());
        }
    }

    private void outputResult(List<CounterStudentsAtGroup> studentsAtGroups) {
        studentsAtGroups.forEach(studentsAtGroup ->
                System.out.printf("%d of students at group: %s.\n",
                        studentsAtGroup.getStudentsCount(), studentsAtGroup.getGroupName()));
    }
}
