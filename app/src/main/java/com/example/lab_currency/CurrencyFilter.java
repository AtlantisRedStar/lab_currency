package com.example.lab_currency;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class CurrencyFilter extends Filter {

    private final CurrencyAdapter adapter;
    private final List<Currency> originalList;
    private final List<Currency> filteredList;

    public CurrencyFilter(CurrencyAdapter adapter, List<Currency> originalList) {
        this.adapter = adapter;
        this.originalList = new ArrayList<>(originalList);
        this.filteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();

        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            String filterPattern = constraint.toString().toLowerCase().trim();

            for (Currency currency : originalList) {
                if (currency.getCurrencyCode().toLowerCase().contains(filterPattern)) {
                    filteredList.add(currency);
                }
            }
        }

        FilterResults results = new FilterResults();
        results.values = filteredList;
        results.count = filteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.getCurrencyList().clear();
        adapter.getCurrencyList().addAll((List<Currency>) results.values);
        adapter.notifyDataSetChanged();
    }
}
