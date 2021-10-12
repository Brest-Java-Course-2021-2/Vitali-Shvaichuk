package com.epam.brest;

import com.epam.brest.calc.CalcImpl;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    static boolean continueScan = true;

    public static void main(String[] args) {
        BigDecimal weight;
        BigDecimal distance;
        BigDecimal pricePerKg;
        BigDecimal pricePerKm;
        BigDecimal deliveryCost;
        Scanner scanner = new Scanner(System.in);
        while (continueScan) {
            weight = getValueFromInput(scanner, "Enter weight: ");
            distance = getValueFromInput(scanner, "Enter distance: ");
            pricePerKg = getValueFromInput(scanner, "Enter price per kg: ");
            pricePerKm = getValueFromInput(scanner, "Enter price per km: ");
            if (weight != null && pricePerKg != null && distance != null && pricePerKm != null) {
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
}
