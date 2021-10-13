package com.epam.brest.json;

public class Price {
    private final Double from;
    private final Double to;
    private final Double value;

    public Price(Double from, Double to, Double value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "from: " + from + ", to: " + to + ", value: " + value;
    }
}
