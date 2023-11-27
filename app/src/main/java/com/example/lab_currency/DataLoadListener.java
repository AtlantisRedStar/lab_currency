package com.example.lab_currency;

import java.util.ArrayList;

public interface DataLoadListener {
    void onDataLoaded(ArrayList<Currency> currencies);
    void onError(String errorMessage);
}
