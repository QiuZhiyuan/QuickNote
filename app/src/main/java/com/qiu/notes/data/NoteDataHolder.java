package com.qiu.notes.data;

import android.content.ContentValues;
import android.text.TextUtils;

import com.qiu.base.lib.data.ListEntry;
import com.qiu.base.lib.impl.Callback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;

public class NoteDataHolder {

    public static final long CREATE_NEW_ENTRY_ID = -1;

    private interface NoteDataChangeListener {
        public void onInsert(@NonNull TextContentEntry entry);

        public void onDelete(@NonNull TextContentEntry entry);

        public void onUpdate(@NonNull TextContentEntry entry);
    }

    @NonNull
    private ListEntry<TextContentEntry> mNoteListEntry = new ListEntry<>();
    @NonNull
    private List<NoteDataChangeListener> mListenerList = new ArrayList<>();
    private Set<Long> mIdPool = new HashSet<>();

    @NonNull
    public ListEntry<TextContentEntry> getNoteList() {
        return mNoteListEntry;
    }

    public void queryAll(@NonNull Callback<List<TextContentEntry>> callback) {
        mNoteListEntry.clear();
        NoteDatabaseImpl.i().queryAll(entryList -> {
            mNoteListEntry.addAll(entryList);
            updateIdPool();
            callback.onCall(entryList);
        });
    }

    /**
     * find TextContentEntry by id in note list or create new one when can't find
     * @param noteId
     * @return
     */
    @NonNull
    public TextContentEntry findEntryById(long noteId) {
        if (noteId == CREATE_NEW_ENTRY_ID) {
            return createNewEntry();
        }
        Iterator<TextContentEntry> iterator = mNoteListEntry.getIterator();
        while (iterator.hasNext()) {
            final TextContentEntry entry = iterator.next();
            if (entry.getId() == noteId) {
                return entry;
            }
        }
        return createNewEntry();
    }

    private void updateIdPool() {
        mIdPool.clear();
        Iterator<TextContentEntry> iterator = mNoteListEntry.getIterator();
        while (iterator.hasNext()) {
            mIdPool.add(iterator.next().getId());
        }
    }

    private long createNewId() {
        long newId = 0;
        while (mIdPool.contains(newId)) {
            newId++;
        }
        mIdPool.add(newId);
        return newId;
    }

    private void insert(@NonNull TextContentEntry entry) {
        NoteDatabaseImpl.i().insert(entry.getId(), entry.getCreatedTime(), entry.getUpdateTime(),
                entry.getNote());
    }

    public void update(@NonNull TextContentEntry entry) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDatabaseImpl.DBEntry.CONTENT, entry.getNote());
        contentValues.put(NoteDatabaseImpl.DBEntry.UPDATE_TIME, entry.getUpdateTime());
        NoteDatabaseImpl.i().update(entry.getId(), contentValues);
    }

    public void delete(@NonNull TextContentEntry entry) {
        NoteDatabaseImpl.i().delete(entry.getId());
    }

    @NonNull
    private TextContentEntry createNewEntry() {
        final TextContentEntry entry = new TextContentEntry();
        entry.setId(createNewId());
        entry.setCreatedTime(System.currentTimeMillis());
        entry.setUpdateTime(System.currentTimeMillis());
        insert(entry);
        return entry;
    }

}
