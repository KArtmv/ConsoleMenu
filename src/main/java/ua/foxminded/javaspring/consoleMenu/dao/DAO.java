package ua.foxminded.javaspring.consoleMenu.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    boolean addItem(T item);

    boolean isValidItemID(Integer itemID);

    boolean isTableExist();

    void createTable();

    boolean isTableEmpty();
}
