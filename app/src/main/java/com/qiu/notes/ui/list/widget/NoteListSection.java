package com.qiu.notes.ui.list.widget;

import android.view.View;
import android.view.ViewGroup;

import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.base.lib.widget.recycler.ViewHolderFactory;
import com.qiu.notes.R;
import com.qiu.notes.data.InternalDataProvider;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.ui.base.widget.TextNoteItem;

import java.util.Iterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteListSection extends BaseRecyclerSection {

    private static class NoteListViewHolderFactory extends ViewHolderFactory {

        @Nullable
        @Override
        public BaseRecyclerViewHolder createViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View view = getLayoutById(parent, R.layout.note_list_item);
            return new NoteListItemViewHolder(view);
        }
    }

    public NoteListSection() {
        prepareItems();
    }

    private void prepareItems() {
        Iterator<TextContentEntry> iterator = InternalDataProvider.i().getNoteDataHolder()
                .getNoteList().getIterator();
        while (iterator.hasNext()) {
            mListEntry.add(new TextNoteItem(iterator.next()));
        }
    }

    @NonNull
    private final NoteListViewHolderFactory mNoteListViewHolderFactory =
            new NoteListViewHolderFactory();

    @NonNull
    @Override
    protected ViewHolderFactory getViewHolderFactory() {
        return mNoteListViewHolderFactory;
    }
}
