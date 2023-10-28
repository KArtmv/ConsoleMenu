package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.dao.GroupDAO;
import ua.foxminded.javaspring.consoleMenu.data.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.data.ReadResourcesFile;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.Group;

import java.util.List;

public class GroupInitializer {

    private GroupDAO groupDAO;

    private DataConduct dataConduct;

    private ReadResourcesFile readResourcesFile;

    private SQLQueryIsTableExist queryIsTableExist;

    private SQLQueryOfCreateTable queryOfCreateTable;

    private List<Group> groups;

    @Autowired
    public GroupInitializer(GroupDAO groupDAO, DataConduct dataConduct, ReadResourcesFile readResourcesFile,
                            SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        this.groupDAO = groupDAO;
        this.dataConduct = dataConduct;
        this.readResourcesFile = readResourcesFile;
        this.queryIsTableExist = queryIsTableExist;
        this.queryOfCreateTable = queryOfCreateTable;
    }

    public void initialize() {
        if (groupDAO.isTableExist(queryIsTableExist.queryForGroupTable())) {
            checkIsTableEmptyAndPopulate();
        } else {
            groupDAO.createGroupTable(readResourcesFile.getScript(queryOfCreateTable.getGroupFilePath()));
            populateTable();
        }
    }

    private void checkIsTableEmptyAndPopulate() {
        if (groupDAO.isGroupTableEmpty()) {
            populateTable();
        }
    }

    private void populateTable() {
        generateGroupData();
        groups.forEach(groupDAO::addGroup);
    }

    private void generateGroupData() {
        groups = dataConduct.createGroups();
    }
}
