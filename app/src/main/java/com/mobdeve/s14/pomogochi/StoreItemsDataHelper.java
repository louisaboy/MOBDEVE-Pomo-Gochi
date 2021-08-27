package com.mobdeve.s14.pomogochi;

import java.util.ArrayList;
import java.util.Collections;

public class StoreItemsDataHelper {
    public ArrayList<StoreItem> initializeData() {
        String[] itemNames = {"pet1", "pet2", "pet3", "pet4"};
        int[] itemImages = {R.drawable.pet2, R.drawable.pet3, R.drawable.pet4, R.drawable.pet5};

        ArrayList<StoreItem> data = new ArrayList<>();
        data.add(new StoreItem(
                itemImages[0],
                itemNames[0],
                "1000",
                "Owned",
                false));
        data.add(new StoreItem(
                itemImages[1],
                itemNames[1],
                "500",
                "For Sale",
                false));
        data.add(new StoreItem(
                itemImages[2],
                itemNames[2],
                "1500",
                "Owned",
                false));
        data.add(new StoreItem(
                itemImages[3],
                itemNames[3],
                "100",
                "Owned",
                false));
        data.add(new StoreItem(
                itemImages[1],
                itemNames[0],
                "2000",
                "Owned",
                false));
        data.add(new StoreItem(
                itemImages[2],
                itemNames[1],
                "1000",
                "Owned",
                false));
        data.add(new StoreItem(
                itemImages[3],
                itemNames[2],
                "1000",
                "Owned",
                false));
        data.add(new StoreItem(
                itemImages[0],
                itemNames[1],
                "1000",
                "Owned",
                false));
        data.add(new StoreItem(
                itemImages[1],
                itemNames[2],
                "1000",
                "Owned",
                false));
        data.add(new StoreItem(
                itemImages[2],
                itemNames[3],
                "1000",
                "Owned",
                false));
        data.add(new StoreItem(
                itemImages[3],
                itemNames[0],
                "1000",
                "Owned",
                false));
        data.add(new StoreItem(
                itemImages[0],
                itemNames[1],
                "1000",
                "Owned",
                false));
        data.add(new StoreItem(
                itemImages[1],
                itemNames[2],
                "1000",
                "Owned",
                false));
        Collections.shuffle(data);

        return data;
    }
}
