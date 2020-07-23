package com.qiu.notes.page;

import android.view.View;

import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.notes.page.base.BaseNoteFragment;
import com.qiu.notes.widget.NoteListSection;

import androidx.annotation.NonNull;

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
