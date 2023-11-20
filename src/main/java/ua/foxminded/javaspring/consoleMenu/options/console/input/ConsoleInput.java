package ua.foxminded.javaspring.consoleMenu.options.console.input;

import java.util.Scanner;

public class ConsoleInput {

    private Scanner sc;
    private static final String ALPHABETIC_PATTERN = "[^a-zA-Z]+";
    private static final String INTEGER_PATTERN = "[^-?\\d+]";
    private static final String MENU_PATTERN = "[^1-9x]";

    public ConsoleInput() {
        this.sc = new Scanner(System.in);
    }

    public Integer inputNumbers() {
        int receivedNumber = 0;
        try {
            receivedNumber = Integer.parseInt(input(INTEGER_PATTERN));
        } catch (NumberFormatException e) {
            System.out.println("Failed to converted input number.");
        }
        return receivedNumber;
    }

    public String inputCharacters() {
        return input(ALPHABETIC_PATTERN);
    }

    public String menuInput() {
        return input(MENU_PATTERN);
    }

    private String input(String regex) {
        String input;
        do {
            input = sc.nextLine();
        } while (handleInvalidInput(input, regex));
        return input;
    }

    private boolean handleInvalidInput(String input, String regex) {
        boolean isValid = false;
        if (input == null || input.isEmpty() || input.matches("\\s+") || input.matches(regex)) {
            isValid = true;

            System.out.println("Received is illegal argument. Try again.");
        }
        return isValid;
    }
}
