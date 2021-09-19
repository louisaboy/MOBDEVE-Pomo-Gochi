package com.mobdeve.s14.pomogochi;

import java.util.ArrayList;

public interface StoreItemDAO {

    // returns true if insert was successful
    // false otherwise
    boolean addStoreItem(StoreItemModel storeItem);

    // returns true if update was successful
    // false otherwise
    boolean updateStoreItem(StoreItemModel storeItem);

    // returns a all posts as an array list
    ArrayList<StoreItemModel> getAllStoreItem();

    // resets store items to their original states
    boolean resetStoreItem();
}
