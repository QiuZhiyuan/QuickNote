package com.qiu.notes.ui.list.widget;

import android.view.View;
import android.view.ViewGroup;

import com.qiu.base.lib.eventbus.EventDispatcher;
import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.base.lib.widget.recycler.ViewHolderFactory;
import com.qiu.notes.R;
import com.qiu.notes.data.InternalDataProvider;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.ui.base.widget.TextNoteItem;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteListSection extends BaseRecyclerSection {

    public static class RefreshNoteListEvent {
    }

    private static class NoteListViewHolderFactory extends ViewHolderFactory {

        @Nullable
        @Override
        public BaseRecyclerViewHolder createViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View view = getLayoutById(parent, R.layout.note_list_item);
            return new NoteListItemViewHolder(view);
        }
    }

    private class EventHandler {
        @Subscribe
        public void refreshList(RefreshNoteListEvent event) {
            update();
        }
    }

    private EventHandler mEventHandler = new EventHandler();

    @Override
    public void onCreate() {
        super.onCreate();
        EventDispatcher.register(mEventHandler);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventDispatcher.unregister(mEventHandler);

    }

    public void update() {
        InternalDataProvider.i().getNoteDataHolder().queryAll(this::prepareItems);
    }

    private void prepareItems(@NonNull List<TextContentEntry> entryList) {
        mListEntry.clear();
        for (TextContentEntry entry : entryList) {
            mListEntry.add(new TextNoteItem(entry));
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
