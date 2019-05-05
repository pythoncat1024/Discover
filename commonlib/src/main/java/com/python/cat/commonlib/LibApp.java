package com.python.cat.commonlib;

import android.app.Application;

public class LibApp extends Application {

    private static Application app;

    public static Application get() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
