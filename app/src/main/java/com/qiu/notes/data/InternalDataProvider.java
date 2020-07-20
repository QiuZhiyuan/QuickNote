package com.qiu.notes.data;

import androidx.annotation.NonNull;

public class InternalDataProvider {

    private static volatile InternalDataProvider sInstance;

    public static InternalDataProvider i() {
        if (sInstance == null) {
            synchronized (InternalDataProvider.class) {
                if (sInstance == null) {
                    sInstance = new InternalDataProvider();
                }
            }
        }
        return sInstance;
    }

    @NonNull
    private final NoteDataHolder mNoteDataHolder;

    @NonNull
    private final SettingDataHolder mSettingDataHolder;


    private InternalDataProvider() {
        mNoteDataHolder = new NoteDataHolder();
        mSettingDataHolder = new SettingDataHolder();
    }

    public NoteDataHolder getNoteDataHolder() {
        return mNoteDataHolder;
    }

    public SettingDataHolder getSettingDataHolder() {
        return mSettingDataHolder;
    }
}
