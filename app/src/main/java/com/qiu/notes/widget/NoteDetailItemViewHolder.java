package com.qiu.notes.widget;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qiu.base.lib.widget.recycler.BaseRecyclerItem;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.notes.R;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.utils.simple.SimpleTextWatcher;
import com.qiu.notes.utils.Tools;
import com.qiu.notes.widget.base.TextNoteItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteDetailItemViewHolder extends BaseRecyclerViewHolder {

    @NonNull
    private final EditText mEditText;
    @NonNull
    private final TextView mUpdateTime;

    public NoteDetailItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mUpdateTime = itemView.findViewById(R.id.update_time);
        mEditText = itemView.findViewById(R.id.edit_text_note_view);
        final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                final TextContentEntry entry = getEntry();
                if (entry != null) {
                    entry.setNoteCache(mEditText.getText().toString());
                }
            }
        };
        mEditText.addTextChangedListener(textWatcher);
    }

    @Override
    public void bindItem(@NonNull BaseRecyclerItem item) {
        super.bindItem(item);
        final TextContentEntry entry = getEntry();
        if (entry == null) {
            return;
        }
        final String content = entry.isChanged() ? entry.getNoteCache() : entry.getNote();
        if (content != null) {
            mEditText.setText(content);
        }
        mUpdateTime.setText(getUpdateTimeStr(entry.getUpdateTime()));
    }

    @Override
    public void onDataUpdate() {
        final TextContentEntry entry = getEntry();
        if (entry == null) {
            return;
        }
        mUpdateTime.setText(getUpdateTimeStr(entry.getUpdateTime()));
    }

    @Nullable
    private TextContentEntry getEntry() {
        if (mItem != null && mItem instanceof TextNoteItem) {
            return ((TextNoteItem) mItem).mEntry;
        }
        return null;
    }

    @NonNull
    private String getUpdateTimeStr(long updateTime) {
        return getResources().getString(R.string.update_time, Tools.getDateString(updateTime));
    }
}