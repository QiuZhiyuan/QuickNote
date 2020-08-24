package com.qiu.notes.widget.base;

import com.qiu.base.lib.widget.recycler.BaseRecyclerItem;
import com.qiu.notes.data.NoteContentEntry;

import androidx.annotation.NonNull;

public class TextNoteItem extends BaseRecyclerItem {

    private final int mId;

    @NonNull
    public final NoteContentEntry mEntry;

    public TextNoteItem(int id, @NonNull NoteContentEntry entry) {
        mId = id;
        mEntry = entry;
    }

    @Override
    public int getId() {
        return mId;
    }
}
