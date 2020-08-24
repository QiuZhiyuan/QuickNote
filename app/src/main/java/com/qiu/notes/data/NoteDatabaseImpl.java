package com.qiu.notes.data;

import androidx.annotation.NonNull;

import com.qiu.base.lib.data.db.TableStorageImpl;

public class NoteDatabaseImpl extends TableStorageImpl<NoteContentEntry> {

    private static volatile NoteDatabaseImpl sInstance;

    protected NoteDatabaseImpl() {
        super(NoteContentEntry.class);
    }

    public static NoteDatabaseImpl instance() {
        if (sInstance == null) {
            synchronized (NoteDatabaseImpl.class) {
                if (sInstance == null) {
                    sInstance = new NoteDatabaseImpl();
                }
            }
        }
        return sInstance;
    }

    @NonNull
    @Override
    protected String getDataBaseName() {
        return "Quick_Note_DB.db";
    }

    @Override
    protected int getVersion() {
        return 1;
    }
}
