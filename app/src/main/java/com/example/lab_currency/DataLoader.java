package com.example.lab_currency;


import android.os.AsyncTask;

import java.util.ArrayList;

public class DataLoader extends AsyncTask<Void, Void, ArrayList<Currency>> {

    private final DataLoadListener listener;

    public DataLoader(DataLoadListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<Currency> doInBackground(Void... voids) {
        Parser parser = new Parser();
        return parser.parseCurrencyData("https://api.currencyapi.com/v3/latest?apikey=cur_live_ZrmD1Bf0cMjanQR9thgGbbgfTRbASgna9Z5xZ5wY");
    }

    @Override
    protected void onPostExecute(ArrayList<Currency> currencies) {
        super.onPostExecute(currencies);
        if (listener != null) {
            listener.onDataLoaded(currencies);
        }
    }
}
