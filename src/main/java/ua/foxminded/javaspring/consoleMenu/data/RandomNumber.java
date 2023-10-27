package ua.foxminded.javaspring.consoleMenu.data;

import java.util.Random;

public class RandomNumber {

    private final Random random = new Random();

    public Integer generateBetweenOneAndInputNumber(int inputNumber) {
        int randomNumber;

        do {
            randomNumber = random.nextInt(inputNumber + 1);
        } while (randomNumber == 0);

        return randomNumber;
    }
}