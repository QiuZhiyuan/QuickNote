package com.qiu.notes.widget;

import android.view.ViewGroup;

import com.qiu.base.lib.thread.ThreadUtils;
import com.qiu.base.lib.tools.UniqueId;
import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.base.lib.widget.recycler.ViewHolderFactory;
import com.qiu.notes.R;
import com.qiu.notes.data.TextContentEntry;
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
    private final Runnable mSaveNoteTask = new Runnable() {
        @Override
        public void run() {
            if (mItem.mEntry.isChanged()) {
                mItem.mEntry.setUpdateTime(System.currentTimeMillis());
                mItem.onDataUpdate();
                mItem.mEntry.save();
            }
        }
    };

    @NonNull
    private NoteViewHolderFactory mNoteViewHolderFactory = new NoteViewHolderFactory();
    @NonNull
    private final TextNoteItem mItem;

    public NoteDetailSection(@NonNull TextContentEntry entry) {
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
        ThreadUtils.i().postMain(mSaveNoteTask);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        ThreadUtils.i().postMain(mSaveNoteTask);
        super.onDestroy();
    }
}
