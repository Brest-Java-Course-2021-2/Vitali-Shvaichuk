package com.epam.brest.model;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Calc implements Status {

    Scanner scanner;
    String fileName;

    public Calc(Scanner scanner, String fileName) {
        this.scanner = scanner;
        this.fileName = fileName;
    }

    @Override
    public Status handle() throws IOException, ParseException {
        try {
            BigDecimal weight = userData.get(0);
            BigDecimal distance = userData.get(1);
            BigDecimal pricePerKg = getPriceFromJSON("price-per-kg", weight);
            BigDecimal pricePerKm = getPriceFromJSON("price-per-km", distance);
            System.out.println("Delivery cost: " + weight.multiply(pricePerKg).add(distance.multiply(pricePerKm)));
        } finally {
            userData.clear();
        }
        return new ReadData(scanner, fileName);
    }

    @Override
    public StatusType getType() {
        return StatusType.CALC;
    }

    public BigDecimal getPriceFromJSON(String priceName, BigDecimal checkValue) throws IOException, ParseException {
        PriceParser parser = new PriceParser(fileName);
        return PriceParser.getRightPrice(parser.getPrices(priceName), checkValue);
    }
}
