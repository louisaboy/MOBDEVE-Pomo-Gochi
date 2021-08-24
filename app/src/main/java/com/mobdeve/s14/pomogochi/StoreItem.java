package com.mobdeve.s11.sibug.jordan.exercise2;

public class StoreItem {

    private int imageId;
    private String name, price, status;
    private boolean equipped;

    public StoreItem(int imageId, String name, String price, String status, boolean equipped) {
        this.imageId = imageId;
        this.price = price;
        this.name = name;
        this.status = status;
        this.equipped = false;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public boolean getEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }
}