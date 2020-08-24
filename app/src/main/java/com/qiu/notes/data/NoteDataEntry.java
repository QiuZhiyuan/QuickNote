package com.qiu.notes.data;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.qiu.base.lib.data.db.TableBaseEntry;
import com.qiu.base.lib.data.db.anno.Column;
import com.qiu.base.lib.data.db.anno.ColumnType;
import com.qiu.base.lib.data.db.anno.Table;

@Table(name = "NoteTable")
public class NoteDataEntry extends TableBaseEntry {

    @Column(name = "create_time", type = ColumnType.INTEGER)
    private long mCreateTime;
    @Column(name = "update_time", type = ColumnType.INTEGER)
    private long mUpdateTime;
    @Nullable
    @Column(name = "content", type = ColumnType.TEXT)
    private String mNote;
    @Nullable
    @Column(name = "title", type = ColumnType.INTEGER)
    private String mTitle;
    @Nullable
    private String mNoteCache;
    @Nullable
    private String mTitleCache;

    public NoteDataEntry() {
    }

    public long getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(long createTime) {
        mCreateTime = createTime;
    }

    public long getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(long updateTime) {
        mUpdateTime = updateTime;
    }

    @Nullable
    public String getNote() {
        return mNote;
    }

    public void setNote(@Nullable String note) {
        mNote = note;
        mNoteCache = mNote;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(@Nullable String title) {
        mTitle = title;
        mTitleCache = mTitle;
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


    public boolean isHasCache() {
        return !TextUtils.equals(mNote, mNoteCache) || !TextUtils.equals(mTitle, mTitleCache);
    }

    public void resetCache() {
        mNote = mNoteCache;
        mTitle = mTitleCache;
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
