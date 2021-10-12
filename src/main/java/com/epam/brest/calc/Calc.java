package com.epam.brest.calc;

import java.math.BigDecimal;

public interface Calc {
    static BigDecimal calculate(BigDecimal weight, BigDecimal pricePerKg,
                                BigDecimal distance, BigDecimal pricePerKm) {
        BigDecimal result = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        return result;
    }
}
