package com.example.lab_currency;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> implements Filterable {

    private ArrayList<Currency> currencyList;
    private ArrayList<Currency> filteredList;

    public CurrencyAdapter(ArrayList<Currency> currencyList) {
        this.currencyList = currencyList;
        this.filteredList = new ArrayList<>(currencyList);
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder viewHolder, int position) {
        Currency currency = filteredList.get(position);
        viewHolder.currencyNameTextView.setText(currency.getCurrencyCode());
        viewHolder.currencyRateTextView.setText(String.valueOf(currency.getExchangeRate()));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterPattern = constraint.toString().toLowerCase().trim();
                filteredList.clear();

                if (filterPattern.isEmpty()) {
                    filteredList.addAll(currencyList);
                } else {
                    for (Currency currency : currencyList) {
                        if (currency.getCurrencyName().toLowerCase().contains(filterPattern)) {
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
                notifyDataSetChanged();
            }
        };
    }

    public static class CurrencyViewHolder extends RecyclerView.ViewHolder {
        TextView currencyNameTextView;
        TextView currencyRateTextView;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyNameTextView = itemView.findViewById(R.id.currencyNameTextView);
            currencyRateTextView = itemView.findViewById(R.id.currencyRateTextView);
        }
    }
}

