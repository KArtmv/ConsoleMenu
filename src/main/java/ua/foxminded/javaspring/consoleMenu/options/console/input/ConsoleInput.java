package ua.foxminded.javaspring.consoleMenu.options.console.input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInput {

    private Scanner sc;
    private static final String ALPHABETIC_PATTERN = "\\p{Alpha}+";
    private static final String INTEGER_PATTERN = "\\d+";
    private static final String MENU_PATTERN = "[1-9x]";

    public ConsoleInput() {
        this.sc = new Scanner(System.in);
    }

    public Integer inputNumbers() throws InputMismatchException {
        try {
            return Integer.parseInt(input(INTEGER_PATTERN));
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Failed to converted input number." + e.getMessage());
        }
    }

    public String inputCharacters() throws InputMismatchException {
        return input(ALPHABETIC_PATTERN);
    }

    public String menuInput() throws InputMismatchException {
        String s = input(MENU_PATTERN);
        if (s.equals("x")){
            sc.close();
        }
        return s;
    }

    private String input(String regex) {
        String s = sc.nextLine();
        if (s.matches(regex)) {
            return s;
        } else {
            throw new InputMismatchException(String.format("Input: '%s', does not match the required pattern: '%s'.", s, regex));
        }
    }
}
