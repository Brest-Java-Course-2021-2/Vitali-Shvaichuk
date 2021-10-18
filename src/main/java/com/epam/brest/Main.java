package com.epam.brest;

import com.epam.brest.model.ReadData;
import com.epam.brest.model.Status;
import com.epam.brest.model.StatusType;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        String fileName = getFileName(args);
        try (Scanner scanner = new Scanner(System.in)) {
            Status currentStatus = new ReadData(scanner, fileName);
            while (currentStatus.getType() != StatusType.EXIT) {
                currentStatus = currentStatus.handle();
            }
        }
    }

    public static String getFileName(String[] args) {
        String fileName;
        if (args.length == 0) {
            System.out.println("The 'prices' filename was not specified. " +
                    "Switching to default prices JSON file...");
            fileName = "prices.json";
        } else {
            fileName = args[0];
            if (!new File("src/main/resources/" + fileName).exists()) {
                System.out.println("File \"" + fileName + "\" does not exist. " +
                        "Switching to default prices JSON file...");
                fileName = "prices.json";
            }
        }
        return fileName;
    }
}
