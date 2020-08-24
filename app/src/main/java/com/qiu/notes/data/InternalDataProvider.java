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
    private final NoteDataHelper mNoteDataHolder;

    @NonNull
    private final SettingDataHolder mSettingDataHolder;


    private InternalDataProvider() {
        mNoteDataHolder = new NoteDataHelper();
        mSettingDataHolder = new SettingDataHolder();
    }

    public NoteDataHelper getNoteDataHelper() {
        return mNoteDataHolder;
    }

    public SettingDataHolder getSettingDataHolder() {
        return mSettingDataHolder;
    }
}
