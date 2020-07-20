package com.qiu.notes.ui.list.widget;

import android.view.View;
import android.widget.TextView;

import com.qiu.base.lib.eventbus.EventDispatcher;
import com.qiu.base.lib.widget.recycler.BaseRecyclerItem;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.notes.R;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.event.ShowFragmentEvent;
import com.qiu.notes.ui.base.widget.TextNoteItem;
import com.qiu.notes.ui.edit.EditNoteFragment;

import androidx.annotation.NonNull;

public class NoteListItemViewHolder extends BaseRecyclerViewHolder {

    @NonNull
    private final TextView mNoteTime;
    @NonNull
    private final TextView mNoteContent;

    public NoteListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mNoteTime = itemView.findViewById(R.id.note_time);
        mNoteContent = itemView.findViewById(R.id.note_content);
    }

    @Override
    public void bindItem(@NonNull BaseRecyclerItem item) {
        if (item instanceof TextNoteItem) {
            final TextContentEntry entry = ((TextNoteItem) item).getEntry();
            mNoteTime.setText(entry.getUpdateTime() + "");
            mNoteContent.setText(entry.getNote());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventDispatcher.post(new ShowFragmentEvent(
                            EditNoteFragment.getInstance(entry.getId())));
                }
            });
        }

    }

    @Override
    public void unBindItem() {
        itemView.setOnClickListener(null);
    }
}
