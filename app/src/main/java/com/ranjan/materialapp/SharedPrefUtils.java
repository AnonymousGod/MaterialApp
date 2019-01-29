package com.ranjan.materialapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by BlueSapling on 1/29/19.
 */
public class SharedPrefUtils {

    public static void addString(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("material_app", Context.MODE_PRIVATE)
                .edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key) {
        return context.getSharedPreferences("material_app", Context.MODE_PRIVATE).getString(key, null);
    }
}
