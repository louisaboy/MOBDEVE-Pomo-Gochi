package com.mobdeve.s14.pomogochi;

import java.util.ArrayList;
import java.util.Collections;
import android.content.Context;

public class StoreItemDataHelper {

    public void initializeData(StoreItemDAO storeItemDAO) {

        String[] itemNames = {"pet1", "pet2", "pet3", "pet4"};
        int[] itemImages = {R.drawable.pet2, R.drawable.pet3, R.drawable.pet4, R.drawable.pet5};

        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[0],
                itemNames[0],
                "10K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[1],
                itemNames[1],
                "10K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[2],
                itemNames[2],
                "10K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[3],
                itemNames[3],
                "10K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[1],
                itemNames[0],
                "25K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[2],
                itemNames[1],
                "25K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[3],
                itemNames[2],
                "25K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[0],
                itemNames[1],
                "25K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[1],
                itemNames[2],
                "25K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[2],
                itemNames[3],
                "50K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[3],
                itemNames[0],
                "50K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[0],
                itemNames[1],
                "50K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[1],
                itemNames[2],
                "50K"));
//        Collections.shuffle(data);
    }
}
