package com.qiu.notes.ui.base.widget;

import com.qiu.base.lib.tools.UniqueId;
import com.qiu.base.lib.widget.recycler.BaseRecyclerItem;
import com.qiu.notes.data.TextContentEntry;

import androidx.annotation.NonNull;

public class TextNoteItem extends BaseRecyclerItem {

    private static final int mId = UniqueId.get();

    @NonNull
    private final TextContentEntry mEntry;

    public TextNoteItem(@NonNull TextContentEntry entry) {
        mEntry = entry;
    }

    @Override
    public int getId() {
        return mId;
    }

    @NonNull
    public TextContentEntry getEntry() {
        return mEntry;
    }
}
