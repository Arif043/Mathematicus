package com.github.arif043.mathematicus;

import android.app.Application;
import android.content.Context;

import java.io.File;

public class ApplicationManager extends Application {

    private static ApplicationManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        manager = this;
    }

    public static float dp(int px) {
        return manager.getResources().getDisplayMetrics().density * px;
    }

    public static File getFiles() {
        return manager.getFilesDir();
    }

    public static Context getContext() {
        return manager.getApplicationContext();
    }
}