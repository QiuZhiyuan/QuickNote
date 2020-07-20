package com.qiu.notes.ui.edit.widget;

import android.view.ViewGroup;

import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.base.lib.widget.recycler.ViewHolderFactory;
import com.qiu.notes.R;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.ui.base.widget.TextNoteItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EditNoteSection extends BaseRecyclerSection {

    private static class NoteViewHolderFactory extends ViewHolderFactory {
        @Nullable
        @Override
        public BaseRecyclerViewHolder createViewHolder(@NonNull ViewGroup parent,
                int viewType) {

            return new EditTextNoteViewHolder(
                    getLayoutById(parent, R.layout.edit_text_note_item));
        }
    }

    @NonNull
    private NoteViewHolderFactory mNoteViewHolderFactory = new NoteViewHolderFactory();
    @NonNull
    private final TextContentEntry mEntry;

    public EditNoteSection(@NonNull TextContentEntry entry) {
        mEntry = entry;
        prepareItems();
    }

    private void prepareItems() {
        mListEntry.add(new TextNoteItem(mEntry));
    }

    @NonNull
    @Override
    protected ViewHolderFactory getViewHolderFactory() {
        return mNoteViewHolderFactory;
    }
}
