package com.qiu.notes.data;

import com.qiu.base.lib.data.ListEntry;
import com.qiu.base.lib.impl.Callback;

import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;

public class NoteDataHelper {

    public static final long CREATE_NEW_ENTRY_ID = -1;

    @NonNull
    private ListEntry<NoteContentEntry> mNoteListEntry = new ListEntry<>();

    public NoteDataHelper() {
    }

    public void queryAll(@NonNull Callback<List<NoteContentEntry>> callback) {
        NoteDatabaseImpl.instance().queryAll(noteContentEntries -> {
            mNoteListEntry.clear();
            mNoteListEntry.addAll(noteContentEntries);
            callback.onCall(mNoteListEntry.getList());
        });
    }

    /**
     * find TextContentEntry by id in note list or create new one when can't find
     *
     * @param noteId
     * @return
     */
    @NonNull
    public NoteContentEntry findEntryById(long noteId) {
        if (noteId == CREATE_NEW_ENTRY_ID) {
            return createNewEntry();
        }
        Iterator<NoteContentEntry> iterator = mNoteListEntry.getIterator();
        while (iterator.hasNext()) {
            final NoteContentEntry entry = iterator.next();
            if (entry.getId() == noteId) {
                return entry;
            }
        }
        return createNewEntry();
    }

    @NonNull
    private NoteContentEntry createNewEntry() {
        final long createTime = System.currentTimeMillis();
        final long updateTime = System.currentTimeMillis();
        final NoteContentEntry entry = new NoteContentEntry();
        entry.setCreateTime(createTime);
        entry.setUpdateTime(updateTime);
        return entry;
    }

    public void save(@NonNull NoteContentEntry entry) {
        entry.resetCache();
        NoteDatabaseImpl.instance().insert(entry);
    }

    public void update(@NonNull NoteContentEntry entry) {
        entry.resetCache();
        NoteDatabaseImpl.instance().update(entry);
    }

    public void delete(@NonNull NoteContentEntry entry) {
        NoteDatabaseImpl.instance().delete(entry);
    }
}
