package com.qiu.notes.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.qiu.base.lib.eventbus.EventDispatcher;
import com.qiu.base.lib.widget.recycler.BaseRecyclerItem;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.notes.R;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.event.UpdateTextNoteEvent;
import com.qiu.notes.widget.TextNoteItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteDetailTextViewHolder extends BaseRecyclerViewHolder implements TextWatcher {

    @NonNull
    private final EditText mEditText;
    @Nullable
    private TextContentEntry mEntry;

    public NoteDetailTextViewHolder(@NonNull View itemView) {
        super(itemView);
        mEditText = itemView.findViewById(R.id.edit_text_note_view);
        mEditText.addTextChangedListener(this);
    }

    @Override
    public void bindItem(@NonNull BaseRecyclerItem item) {
        if (item instanceof TextNoteItem) {
            final TextNoteItem noteItem = (TextNoteItem) item;
            mEntry = noteItem.getEntry();
            final String content = mEntry.getNote();
            if (content != null) {
                mEditText.setText(content);
            }
        }
    }

    @Override
    public void unBindItem() {
        mEntry = null;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mEntry != null) {
            mEntry.setUpdateTime(System.currentTimeMillis());
            mEntry.setNote(s.toString());
            EventDispatcher.post(new UpdateTextNoteEvent(mEntry));
        }
    }
}