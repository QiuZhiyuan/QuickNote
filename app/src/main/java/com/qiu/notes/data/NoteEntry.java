package com.qiu.notes.data;

import com.qiu.base.lib.data.ListEntry;

import java.util.Iterator;

import androidx.annotation.NonNull;

public class NoteEntry {
    private long mCreatedTime;

    private long mUpdateTime;

    private ListEntry<BaseContentEntry> mContentEntryList = new ListEntry<>();

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

    public void addEntry(@NonNull BaseContentEntry entry) {
        mContentEntryList.add(entry);
    }

    public void removeEntry(@NonNull BaseContentEntry entry) {
        mContentEntryList.remove(entry);
    }

    @NonNull
    public Iterator<BaseContentEntry> getIterator() {
        return mContentEntryList.getIterator();
    }
}
