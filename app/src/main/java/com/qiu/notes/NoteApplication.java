package com.qiu.notes;

import android.app.Application;

import com.qiu.base.lib.utils.App;

public class NoteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        App.i().init(this);
    }
}