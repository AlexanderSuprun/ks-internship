package com.example.ks_internship.utils;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages {@link SharedPreferences}
 */

public class AppPrefsManager {

    private Context context;
    private List<String> historyItems;

    public AppPrefsManager(Context context) {
        this.context = context;
    }

    private SharedPreferences getPrefs() {
        return context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void cacheSearchHistory(String name) {
        historyItems = getCachedSearchHistory();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < historyItems.size(); i++) {
            stringBuilder.append(historyItems.get(i)).append(",");
        }
        stringBuilder.append(name).append(",");
        getPrefs().edit().putString(Constants.PREFS_SEARCH_HISTORY, stringBuilder.toString()).apply();
    }

    public List<String> getCachedSearchHistory() {
        String cache = getPrefs().getString(Constants.PREFS_SEARCH_HISTORY, null);
        if (cache != null) {
            historyItems = new ArrayList<>(Arrays.asList(cache.split(",")));
        } else {
            historyItems = new ArrayList<>();
        }
        return historyItems;
    }

    public void clearHistoryCache() {
        getPrefs().edit().remove(Constants.PREFS_SEARCH_HISTORY).apply();
    }
}
