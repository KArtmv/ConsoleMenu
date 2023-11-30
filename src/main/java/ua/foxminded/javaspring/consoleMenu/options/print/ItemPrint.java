package ua.foxminded.javaspring.consoleMenu.options.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;

import java.util.List;

public class ItemPrint<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemPrint.class);

    private DAO<T> dao;
    private Print<T> print;

    @Autowired
    public ItemPrint(DAO<T> dao, Print<T> print) {
        this.dao = dao;
        this.print = print;
    }

    public void printAllAvailableItems(){
        List<T> list = dao.getAll();
        if (!CollectionUtils.isEmpty(list)) {
            print.printItems(list);
        } else {
            LOGGER.info("Failed to get list of {}.", dao.getClass());
        }
    }
}
