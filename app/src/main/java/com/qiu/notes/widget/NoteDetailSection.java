package com.qiu.notes.widget;

import android.view.ViewGroup;

import com.qiu.base.lib.eventbus.EventDispatcher;
import com.qiu.base.lib.tools.UniqueId;
import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.base.lib.widget.recycler.ViewHolderFactory;
import com.qiu.notes.R;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.event.UpdateTextNoteEvent;
import com.qiu.notes.widget.base.TextNoteItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteDetailSection extends BaseRecyclerSection {

    private static final int ID_NOTE_DETAIL_ITEM = UniqueId.get();

    private static class NoteViewHolderFactory extends ViewHolderFactory {
        @Nullable
        @Override
        public BaseRecyclerViewHolder createViewHolder(@NonNull ViewGroup parent,
                int viewType) {
            if (viewType == ID_NOTE_DETAIL_ITEM) {
                return new NoteDetailItemViewHolder(
                        getLayoutById(parent, R.layout.item_note_detail));
            } else {
                return null;
            }
        }
    }

    @NonNull
    private NoteViewHolderFactory mNoteViewHolderFactory = new NoteViewHolderFactory();
    @NonNull
    private final TextContentEntry mEntry;

    public NoteDetailSection(@NonNull TextContentEntry entry) {
        mEntry = entry;
        prepareItems();
    }

    private void prepareItems() {
        mListEntry.add(new TextNoteItem(ID_NOTE_DETAIL_ITEM, mEntry));
    }

    @NonNull
    @Override
    protected ViewHolderFactory getViewHolderFactory() {
        return mNoteViewHolderFactory;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventDispatcher.post(new UpdateTextNoteEvent(mEntry));
    }
}
