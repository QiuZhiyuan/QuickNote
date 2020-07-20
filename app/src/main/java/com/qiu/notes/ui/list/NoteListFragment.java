package com.qiu.notes.ui.list;

import android.os.Bundle;
import android.view.View;

import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.base.lib.widget.recycler.BaseRecyclerView;
import com.qiu.notes.R;
import com.qiu.notes.ui.base.BaseNoteFragment;
import com.qiu.notes.ui.list.widget.NoteListSection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class NoteListFragment extends BaseNoteFragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected void prepareContentView(@NonNull BaseRecyclerView contentView) {
        super.prepareContentView(contentView);
        final int paddingH =
                getContext().getResources().getDimensionPixelOffset(R.dimen.note_list_padding_h);
        contentView.setPadding(paddingH, 0, paddingH, 0);
    }

    @Override
    protected BaseRecyclerSection getNoteSection() {
        return new NoteListSection();
    }

}
