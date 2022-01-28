package ru.dw.gbnotes.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesCounter {
    private static final String SHARED_PREFS_NAME = "counter";
    private static final String SHARED_PREFS_COUNT_KEY = "SHARED_PREFS_COUNT_KEY";

    private final SharedPreferences sharedPreferences;

    public SharedPreferencesCounter(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setCount(){
        Integer count = getCount();
        count++;
        sharedPreferences
                .edit()
                .putInt(SHARED_PREFS_COUNT_KEY,count)
                .apply();
    }

    public Integer getCount(){
        return sharedPreferences.getInt(SHARED_PREFS_COUNT_KEY,0);
    }
}
