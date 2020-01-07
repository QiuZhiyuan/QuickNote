package com.qiu.notes.edit.widget;

import android.view.ViewGroup;

import com.qiu.base.lib.thread.ThreadUtils;
import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.base.lib.widget.recycler.ViewHolderFactory;
import com.qiu.notes.R;
import com.qiu.notes.data.NoteDatabaseImpl;
import com.qiu.notes.data.TextContentEntry;


import java.util.List;

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

    public EditNoteSection() {
        loadData();
    }

    private void loadData() {
        ThreadUtils.i().postTask(new Runnable() {
            @Override
            public void run() {
                final List<TextContentEntry> entryList = NoteDatabaseImpl.i().queryAll();
                ThreadUtils.i().postTask(new Runnable() {
                    @Override
                    public void run() {
                        prepareItems(entryList);
                    }
                }, 0, false);
            }
        }, 0, true, true);
    }

    private void prepareItems(@NonNull List<TextContentEntry> entryList) {
        for (TextContentEntry entry : entryList) {
            mListEntry.add(new EditTextNoteItem(entry));
        }
    }

    @NonNull
    @Override
    protected ViewHolderFactory getViewHolderFactory() {
        return mNoteViewHolderFactory;
    }
}
