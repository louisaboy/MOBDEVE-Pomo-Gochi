package com.mobdeve.s14.pomogochi;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StoreItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivItemImage, ivItemBtn;
    private TextView tvItemName, tvItemPrice;

    public StoreItemViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
        super(itemView);

        this.ivItemImage = itemView.findViewById(R.id.iv_item_img);
        this.tvItemName = itemView.findViewById(R.id.tv_item_name);
        this.tvItemPrice = itemView.findViewById(R.id.tv_item_price);
        this.ivItemBtn = itemView.findViewById(R.id.iv_item_btn);
    }

    public void setIvItemBtn(View.OnClickListener onClickListener) {
        this.ivItemBtn.setOnClickListener(onClickListener);
    }

    public void bindData(StoreItemModel storeItem) {
        this.ivItemImage.setImageResource(storeItem.getImageID());
        this.tvItemName.setText(storeItem.getName());
        this.tvItemPrice.setText(storeItem.getPrice());

        if(!storeItem.getOwned()) {
            this.ivItemBtn.setImageResource(R.drawable.btn_buy);
        } else {
            this.ivItemBtn.setImageResource(R.drawable.btn_owned);
        }
    }
}
