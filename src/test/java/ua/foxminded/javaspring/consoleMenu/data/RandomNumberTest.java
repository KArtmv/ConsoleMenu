package ua.foxminded.javaspring.consoleMenu.data;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomNumberTest {

    @Test
    public void generateBetweenOneAnd_shouldReturnIntegerValue_whenIsCalled() {
        RandomNumber randomNumber = new RandomNumber();

        for (int inputNumber = 1; inputNumber <= 20; inputNumber++) {
            int result = randomNumber.generateBetweenOneAndInputNumber(inputNumber);
            assertThat((result >= 1) && (result <= inputNumber)).isTrue();
        }
    }
}
