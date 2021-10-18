package com.epam.brest.model;

import java.math.BigDecimal;
import java.util.Scanner;

public class ReadData implements Status {

    public static final int NUMBER_OF_USER_DATA = 2;
    final Scanner scanner;
    final String fileName;

    public ReadData(Scanner scanner, String fileName) {
        this.scanner = scanner;
        this.fileName = fileName;
    }

    @Override
    public Status handle() {
        if (userData.size() < NUMBER_OF_USER_DATA) {
            System.out.print(messages.get(userData.size()));
            String inputValue = scanner.next();
            if (inputValue.equalsIgnoreCase("q")) {
                return new Exit();
            } else if (isCorrectValue(inputValue)) {
                userData.add(new BigDecimal(inputValue));
            }
        } else {
            return new Calc(scanner, fileName);
        }
        return this;
    }

    private boolean isCorrectValue(String inputValue) {
        try {
            BigDecimal enteredValue = new BigDecimal(inputValue);
            if (enteredValue.doubleValue() > 0) {
                return true;
            } else {
                System.out.println("Incorrect value: " + inputValue);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Incorrect value: " + inputValue);
        }
        return false;
    }

    @Override
    public StatusType getType() {
        return StatusType.READ_DATA;
    }
}
