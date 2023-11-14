package ua.foxminded.javaspring.consoleMenu.databaseInitializer.tables;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data.DataGenerator;

import java.util.List;

public class TableInitializer<T> {

    private DAO<T> dao;
    private DataGenerator<T> generateItems;

    private List<T> items;

    @Autowired
    public TableInitializer(DAO<T> dao, DataGenerator<T> generateItems) {
        this.dao = dao;
        this.generateItems = generateItems;
    }

    public void initialize() {
        if (dao.isTableExist()) {
            populateIfEmpty();
        } else {
            dao.createTable();
            populateTable();
        }
    }

    private void populateIfEmpty() {
        if (dao.isTableEmpty()) {
            populateTable();
        }
    }

    private void populateTable() {
        generateData();
        items.forEach(dao::addItem);
    }

    private void generateData() {
        items = generateItems.generate();
    }
}
