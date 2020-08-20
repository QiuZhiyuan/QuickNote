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
    @Nullable
    private String mTitle;
    @Nullable
    private String mTitleCache;

    public TextContentEntry(long id, long createdTime, long updateTime, @Nullable String title,
            @Nullable String note) {
        this.mId = id;
        mCreatedTime = createdTime;
        mUpdateTime = updateTime;
        mTitle = title;
        mNote = note;
        mNoteCache = mNote;
        mTitleCache = mTitle;
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

    @Nullable
    public String getTitleCache() {
        return mTitleCache;
    }

    public void setTitleCache(@Nullable String titleCache) {
        mTitleCache = titleCache;
    }

    public void setNoteCache(@Nullable String note) {
        mNoteCache = note;
    }

    @Nullable
    public String getNoteCache() {
        return mNoteCache;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getNote() {
        return mNote;
    }

    public boolean isChanged() {
        return !TextUtils.equals(mNote, mNoteCache) || !TextUtils.equals(mTitle, mTitleCache);
    }

    public void save() {
        mNote = mNoteCache;
        mTitle = mTitleCache;
        InternalDataProvider.i().getNoteDataHolder().update(this);
    }

    public void delete() {
        InternalDataProvider.i().getNoteDataHolder().delete(this);
    }

    public boolean isEmpty() {
        return isNoteEmpty() && isTitleEmpty();
    }

    private boolean isNoteEmpty() {
        return TextUtils.isEmpty(mNote) || TextUtils.isEmpty(mNoteCache);
    }

    private boolean isTitleEmpty() {
        return TextUtils.isEmpty(mTitle) || TextUtils.isEmpty(mTitleCache);
    }
}
