package com.bingoplayer.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Shared {
    private static final String PREF_NAME = "BingoPlayer";
    private Context context;
    static SharedPreferences sharedPreferences;

    public Shared(Context context) {
        this.context = context;
        this.sharedPreferences = this.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void set(String key, String data) {
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString(key, data);
        myEdit.apply();
    }
    public String get(String key) {
        return sharedPreferences.getString(key, null);
    }


    public static void clearsession(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();

        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.remove("user");
        myEdit.apply();
    }

    /**
     * Put ArrayList of String into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param stringList ArrayList of String to be added
     */
    public static void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[0]);
        sharedPreferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }
    public static ArrayList<String> getListString(String key) {
        return new ArrayList<>(Arrays.asList(TextUtils.split(sharedPreferences.getString(key, ""), "‚‗‚")));
    }

    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     * @param key the pref key to check
     */
    private static void checkForNullKey(String key){
        if (key == null){
            throw new NullPointerException();
        }
    }
}
