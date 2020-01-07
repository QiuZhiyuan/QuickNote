package com.qiu.notes.data;

import androidx.annotation.Nullable;

public class TextContentEntry {

    private long id;

    private long mCreatedTime;

    private long mUpdateTime;

    @Nullable
    private String mNote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(long createdTime) {
        mCreatedTime = createdTime;
    }

    public long getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(long updateTime) {
        mUpdateTime = updateTime;
    }

    public void setNote(@Nullable String note) {
        mNote = note;
    }

    @Nullable
    public String getNote() {
        return mNote;
    }

    public void save() {

    }
}
