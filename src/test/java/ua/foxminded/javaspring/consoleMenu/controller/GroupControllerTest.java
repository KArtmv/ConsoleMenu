package ua.foxminded.javaspring.consoleMenu.controller;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputHandler;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;
import ua.foxminded.javaspring.consoleMenu.util.ApplicationMessages;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GroupControllerTest {

    @Mock
    private GroupService groupService;
    @Mock
    private ConsolePrinter consolePrinter;
    @Mock
    private ApplicationMessages messages;
    @Mock
    private InputHandler inputHandler;
    @InjectMocks
    private GroupController groupController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void counterStudentsAtGroups_shouldViewGroupsByAmountOfStudents_whenFoundedByRequiredAmount() {
        int requiredAmountOfStudent = 10;
        List<CounterStudentsAtGroup> counterStudentsAtGroups = new ArrayList<>();
        counterStudentsAtGroups.add(new CounterStudentsAtGroup(10, "group1"));
        counterStudentsAtGroups.add(new CounterStudentsAtGroup(1, "group2"));
        counterStudentsAtGroups.add(new CounterStudentsAtGroup(5, "group3"));

        when(inputHandler.getRequiredAmountOfStudents()).thenReturn(requiredAmountOfStudent);
        when(groupService.counterStudentsAtGroups(anyInt())).thenReturn(counterStudentsAtGroups);

        groupController.counterStudentsAtGroups();

        verify(consolePrinter).print(messages.GROUPS_BY_COUNT);
        verify(inputHandler).getRequiredAmountOfStudents();
        verify(groupService).counterStudentsAtGroups(requiredAmountOfStudent);
        verify(consolePrinter).viewAmountStudentAtGroup(counterStudentsAtGroups);
    }

    @Test
    void testCounterStudentsAtGroupsException() {
        when(inputHandler.getRequiredAmountOfStudents()).thenThrow(new InputMismatchException());

        groupController.counterStudentsAtGroups();

        verify(consolePrinter).print(messages.GROUPS_BY_COUNT);
        verify(inputHandler).getRequiredAmountOfStudents();
    }
}