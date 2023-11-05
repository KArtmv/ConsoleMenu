package ua.foxminded.javaspring.consoleMenu.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    boolean addItem(T item);

    boolean isValidItemID(T item);

    boolean isTableExist();

    void createTable();

    boolean isTableEmpty();

    boolean removeItem(T item);

    Optional<T> getByItemID(T item);

    List<T> listOfItems(T item);

}
