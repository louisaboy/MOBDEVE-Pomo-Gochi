package com.mobdeve.s14.pomogochi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreItemDatabase extends SQLiteOpenHelper {

    // DB information
    private static final String DATABASE_NAME = "store_items.db";
    private static final int DATABASE_VERSION = 1;

    // Column names
    public static final String TABLE_STORE_ITEM = "TABLE_STORE_ITEM";
    public static final String STORE_ITEM_ID = "STORE_ITEM_ID";
    public static final String STORE_ITEM_IMAGE_ID = "STORE_ITEM_IMAGE_ID";
    public static final String STORE_ITEM_NAME = "STORE_ITEM_NAME";
    public static final String STORE_ITEM_PRICE = "STORE_ITEM_PRICE";
    public static final String STORE_ITEM_OWNED = "STORE_ITEM_OWNED";

    // Table information
    private static final String CREATE_STORE_ITEM_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_STORE_ITEM + " (" +
                    STORE_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    STORE_ITEM_IMAGE_ID + " INTEGER, " +
                    STORE_ITEM_NAME + " TEXT, " +
                    STORE_ITEM_PRICE + " TEXT, " +
                    STORE_ITEM_OWNED + " BIT)";

    private static final String DROP_STORE_ITEM_TABLE = "DROP TABLE IF EXISTS '" + TABLE_STORE_ITEM + "'";

    public StoreItemDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STORE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_STORE_ITEM_TABLE);
    }
}
