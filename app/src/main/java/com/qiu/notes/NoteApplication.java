package com.qiu.notes;

import android.app.Application;

import com.qiu.notes.data.NoteDatabaseImpl;

public class NoteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NoteDatabaseImpl.i().init(this);
    }
}
