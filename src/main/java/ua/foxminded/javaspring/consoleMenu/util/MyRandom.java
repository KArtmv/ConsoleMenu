package ua.foxminded.javaspring.consoleMenu.util;

import java.util.concurrent.ThreadLocalRandom;

public class MyRandom {

    public int getInt(int bound){
        return ThreadLocalRandom.current().nextInt(1, bound);
    }

    public long getLong(int bound){
        return ThreadLocalRandom.current().nextLong(1, bound);
    }
}
