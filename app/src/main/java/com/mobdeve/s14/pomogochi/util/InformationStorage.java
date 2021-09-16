package com.mobdeve.s14.pomogochi.util;

import android.content.Context;
import android.content.SharedPreferences;

public class InformationStorage {
    private SharedPreferences sharedPreferences;
    public final String FIRST = "FIRST";
    public final String CURRENCY = "CURRENCY";

    public InformationStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(CURRENCY, Context.MODE_PRIVATE);
    }

    public void setCurrency(String key, int value) {
        SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();
        preferenceEditor.putInt(key, value);
        preferenceEditor.commit();
    }

    public int getCurrency(String key){
        return (sharedPreferences.getInt(key, 100000));
    }

    public void setFirst(String key, boolean value) {
        SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();
        preferenceEditor.putBoolean(key, value);
        preferenceEditor.commit();
    }

    public boolean getFirst(String key){
        return (sharedPreferences.getBoolean(key, true));
    }

}
