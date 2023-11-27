package com.example.lab_currency;



import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Parser {
    public ArrayList<Currency> parseCurrencyData(String url) {
        ArrayList<Currency> currencies = new ArrayList<>();

        try {
            String jsonString = readUrl(url);
            JSONObject json = new JSONObject(jsonString);
            JSONObject data = json.getJSONObject("data");


            Iterator<String> keys = data.keys();
            while (keys.hasNext()) {
                String currencyCode = keys.next();
                JSONObject currencyData = data.getJSONObject(currencyCode);
                double exchangeRate = currencyData.getDouble("value");
                currencies.add(new Currency(currencyCode, exchangeRate));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return currencies;
    }

    private String readUrl(String urlString) throws IOException {
        try (Scanner scanner = new Scanner(new URL(urlString).openStream(),
                StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
