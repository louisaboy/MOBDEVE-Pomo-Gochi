package com.mobdeve.s14.pomogochi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class StoreItemDAOSQLImpl implements StoreItemDAO{

    private SQLiteDatabase database;
    private StoreItemDatabase storeItemDatabase;

    public StoreItemDAOSQLImpl(Context context){
        storeItemDatabase = new StoreItemDatabase(context);
    }

    // Inserts a new store item with Image ID, Name, Price, and Owned Status into Database
    @Override
    public boolean addStoreItem(StoreItemModel storeItem) {
        this.database = this.storeItemDatabase.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(StoreItemDatabase.STORE_ITEM_IMAGE_ID, storeItem.getImageID());
        cv.put(StoreItemDatabase.STORE_ITEM_NAME, storeItem.getName());
        cv.put(StoreItemDatabase.STORE_ITEM_PRICE, storeItem.getStrPrice());
        cv.put(StoreItemDatabase.STORE_ITEM_OWNED, storeItem.getOwned());

        long result = this.database.insert(StoreItemDatabase.TABLE_STORE_ITEM, null, cv);

        return (result != -1);
    }

    // Updates a new store item's Owned Status into Database
    public boolean updateStoreItem(StoreItemModel storeItem) {
        this.database = this.storeItemDatabase.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(StoreItemDatabase.STORE_ITEM_OWNED, storeItem.getOwned());

        long result = database.update(StoreItemDatabase.TABLE_STORE_ITEM, cv, "STORE_ITEM_ID = ?", new String[]{Integer.toString(storeItem.getID())});

        return (result != -1);
    }

    // Returns an arraylist with the item's from the database
    @Override
    public ArrayList<StoreItemModel> getAllStoreItem() {
        this.database = this.storeItemDatabase.getReadableDatabase();

        String allQuery = "SELECT * FROM " + storeItemDatabase.TABLE_STORE_ITEM;

        Cursor c = this.database.rawQuery(allQuery,null);

        ArrayList<StoreItemModel> storeItemArrayList = new ArrayList<>();

        while(c.moveToNext()) {
            storeItemArrayList.add(new StoreItemModel(
                    c.getInt(c.getColumnIndexOrThrow(storeItemDatabase.STORE_ITEM_ID)),
                    c.getInt(c.getColumnIndexOrThrow(storeItemDatabase.STORE_ITEM_IMAGE_ID)),
                    c.getString(c.getColumnIndexOrThrow(storeItemDatabase.STORE_ITEM_NAME)),
                    c.getString(c.getColumnIndexOrThrow(storeItemDatabase.STORE_ITEM_PRICE)),
                    c.getInt(c.getColumnIndexOrThrow(storeItemDatabase.STORE_ITEM_OWNED)) > 0
            ));
        }

        return storeItemArrayList;
    }

    // Deletes the table from the Store Item Database
    @Override
    public boolean resetStoreItem() {
        this.database = this.storeItemDatabase.getWritableDatabase();

        long result = database.delete(storeItemDatabase.TABLE_STORE_ITEM, null, null);

        database.close();

        return (result != -1);
    }
}
