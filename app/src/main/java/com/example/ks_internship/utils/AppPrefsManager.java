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

    private List<String> historyItems;

    private SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void cacheSearchHistory(Context context, String name) {
        historyItems = getCachedSearchHistory(context);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < historyItems.size(); i++) {
            stringBuilder.append(historyItems.get(i)).append(",");
        }
        stringBuilder.append(name).append(",");
        getPrefs(context).edit().putString(Constants.PREFS_SEARCH_HISTORY, stringBuilder.toString()).apply();
    }

    public List<String> getCachedSearchHistory(Context context) {
        String cache = getPrefs(context).getString(Constants.PREFS_SEARCH_HISTORY, null);
        if (cache != null) {
            historyItems = new ArrayList<>(Arrays.asList(cache.split(",")));
        } else {
            historyItems = new ArrayList<>();
        }
        return historyItems;
    }

    public void clearHistoryCache(Context context) {
        getPrefs(context).edit().remove(Constants.PREFS_SEARCH_HISTORY).apply();
    }
}
