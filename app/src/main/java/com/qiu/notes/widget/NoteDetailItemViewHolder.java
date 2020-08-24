package com.qiu.notes.widget;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qiu.base.lib.widget.recycler.BaseRecyclerItem;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.notes.R;
import com.qiu.notes.data.NoteContentEntry;
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
    @NonNull
    private final EditText mEditTitle;

    public NoteDetailItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mUpdateTime = itemView.findViewById(R.id.update_time);
        mEditTitle = itemView.findViewById(R.id.edit_text_note_title);
        mEditTitle.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                final NoteContentEntry entry = getEntry();
                if (entry != null) {
                    entry.setTitleCache(mEditTitle.getText().toString());
                }
            }
        });
        mEditText = itemView.findViewById(R.id.edit_text_note_content);
        mEditText.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                final NoteContentEntry entry = getEntry();
                if (entry != null) {
                    entry.setNoteCache(mEditText.getText().toString());
                }
            }
        });
    }

    @Override
    public void bindItem(@NonNull BaseRecyclerItem item) {
        super.bindItem(item);
        final NoteContentEntry entry = getEntry();
        if (entry == null) {
            return;
        }
        final String title = entry.isHasCache() ? entry.getTitleCache() : entry.getTitle();
        if (!TextUtils.isEmpty(title)) {
            mEditTitle.setText(title);
        }
        final String content = entry.isHasCache() ? entry.getNoteCache() : entry.getNote();
        if (!TextUtils.isEmpty(content)) {
            mEditText.setText(content);
        }
        mUpdateTime.setText(getUpdateTimeStr(entry.getUpdateTime()));
    }

    @Override
    public void onDataUpdate() {
        final NoteContentEntry entry = getEntry();
        if (entry == null) {
            return;
        }
        mUpdateTime.setText(getUpdateTimeStr(entry.getUpdateTime()));
    }

    @Nullable
    private NoteContentEntry getEntry() {
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