package com.mobdeve.s14.pomogochi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StoreItemAdapter extends RecyclerView.Adapter<StoreItemViewHolder> {

    private ArrayList<StoreItemModel> dataStoreItems;

    public StoreItemAdapter(ArrayList<StoreItemModel> dataStoreItems) {
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

        StoreItemModel storeItem = this.dataStoreItems.get(position);

        holder.setIvItemImage(storeItem.getImageID());
        holder.setTvItemName(storeItem.getName());
        holder.setTvItemPrice(storeItem.getPrice());
//        holder.setTvItemStatus(storeItem.getStatus());
    }

    @Override
    public int getItemCount() {
        return dataStoreItems.size();
    }
}
