package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.data.generator.DataConduct;

import java.util.List;

public class TableInitializer<T> {

    private DAO<T> dao;
    private DataConduct dataConduct;
    private Class<T> typeClass;

    private List<T> items;

    @Autowired
    public TableInitializer(DAO<T> dao, DataConduct dataConduct, Class<T> typeClass) {
        this.dao = dao;
        this.dataConduct = dataConduct;
        this.typeClass = typeClass;

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
        items = (List<T>) dataConduct.generateItems(typeClass);
    }
}
