package ua.foxminded.javaspring.consoleMenu.options.input;

import java.util.Scanner;

public class ConsoleInput {

    private Scanner sc;
    private static final String regexForAlphabetic = "[^a-zA-Z]+";
    private static final String regexForInteger = "[^-?\\d+]";
    private static final String regexForMenu = "[^1-9x]";


    public ConsoleInput() {
        this.sc = new Scanner(System.in);
    }

    public Integer inputNumbers(){
        int receivedNumber = 0;
            try {
                receivedNumber = Integer.parseInt(input(regexForInteger));
            } catch (NumberFormatException e) {
                System.out.println("Failed to converted input number.");
            }
        return receivedNumber;
    }

    public String inputCharacters(){
        return input(regexForAlphabetic);
    }

    public String menuInput(){
        return input(regexForMenu);
    }

    private String input(String regex){
        String input;
        do {
            input = sc.nextLine();
        } while(isValidInput(input, regex));
        return input;
    }

    private boolean isValidInput(String input, String regex){
        boolean isValid = false;
        if (input == null || input.isEmpty() || input.matches("\\s+") || input.matches(regex)) {
            isValid = true;

            System.out.println("Received is illegal argument. Try again.");
        }
        return isValid;
    }
}
