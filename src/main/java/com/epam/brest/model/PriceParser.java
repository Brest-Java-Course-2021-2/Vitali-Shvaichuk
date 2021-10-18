package com.epam.brest.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PriceParser {
    private final String fileName;

    public PriceParser(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public static BigDecimal getRightPrice(List<Price> prices, BigDecimal checkValue) {
        BigDecimal rightPrice = new BigDecimal("-1");
        for (Price price: prices) {
            if (checkValue.doubleValue() > price.getFrom() && checkValue.doubleValue() <= price.getTo()) {
                rightPrice = BigDecimal.valueOf(price.getValue());
            }
        }
        return rightPrice;
    }

    public List<Price> getPrices(String priceName) throws IOException, ParseException {
        List<Price> prices = new ArrayList<>();
        InputStream fis = PriceParser.class.getClassLoader().getResourceAsStream(getFileName());
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        fis.close();
        reader.close();
        JSONArray array = (JSONArray) jsonObject.get(priceName);
        for (Object object: array) {
            JSONObject element = (JSONObject) object;
            Double from = Double.parseDouble(element.get("from").toString());
            Double to = Double.parseDouble(element.get("to").toString());
            Double value = Double.parseDouble(element.get("value").toString());
            prices.add(new Price(from, to, value));
        }
        return prices;
    }
}
