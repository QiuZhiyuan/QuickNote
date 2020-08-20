package com.qiu.notes.data;

import android.text.TextUtils;

import androidx.annotation.Nullable;

public class TextContentEntry {

    private final long mId;
    private final long mCreatedTime;
    private long mUpdateTime;
    private boolean mIsChanged;

    @Nullable
    private String mNote;

    public TextContentEntry(long id, long createdTime, long updateTime,
            @Nullable String note) {
        this.mId = id;
        mCreatedTime = createdTime;
        mUpdateTime = updateTime;
        mIsChanged = false;
        mNote = note;
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

    public void setNote(@Nullable String note) {
        mIsChanged = !TextUtils.equals(note, mNote);
        if (mIsChanged) {
            mNote = note;
        }
    }

    @Nullable
    public String getNote() {
        return mNote;
    }

    public boolean isChanged() {
        return mIsChanged;
    }

    public void save() {
        InternalDataProvider.i().getNoteDataHolder().update(this);
        mIsChanged = false;
    }

    public void delete() {
        InternalDataProvider.i().getNoteDataHolder().delete(this);
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(mNote);
    }
}
