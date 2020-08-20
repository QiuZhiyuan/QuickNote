package com.qiu.notes.widget;

import android.view.View;
import android.widget.TextView;

import com.qiu.base.lib.eventbus.EventDispatcher;
import com.qiu.base.lib.widget.recycler.BaseRecyclerItem;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.notes.R;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.event.RefreshNoteEvent;
import com.qiu.notes.event.ShowFragmentEvent;
import com.qiu.notes.widget.base.TextNoteItem;
import com.qiu.notes.page.NoteDetailFragment;
import com.qiu.notes.utils.Tools;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class NoteListItemViewHolder extends BaseRecyclerViewHolder implements View.OnClickListener,
        View.OnLongClickListener {

    @NonNull
    private final TextView mNoteTime;
    @NonNull
    private final TextView mNoteTitle;
    @NonNull
    private final TextView mNoteContent;
    @Nullable
    private TextContentEntry mEntry;

    public NoteListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mNoteTime = itemView.findViewById(R.id.update_time);
        mNoteContent = itemView.findViewById(R.id.note_content);
        mNoteTitle = itemView.findViewById(R.id.note_title);
    }

    @Override
    public void bindItem(@NonNull BaseRecyclerItem item) {
        if (item instanceof TextNoteItem) {
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            mEntry = ((TextNoteItem) item).mEntry;
            mNoteTime.setText(Tools.getDateString(mEntry.getUpdateTime()));
            mNoteTitle.setText(mEntry.getTitle());
            mNoteContent.setText(mEntry.getNote());
        }

    }

    @Override
    public void unBindItem() {
        itemView.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        if (mEntry != null) {
            EventDispatcher.post(new ShowFragmentEvent(
                    NoteDetailFragment.getInstance(mEntry.getId())));
        }
    }

    @Override
    public boolean onLongClick(View v) {
        showDeleteDialog();
        return false;
    }

    private void showDeleteDialog() {
        new AlertDialog.Builder(itemView.getContext()).setMessage("是否删除?").setNegativeButton("否",
                (dialog, which) -> dialog.dismiss()).setPositiveButton("是", (dialog, which) -> {
            if (mEntry != null) {
                mEntry.delete();
                EventDispatcher.post(new RefreshNoteEvent());
            }
            dialog.dismiss();
        }).create().show();
    }
}
