package com.e.zguide.repositories;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesRepository {
    public static final String SHARED_PREFERENCES_KEY = "SHARED_PREFERENCES";
    public static final String IS_VISITED = SHARED_PREFERENCES_KEY + "_IS_VISITED";


    private final SharedPreferences mPreferences;
    private static SharedPreferencesRepository instance;

    protected SharedPreferencesRepository(SharedPreferences preferences) {
        this.mPreferences = preferences;
    }

    public static SharedPreferencesRepository getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesRepository(context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE));
        }

        return instance;
    }

    public static SharedPreferencesRepository getInstance() {
        return instance;
    }

    public boolean getIsVisited() {
        return mPreferences.getBoolean(IS_VISITED, false);
    }

    public void setIsVisited(Boolean value) {
        mPreferences.edit().putBoolean(IS_VISITED, value).apply();
    }
}
