package com.qiu.notes.data;

import com.qiu.base.lib.data.ListEntry;
import com.qiu.base.lib.impl.Callback;

import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;

public class NoteDataHelper {

    public static final long CREATE_NEW_ENTRY_ID = -1;

    @NonNull
    private ListEntry<NoteDataEntry> mNoteListEntry = new ListEntry<>();

    public NoteDataHelper() {
    }

    public void queryAll(@NonNull Callback<List<NoteDataEntry>> callback) {
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
    public NoteDataEntry findEntryById(long noteId) {
        if (noteId == CREATE_NEW_ENTRY_ID) {
            return createNewEntry();
        }
        Iterator<NoteDataEntry> iterator = mNoteListEntry.getIterator();
        while (iterator.hasNext()) {
            final NoteDataEntry entry = iterator.next();
            if (entry.getId() == noteId) {
                return entry;
            }
        }
        return createNewEntry();
    }

    @NonNull
    private NoteDataEntry createNewEntry() {
        final long createTime = System.currentTimeMillis();
        final long updateTime = System.currentTimeMillis();
        final NoteDataEntry entry = new NoteDataEntry();
        entry.setCreateTime(createTime);
        entry.setUpdateTime(updateTime);
        save(entry);
        return entry;
    }

    public void save(@NonNull NoteDataEntry entry) {
        entry.resetCache();
        NoteDatabaseImpl.instance().insert(entry);
    }

    public void update(@NonNull NoteDataEntry entry) {
        entry.resetCache();
        NoteDatabaseImpl.instance().update(entry);
    }

    public void delete(@NonNull NoteDataEntry entry) {
        NoteDatabaseImpl.instance().delete(entry);
    }
}
