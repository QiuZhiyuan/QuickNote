package com.qiu.notes.data;

import com.qiu.base.lib.data.ListEntry;

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

    public void updateAll() {
        mNoteListEntry.clear();
        mNoteListEntry.addAll(NoteDatabaseImpl.i().queryAll());
        updateIdPool();
    }

    /**
     * find TextContentEntry by id in note list or create new one when can't find
     * @param noteId
     * @return
     */
    @NonNull
    public TextContentEntry findEntryById(long noteId) {
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

    public void insert(@NonNull TextContentEntry entry) {
        NoteDatabaseImpl.i().insert(entry.getId(), entry.getCreatedTime(), entry.getUpdateTime(),
                entry.getNote());
    }

    public void delete(@NonNull TextContentEntry entry) {

    }

    @NonNull
    public TextContentEntry createNewEntry() {
        final TextContentEntry entry = new TextContentEntry();
        entry.setId(createNewId());
        entry.setCreatedTime(System.currentTimeMillis());
        entry.setUpdateTime(System.currentTimeMillis());
        return entry;
    }

}
