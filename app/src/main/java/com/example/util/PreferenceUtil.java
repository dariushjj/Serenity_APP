package com.example.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {

    public static final String GUIDE = "guide";
    public static final String LOGIN = "login";
    public static boolean setBooleanPair(Context context, String key, boolean value, String fileType){
        SharedPreferences preferences = context.getSharedPreferences(fileType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean getBooleanValue(Context context, String key, String fileType){
        SharedPreferences preferences = context.getSharedPreferences(fileType, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    public static boolean removePair(Context context, String key, String fileType){
        SharedPreferences preferences = context.getSharedPreferences(fileType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        return editor.commit();
    }

    public static boolean clearPairs(Context context, String key, String fileType){
        SharedPreferences preferences = context.getSharedPreferences(fileType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        return editor.commit();
    }
}
