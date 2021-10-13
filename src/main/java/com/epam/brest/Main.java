package com.epam.brest;

import com.epam.brest.calc.CalcImpl;
import com.epam.brest.json.Price;
import com.epam.brest.json.PriceParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    static boolean continueScan = true;

    public static void main(String[] args) throws IOException, ParseException {
        String fileName;
        if (args.length == 0) {
            System.out.println("The prices filename was not specified. " +
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
        BigDecimal weight;
        BigDecimal distance;
        BigDecimal pricePerKg;
        BigDecimal pricePerKm;
        BigDecimal deliveryCost;
        Scanner scanner = new Scanner(System.in);
        while (continueScan) {
            weight = getValueFromInput(scanner, "Enter weight: ");
            distance = getValueFromInput(scanner, "Enter distance: ");
            if (weight != null && distance != null) {
                pricePerKg = getPriceFromJSON(fileName, "price-per-kg", weight);
                pricePerKm = getPriceFromJSON(fileName, "price-per-km", distance);
                CalcImpl calculator = new CalcImpl(weight, distance, pricePerKg, pricePerKm);
                deliveryCost = calculator.calculate();
                System.out.println("Delivery cost: " + deliveryCost);
            }
        }
        scanner.close();
        System.out.println("Invalid input. Exiting...");
    }

    private static BigDecimal getValueFromInput(Scanner scanner, String message) {
        BigDecimal value = null;
        if (continueScan) {
            System.out.print(message);
            if (scanner.hasNextBigDecimal()) {
                value = scanner.nextBigDecimal();
                if (value.compareTo(BigDecimal.valueOf(0)) < 0) {
                    value = null;
                    continueScan = false;
                }
            } else {
                continueScan = false;
            }
        }
        return value;
    }

    private static BigDecimal getPriceFromJSON(String fileName, String priceName, BigDecimal checkValue) throws IOException, ParseException {
        PriceParser parser = new PriceParser(fileName);
        List<Price> prices = parser.getPrices(priceName);
        return PriceParser.getRightPrice(prices, checkValue);
    }
}
