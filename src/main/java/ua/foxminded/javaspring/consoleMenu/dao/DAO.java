package ua.foxminded.javaspring.consoleMenu.dao;

import java.util.List;

public interface DAO<T> {
    boolean addItem(T item);

    boolean isValidItemID(Integer itemID);

    boolean isTableExist();

    void createTable();

    boolean isTableEmpty();

    List<T> getAll();
}
