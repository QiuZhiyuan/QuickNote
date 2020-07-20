package com.qiu.notes.data;

import android.database.sqlite.SQLiteDatabase;

import com.qiu.base.lib.data.ListEntry;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class NoteDataHolder {

    private interface NoteDataChangeListener {
        public void onInsert(@NonNull TextContentEntry entry);

        public void onDelete(@NonNull TextContentEntry entry);

        public void onUpdate(@NonNull TextContentEntry entry);
    }

    @NonNull
    private ListEntry<TextContentEntry> mNoteListEntry = new ListEntry<>();
    @NonNull
    private List<NoteDataChangeListener> mListenerList = new ArrayList<>();

    @NonNull
    public ListEntry<TextContentEntry> getNoteList() {
        return mNoteListEntry;
    }

    public void updateAll() {
        mNoteListEntry.clear();
        mNoteListEntry.addAll(NoteDatabaseImpl.i().queryAll());
    }

    public void insert(@NonNull TextContentEntry entry) {
        NoteDatabaseImpl.i().insert(entry.getId(), entry.getCreatedTime(), entry.getUpdateTime(),
                entry.getNote());
    }

    public void delete(@NonNull TextContentEntry entry) {

    }
}
