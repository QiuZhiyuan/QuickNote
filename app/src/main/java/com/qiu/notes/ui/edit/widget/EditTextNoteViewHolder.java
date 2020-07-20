package com.qiu.notes.ui.edit.widget;

import android.view.View;
import android.widget.EditText;

import com.qiu.base.lib.widget.recycler.BaseRecyclerItem;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.notes.R;
import com.qiu.notes.ui.base.widget.TextNoteItem;

import androidx.annotation.NonNull;

public class EditTextNoteViewHolder extends BaseRecyclerViewHolder {

    @NonNull
    private final EditText mEditText;

    public EditTextNoteViewHolder(@NonNull View itemView) {
        super(itemView);
        mEditText = itemView.findViewById(R.id.edit_text_note_view);
    }

    @Override
    public void bindItem(@NonNull BaseRecyclerItem item) {
        if (item instanceof TextNoteItem) {
            final TextNoteItem noteItem = (TextNoteItem) item;
            final String content = noteItem.getEntry().getNote();
            if (content != null) {
                mEditText.setText(content);
            }
        }
    }

    @Override
    public void unBindItem() {

    }
}