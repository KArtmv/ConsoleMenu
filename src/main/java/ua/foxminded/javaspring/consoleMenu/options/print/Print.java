package ua.foxminded.javaspring.consoleMenu.options.print;

import java.util.List;

public interface Print<T> {
    void printItems(List<T> tList);
}
