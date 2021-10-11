package com.epam.brest.calc;

import java.math.BigDecimal;

public interface Calc {
    BigDecimal calculate(BigDecimal weight, BigDecimal pricePerKg,
                                BigDecimal distance, BigDecimal pricePerKm);
}
