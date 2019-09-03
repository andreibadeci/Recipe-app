package com.example.licenta.Project;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.licenta.Utils.Constants;

import java.util.List;


public class ApplicationData {

    public static void setStringValueInSharedPreferences(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.APP_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringValueFromSharedPreferences(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.APP_KEY, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public static void deleteValueFromSharedPreferences(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.APP_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

}
