package com.mobdeve.s11.sibug.jordan.exercise2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StoreItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivItemImage;
    private TextView tvItemName, tvItemPrice, tvItemStatus;

    public StoreItemViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
        super(itemView);

        this.ivItemImage = itemView.findViewById(R.id.iv_item_img);
        this.tvItemName = itemView.findViewById(R.id.tv_item_name);
        this.tvItemPrice = itemView.findViewById(R.id.tv_item_price);
        this.tvItemStatus = itemView.findViewById(R.id.tv_item_status);
    }

//    public TextView getTvItemName() {
//        return tvItemName;
//    }
//
//    public TextView getTvItemPrice() {
//        return tvItemPrice;
//    }
//
//    public TextView getTvItemStatus() {
//        return tvItemStatus;
//    }

    public void setIvItemImage(int data) {
        this.ivItemImage.setImageResource(data);
    }

    public void setTvItemName(String data) {
        this.tvItemName.setText(data);
    }

    public void setTvItemPrice(String data) {
        this.tvItemPrice.setText(data);
    }

    public void setTvItemStatus(String data) {
        this.tvItemStatus.setText(data);
    }
}
