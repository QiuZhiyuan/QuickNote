package com.qiu.notes.ui.list;

import android.os.Bundle;
import android.view.View;

import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.notes.ui.base.BaseNoteFragment;
import com.qiu.notes.ui.list.widget.NoteListSection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteListFragment extends BaseNoteFragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected BaseRecyclerSection getNoteSection() {
        return new NoteListSection();
    }

}
