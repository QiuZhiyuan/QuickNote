package com.qiu.notes.widget;

import android.view.ViewGroup;

import com.qiu.base.lib.tools.UniqueId;
import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.base.lib.widget.recycler.ViewHolderFactory;
import com.qiu.notes.R;
import com.qiu.notes.data.InternalDataProvider;
import com.qiu.notes.data.NoteDataEntry;
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
    private final TextNoteItem mItem;

    public NoteDetailSection(@NonNull NoteDataEntry entry) {
        mItem = new TextNoteItem(ID_NOTE_DETAIL_ITEM, entry);
        prepareItems();
    }

    private void prepareItems() {
        mListEntry.add(mItem);
    }

    @NonNull
    @Override
    protected ViewHolderFactory getViewHolderFactory() {
        return mNoteViewHolderFactory;
    }

    @Override
    public void onPause() {
        updateData();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        updateData();
        super.onDestroy();
    }

    private void updateData() {
        if (mItem.mEntry.isHasCache()) {
            mItem.mEntry.setUpdateTime(System.currentTimeMillis());
            mItem.onDataUpdate();
            InternalDataProvider.i().getNoteDataHelper().update(mItem.mEntry);
        }
    }
}
