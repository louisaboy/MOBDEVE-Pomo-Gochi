package com.mobdeve.s14.pomogochi;

import java.util.ArrayList;

public interface StoreItemDAO {

    //returns true is insert was successful
    //false if not successful
    boolean addStoreItem(StoreItemModel storeItem);

    boolean updateStoreItem(StoreItemModel storeItem);

    //returns a all posts
    ArrayList<StoreItemModel> getAllStoreItem();

    boolean resetStoreItem();
}
