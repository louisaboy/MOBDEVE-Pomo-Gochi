package com.mobdeve.s14.pomogochi;

public class StoreItemModel {

    private int ID;
    private int imageID;
    private String name, price;
    private boolean owned;

    public StoreItemModel(int imageID, String name, String price) {
        this.imageID = imageID;
        this.name = name;
        this.price = price;
        this.owned = false;
    }

    public StoreItemModel(int ID, int imageID, String name, String price, boolean owned) {
        this.ID = ID;
        this.imageID = imageID;
        this.name = name;
        this.price = price;
        this.owned = owned;
    }

    public int getID() {
        return ID;
    }

    public int getImageID() {
        return imageID;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public boolean getOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }
}