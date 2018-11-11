package com.sequeniatestapp;

import android.app.Application;

import com.sequeniatestapp.model.services.Service;

import io.realm.Realm;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        Service.getInstance();
    }
}
