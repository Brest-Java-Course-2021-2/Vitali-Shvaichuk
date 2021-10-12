package com.epam.brest.calc;

import java.math.BigDecimal;

@FunctionalInterface
public interface Calc {
    BigDecimal calculate();
}
