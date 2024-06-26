package ua.foxminded.javaspring.consoleMenu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.dao.GroupDAO;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;
import ua.foxminded.javaspring.consoleMenu.service.impl.GroupServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GroupServiceImplTest {

    @Mock
    private GroupDAO groupDAO;
    @InjectMocks
    private GroupServiceImpl groupService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void counterStudentsAtGroups_shouldReturnListOfCountStudentsAtGroup_whenIsCalled() {
        List<CounterStudentsAtGroup> counterStudentsAtGroup = new ArrayList<>();
        counterStudentsAtGroup.add(new CounterStudentsAtGroup(22, "someGroup1"));
        counterStudentsAtGroup.add(new CounterStudentsAtGroup(18, "someGroup2"));
        counterStudentsAtGroup.add(new CounterStudentsAtGroup(10, "someGroup3"));

        int countStudentsAtGroup = 22;

        when(groupDAO.counterStudentsAtGroups(anyInt())).thenReturn(counterStudentsAtGroup);

        List<CounterStudentsAtGroup> result = groupService.counterStudentsAtGroups(countStudentsAtGroup);

        assertThat(result).usingRecursiveComparison().isSameAs(counterStudentsAtGroup);

        verify(groupDAO).counterStudentsAtGroups(anyInt());
    }

    @Test
    void getAllGroups_shouldReturnListAvailableGroups_whenInvoke() {
        List<Group> groups = new InitializeObject().groupsListInit();

        when(groupDAO.getAll()).thenReturn(groups);

        assertThat(groupService.getAllGroups()).isSameAs(groups);

        verify(groupDAO).getAll();
    }
}
