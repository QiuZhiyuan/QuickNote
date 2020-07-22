package com.qiu.notes.ui.list;

import android.os.Bundle;
import android.view.View;

import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.notes.R;
import com.qiu.notes.ui.base.BaseNoteFragment;
import com.qiu.notes.ui.list.widget.NoteListSection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteListFragment extends BaseNoteFragment {

    @NonNull
    private final NoteListSection mSection = new NoteListSection();

    @Override
    protected void prepareViews(@NonNull View view) {
        super.prepareViews(view);
    }

    @Override
    protected BaseRecyclerSection getNoteSection() {
        return mSection;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSection.update();
    }
}
