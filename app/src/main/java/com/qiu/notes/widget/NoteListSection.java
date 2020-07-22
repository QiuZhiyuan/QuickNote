package com.qiu.notes.widget;

import android.view.View;
import android.view.ViewGroup;

import com.qiu.base.lib.eventbus.EventDispatcher;
import com.qiu.base.lib.tools.UniqueId;
import com.qiu.base.lib.widget.recycler.BaseRecyclerItem;
import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.base.lib.widget.recycler.ViewHolderFactory;
import com.qiu.notes.R;
import com.qiu.notes.data.InternalDataProvider;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.event.RefreshNoteEvent;
import com.qiu.notes.widget.base.TextNoteItem;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteListSection extends BaseRecyclerSection {

    private static final int ID_NOTE_DETAIL_ITEM = UniqueId.get();

    private static class NoteListViewHolderFactory extends ViewHolderFactory {

        @Nullable
        @Override
        public BaseRecyclerViewHolder createViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == ID_NOTE_DETAIL_ITEM) {
                final View view = getLayoutById(parent, R.layout.item_note_list);
                return new NoteListItemViewHolder(view);
            } else {
                return null;
            }
        }
    }

    private class EventHandler {
        @Subscribe
        public void refreshList(RefreshNoteEvent event) {
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
        setState(State.LOADING);
        InternalDataProvider.i().getNoteDataHolder().queryAll(this::prepareItems);
    }

    private void prepareItems(@NonNull List<TextContentEntry> entryList) {
        List<BaseRecyclerItem> itemList = new ArrayList<>();
        for (TextContentEntry entry : entryList) {
            itemList.add(new TextNoteItem(ID_NOTE_DETAIL_ITEM, entry));
        }
        mListEntry.clear();
        mListEntry.addAll(itemList);
        setState(State.FINISH);
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
