package com.qiu.notes.widget;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qiu.base.lib.widget.recycler.BaseRecyclerItem;
import com.qiu.base.lib.widget.recycler.BaseRecyclerViewHolder;
import com.qiu.notes.R;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.setting.Config;
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
    private final Runnable mSaveNoteTask = new Runnable() {
        @Override
        public void run() {
            if (mEntry != null && mEntry.isChanged()) {
                mEntry.setUpdateTime(System.currentTimeMillis());
                mEntry.save();
                updateTime();
            }
        }
    };
    @NonNull
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    @Nullable
    private TextContentEntry mEntry;

    public NoteDetailItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mUpdateTime = itemView.findViewById(R.id.update_time);
        mEditText = itemView.findViewById(R.id.edit_text_note_view);
        final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mEntry.setNote(mEditText.getText().toString());
                mHandler.removeCallbacks(mSaveNoteTask);
                mHandler.postDelayed(mSaveNoteTask, Config.EDIT_SAVE_DURATION);
            }
        };
        mEditText.addTextChangedListener(textWatcher);
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
            updateTime();
        }
    }

    private void updateTime() {
        if (mEntry != null) {
            mUpdateTime.setText(getResources()
                    .getString(R.string.update_time, Tools.getDateString(mEntry.getUpdateTime())));
        }
    }

    @Override
    public void unBindItem() {
        mEntry = null;
    }
}