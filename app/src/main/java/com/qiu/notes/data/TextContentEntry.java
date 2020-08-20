package com.qiu.notes.data;

import android.text.TextUtils;

import androidx.annotation.Nullable;

public class TextContentEntry {

    private final long mId;
    private final long mCreatedTime;
    private long mUpdateTime;
    @Nullable
    private String mNote;
    @Nullable
    private String mNoteCache;

    public TextContentEntry(long id, long createdTime, long updateTime,
            @Nullable String note) {
        this.mId = id;
        mCreatedTime = createdTime;
        mUpdateTime = updateTime;
        mNote = note;
        mNoteCache = mNote;
    }

    public long getId() {
        return mId;
    }

    public long getCreatedTime() {
        return mCreatedTime;
    }

    public long getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(long updateTime) {
        mUpdateTime = updateTime;
    }

    public void setNoteCache(@Nullable String note) {
        mNoteCache = note;
    }

    @Nullable
    public String getNoteCache() {
        return mNoteCache;
    }

    @Nullable
    public String getNote() {
        return mNote;
    }

    public boolean isChanged() {
        return !TextUtils.equals(mNote, mNoteCache);
    }

    public void save() {
        mNote = mNoteCache;
        InternalDataProvider.i().getNoteDataHolder().update(this);
    }

    public void delete() {
        InternalDataProvider.i().getNoteDataHolder().delete(this);
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(mNote) || TextUtils.isEmpty(mNoteCache);
    }
}
