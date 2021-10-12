package com.epam.brest.calc;

import java.math.BigDecimal;

public class CalcImpl implements Calc {
    private final BigDecimal weight;
    private final BigDecimal distance;
    private final BigDecimal pricePerKg;
    private final BigDecimal pricePerKm;

    public CalcImpl(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm) {
        this.weight = weight;
        this.distance = distance;
        this.pricePerKg = pricePerKg;
        this.pricePerKm = pricePerKm;
    }

    @Override
    public BigDecimal calculate() {
        BigDecimal result = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        return result;
    }
}
