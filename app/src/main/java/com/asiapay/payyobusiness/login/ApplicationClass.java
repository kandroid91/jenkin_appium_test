package com.asiapay.payyobusiness.login;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.asiapay.payyobusiness.database.PayForDataSource;

public class ApplicationClass extends Application {
    private static ApplicationClass instance;
    public PayForDataSource payForDataSource;
    private SharedPreferences pref;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        pref = getSharedPreferences("PayYoForBusiness", MODE_PRIVATE);
        //open database
        payForDataSource = new PayForDataSource(this);
        payForDataSource.open();
    }

    public static ApplicationClass getInstance() {
        return instance;
    }

    public SharedPreferences getPreferences() {
        return pref;
    }
}
