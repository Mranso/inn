package com.inn.inn.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.inn.inn.common.InnApplication;

public class SharedPreferencesUtils {

    private static final String SP_KEY_MONEY_LIST = "KEY_MONEY_LIST";

    private static final String SP_NAME = "save_money";

    private static SharedPreferencesUtils instance = new SharedPreferencesUtils();

    public SharedPreferencesUtils() {

    }

    private static synchronized void initSharedPreferencesUtils() {
        if (instance == null) {
            instance = new SharedPreferencesUtils();
        }
    }

    public static SharedPreferencesUtils getInstance() {
        if (instance == null) {
            initSharedPreferencesUtils();
        }
        return instance;
    }

    private SharedPreferences getSharedPreferences() {
        return InnApplication.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public String getMoneyString() {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences();
            if (sharedPreferences != null) {
                return sharedPreferences.getString(SP_KEY_MONEY_LIST, "9999");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "9999";
    }

    public void putMoneyString(String moneyString) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences();
            if (sharedPreferences != null) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(SP_KEY_MONEY_LIST, moneyString);
                editor.apply();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
