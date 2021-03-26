package com.bingoplayer.app.utils;


import android.app.Application;

import com.bingoplayer.app.models.ModelBoardSession;
import com.google.gson.Gson;

import java.util.List;

public class MyApp extends Application {
    public static MyApp myApp;
    public static Shared shared;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        shared = new Shared(this);

    }

    public static MyApp getInstance() {
        return myApp;
    }
}
