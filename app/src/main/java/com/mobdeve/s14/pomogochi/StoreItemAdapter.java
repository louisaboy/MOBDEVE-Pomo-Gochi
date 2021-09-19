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

            // Checks whether store item pressed is not owned
            if(!storeItem.getOwned()) {

                // Gets the amount of money the user has
                int curr = MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY);

                // Gets the price of the item they want to buy
                int price = storeItem.getIntPrice();

                // Checks if user has enough money to purchase the item
                if(curr >= price) {

                    // Sets store item to owned
                    storeItem.setOwned(true);

                    Log.d("curr", Integer.toString(curr));

                    // Subtracts the price of the item to the user's money
                    curr -= price;

                    // Sets the currency to the subtracted amount
                    MainActivity.informationStorage.setCurrency(MainActivity.informationStorage.CURRENCY, curr);

                    // Updates the currency counter
                    StoreItemActivity.tv_money.setText(String.valueOf(MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY)));

                    Log.d("curr", Integer.toString(curr));

                    // Sets the front end Buy item Btn to the Owned Status
                    viewHolder.setIvItemBtn(storeItem);
                } else {
                    // Informs user whether that they don't have enough money
                    Toast.makeText(parent.getContext(), "Not enough money!", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Informs user that they already own the item they're trying to buy
                Toast.makeText(parent.getContext(), "Already owned!", Toast.LENGTH_SHORT).show();
            }

            // Updates the item bought from the store in the database
            // Only changes the value of owned to true
            storeItemDAO.updateStoreItem(storeItem);

            // Refreshes the array list used for the recylcer view of the store item activity
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
