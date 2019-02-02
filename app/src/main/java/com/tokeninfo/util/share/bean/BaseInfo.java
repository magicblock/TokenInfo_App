package com.tokeninfo.util.share.bean;

import android.content.Context;
import android.content.SharedPreferences;

import com.tokeninfo.base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

public class BaseInfo {

    private SharedPreferences preferences;
    private Map<String, Object> valueMap;

    public BaseInfo(String name) {
        valueMap = new HashMap<>();
        Context context = BaseApplication.getBaseApplication().getBaseContext();
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void putValue(String key, String value) {
        valueMap.put(key, value);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
        editor.commit();
    }

    public void putValue(String key, int value) {
        valueMap.put(key, value);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
        editor.commit();
    }

    public void putValue(String key, boolean value) {
        valueMap.put(key, value);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
        editor.commit();
    }

    public void putValue(String key, long value) {
        valueMap.put(key, value);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
        editor.commit();
    }

    public boolean isContains(String key) {
        boolean contains = false;
        if (valueMap.containsKey(key)) {
            contains = true;
        } else if (preferences.contains(key)) {
            contains = true;
        }

        return contains;
    }

    public void remove(String key) {
        valueMap.remove(key);

        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
        editor.commit();
    }

    public int getIntValue(String key) {
        int value = 0;
        if (valueMap.containsKey(key)) {
            value = (int) valueMap.get(key);
        } else {
            value = preferences.getInt(key, 0);
        }
        return value;
    }

    public long getLongValue(String key) {
        long value = 0;
        if (valueMap.containsKey(key)) {
            value = (int) valueMap.get(key);
        } else {
            value = preferences.getLong(key, 0);
        }
        return value;
    }

    public int getIntValue(String key, int def) {
        int value = def;
        if (valueMap.containsKey(key)) {
            value = (int) valueMap.get(key);
        } else {
            value = preferences.getInt(key, def);
        }
        return value;
    }

    public String getStringValue(String key) {
        String value = "";
        if (valueMap.containsKey(key)) {
            value = (String) valueMap.get(key);
        } else {
            value = preferences.getString(key, "");
        }
        return value;
    }

    public boolean getBoolean(String key) {
        boolean value = false;
        if (valueMap.containsKey(key)) {
            value = (boolean) valueMap.get(key);
        } else {
            value = preferences.getBoolean(key, false);
        }
        return value;
    }
}
