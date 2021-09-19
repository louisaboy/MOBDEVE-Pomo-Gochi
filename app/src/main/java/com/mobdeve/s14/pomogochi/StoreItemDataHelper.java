package com.mobdeve.s14.pomogochi;

import java.util.ArrayList;
import java.util.Collections;
import android.content.Context;

public class StoreItemDataHelper {

    // Initializes all of the store items for the user to view and purchase
    public void initializeData(StoreItemDAO storeItemDAO) {

        String[] itemNames = {
                "Heart",
                "CCS",
                "Sushi",
                "Unicorn",
                "Potter",
                "Ukulele",
                "Sad",
                "Classic",
                "French Fry",
                "Slurp",
                "Sleep",
                "Omnom",
                "Family"
        };
        int[] itemImages = {
                R.drawable.pet1,
                R.drawable.pet2,
                R.drawable.pet3,
                R.drawable.pet4,
                R.drawable.pet5,
                R.drawable.pet6,
                R.drawable.pet7,
                R.drawable.pet8,
                R.drawable.pet9,
                R.drawable.pet10,
                R.drawable.pet11,
                R.drawable.pet12,
                R.drawable.pet13
        };

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
                itemImages[4],
                itemNames[4],
                "25K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[5],
                itemNames[5],
                "25K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[6],
                itemNames[6],
                "25K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[7],
                itemNames[7],
                "25K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[8],
                itemNames[8],
                "25K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[9],
                itemNames[9],
                "50K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[10],
                itemNames[10],
                "50K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[11],
                itemNames[11],
                "50K"));
        storeItemDAO.addStoreItem(new StoreItemModel(
                itemImages[12],
                itemNames[12],
                "50K"));
//        Collections.shuffle(data);
    }
}
