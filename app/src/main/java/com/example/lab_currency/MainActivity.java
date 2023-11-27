package com.example.lab_currency;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataLoadListener {

    private EditText searchEditText;
    private RecyclerView currencyRecyclerView;
    private CurrencyAdapter currencyAdapter;
    private DataLoader dataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        currencyRecyclerView = findViewById(R.id.currencyRecyclerView);

        currencyAdapter = new CurrencyAdapter(new ArrayList<>());
        currencyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        currencyRecyclerView.setAdapter(currencyAdapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                if (currencyAdapter != null) {
                    currencyAdapter.getFilter().filter(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        dataLoader = new DataLoader(this);
        dataLoader.execute();
    }

    @Override
    public void onDataLoaded(ArrayList<Currency> currencies) {
        if (currencyAdapter != null) {
            currencyAdapter.getCurrencyList().clear();
            currencyAdapter.getCurrencyList().addAll(currencies);
            currencyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(String errorMessage) {

    }
}
