package com.qiu.notes.ui.edit;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.base.lib.widget.recycler.BaseRecyclerView;
import com.qiu.notes.data.InternalDataProvider;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.ui.edit.widget.EditNoteSection;
import com.qiu.notes.ui.base.BaseNoteFragment;

public class EditNoteFragment extends BaseNoteFragment {

    private static final String KEY_NOTE_ID = "note_id";

    @NonNull
    private final TextContentEntry mEntry;
    @NonNull
    private final EditNoteSection mSection;

    private EditNoteFragment(long noteId) {
        TextContentEntry entry;
        entry = InternalDataProvider.i().getNoteDataHolder().findEntryById(noteId);
        mEntry = entry;
        mSection = new EditNoteSection(mEntry);
    }

    public static EditNoteFragment getInstance(long noteId) {
        return new EditNoteFragment(noteId);
    }

    @Override
    protected BaseRecyclerSection getNoteSection() {
        return mSection;
    }

    @Override
    protected void prepareContentView(@NonNull BaseRecyclerView contentView) {
        super.prepareContentView(contentView);
        contentView.setBackgroundColor(Color.WHITE);
    }
}
