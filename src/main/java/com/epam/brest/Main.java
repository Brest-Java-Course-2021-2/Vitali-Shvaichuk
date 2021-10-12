package com.epam.brest;

import com.epam.brest.calc.Calc;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    static boolean continueScan = true;

    public static void main(String[] args) {
        BigDecimal weight;
        BigDecimal pricePerKg;
        BigDecimal distance;
        BigDecimal pricePerKm;
        BigDecimal deliveryCost;
        Scanner scanner = new Scanner(System.in);
        while (continueScan) {
            weight = getValueFromInput(scanner, "Enter weight: ");
            pricePerKg = getValueFromInput(scanner, "Enter price per kg: ");
            distance = getValueFromInput(scanner, "Enter distance: ");
            pricePerKm = getValueFromInput(scanner, "Enter price per km: ");
            if (weight != null && pricePerKg != null && distance != null && pricePerKm != null) {
                deliveryCost = Calc.calculate(weight, pricePerKg, distance, pricePerKm);
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
}
