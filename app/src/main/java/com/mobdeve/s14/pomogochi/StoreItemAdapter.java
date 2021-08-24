package com.mobdeve.s11.sibug.jordan.exercise2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StoreItemAdapter extends RecyclerView.Adapter<StoreItemViewHolder> {

    private ArrayList<StoreItem> dataStoreItems;

    public StoreItemAdapter(ArrayList<StoreItem> dataStoreItems) {
        this.dataStoreItems = dataStoreItems;
    }

    @NonNull
    @NotNull
    @Override
    public StoreItemViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.store_item_template, parent, false);

        StoreItemViewHolder viewHolder = new StoreItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull StoreItemViewHolder holder, int position) {

        StoreItem storeItem = this.dataStoreItems.get(position);

        holder.setIvItemImage(storeItem.getImageId());
        holder.setTvItemName(storeItem.getName());
        holder.setTvItemPrice(storeItem.getPrice());
        holder.setTvItemStatus(storeItem.getStatus());
    }

    @Override
    public int getItemCount() {
        return dataStoreItems.size();
    }
}
