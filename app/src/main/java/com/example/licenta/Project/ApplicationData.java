package com.example.licenta.Project;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.licenta.Utils.Constants;

import java.util.List;


public class ApplicationData {

    public static void setIntValueInSharedPreferences(Context context, String key, int value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.APP_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getIntValueFromSharedPreferences(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.APP_KEY, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -1);
    }

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

    public static boolean checkIfKeyExists(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.APP_KEY, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(key)){
            return true;
        }
        else{
            return false;
        }
    }
}
