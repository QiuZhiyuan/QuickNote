package com.qiu.notes.ui.edit;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.qiu.base.lib.widget.recycler.BaseRecyclerAdapter;
import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.notes.data.NoteDatabaseImpl;
import com.qiu.notes.ui.edit.widget.EditNoteSection;
import com.qiu.notes.ui.base.BaseNoteFragment;

public class EditNoteFragment extends BaseNoteFragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected BaseRecyclerSection getNoteSection() {
        return new EditNoteSection();
    }
}
