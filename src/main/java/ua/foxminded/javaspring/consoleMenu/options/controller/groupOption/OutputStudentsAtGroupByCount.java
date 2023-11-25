package ua.foxminded.javaspring.consoleMenu.options.controller.groupOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;

import java.util.List;

public class OutputStudentsAtGroupByCount {

    private GroupService groupService;
    private ConsoleInput consoleInput;

    @Autowired
    public OutputStudentsAtGroupByCount(GroupService groupService, ConsoleInput consoleInput) {
        this.groupService = groupService;
        this.consoleInput = consoleInput;
    }

    public void groupsByCount() {
        System.out.println("Input count of students at course.");
        int receivedCountOfStudent = consoleInput.inputNumbers();
        List<CounterStudentsAtGroup> studentsAtGroups = groupService.counterStudentsAtGroups(receivedCountOfStudent);
        studentsAtGroups.sort((a, b) -> Long.compare(b.getStudentsCount(), a.getStudentsCount()));

        if (CollectionUtils.isEmpty(studentsAtGroups)) {
            System.out.printf("Could not find a group with %d or fewer students.\n", receivedCountOfStudent);
        } else {
            outputResult(studentsAtGroups);
        }
    }

    private void outputResult(List<CounterStudentsAtGroup> studentsAtGroups) {
        studentsAtGroups.forEach(studentsAtGroup ->
                System.out.printf("%d of students at group: %s.\n",
                        studentsAtGroup.getStudentsCount(), studentsAtGroup.getGroupName()));
    }
}
