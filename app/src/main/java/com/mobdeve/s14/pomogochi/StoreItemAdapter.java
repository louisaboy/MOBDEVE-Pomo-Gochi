package com.mobdeve.s14.pomogochi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

        viewHolder.setIvItemBtnOnClickListener(v -> {


            StoreItemModel storeItem = dataStoreItems.get(viewHolder.getBindingAdapterPosition());

            if(!storeItem.getOwned()) {
                // TODO Check if user has enough money
                int curr = MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY);

                int price = storeItem.getIntPrice();

                if(curr > price) {
                    storeItem.setOwned(true);

                    Log.d("curr", Integer.toString(curr));

                    curr -= price;

                    MainActivity.informationStorage.setCurrency(MainActivity.informationStorage.CURRENCY, curr);

                    StoreItemActivity.tv_money.setText(String.valueOf(MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY)));

                    Log.d("curr", Integer.toString(curr));

                    viewHolder.setIvItemBtn(storeItem);
                } else {
                    // TODO Show Toast
                    Toast.makeText(parent.getContext(), "Not enough money!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(parent.getContext(), "Already owned!", Toast.LENGTH_SHORT).show();
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
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull StoreItemViewHolder viewHolder, int position) {
        StoreItemModel storeItem = this.dataStoreItems.get(position);

        viewHolder.bindData(storeItem);
    }

    @Override
    public int getItemCount() {
        return dataStoreItems.size();
    }

//    public void setData(ArrayList<StoreItemModel> data){
//        this.dataStoreItems.clear();
//        this.dataStoreItems.addAll(data);
//        notifyDataSetChanged();
//    }

    public void setTvMoney() {

    }
}
