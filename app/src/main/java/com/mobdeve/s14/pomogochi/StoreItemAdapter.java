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
    private StoreItemDAO storeItemDAO;

    public StoreItemAdapter(ArrayList<StoreItemModel> dataStoreItems, StoreItemDAO storeItemDAO) {
        this.dataStoreItems = dataStoreItems;
        this.storeItemDAO = storeItemDAO;
    }

    @NonNull
    @NotNull
    @Override
    public StoreItemViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.store_item_template, parent, false);

        StoreItemViewHolder viewHolder = new StoreItemViewHolder(view);

        viewHolder.setIvItemBtn(v -> {

            // TODO Check if user has enough money

            StoreItemModel storeItem = dataStoreItems.get(viewHolder.getBindingAdapterPosition());

            if(!storeItem.getOwned()) {
                storeItem.setOwned(false);
            } else {
                storeItem.setOwned(true);
            }

            storeItemDAO.updateStoreItem(storeItem);

            // TODO Check if data in store is actually updated
            // TODO notifyDataSetChanged might not work since it's in onCreate might need to refresh activity

            this.dataStoreItems.clear();
            this.dataStoreItems.addAll(storeItemDAO.getAllStoreItem());
            notifyDataSetChanged();
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull StoreItemViewHolder holder, int position) {
        StoreItemModel storeItem = this.dataStoreItems.get(position);

        holder.bindData(storeItem);
    }

    @Override
    public int getItemCount() {
        return dataStoreItems.size();
    }
}
