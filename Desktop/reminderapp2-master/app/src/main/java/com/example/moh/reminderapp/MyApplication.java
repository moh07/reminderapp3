package com.example.moh.reminderapp;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by sellemi on 11/7/2016.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the SDK before executing any other operations,
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);


    }


}
