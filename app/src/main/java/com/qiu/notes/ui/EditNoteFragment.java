package com.qiu.notes.ui;

import android.view.View;

import androidx.annotation.NonNull;

import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.notes.R;
import com.qiu.notes.data.InternalDataProvider;
import com.qiu.notes.data.TextContentEntry;
import com.qiu.notes.ui.base.BaseNoteFragment;
import com.qiu.notes.widget.NoteDetailSection;

public class EditNoteFragment extends BaseNoteFragment {

    private static final String KEY_NOTE_ID = "note_id";

    @NonNull
    private final TextContentEntry mEntry;
    @NonNull
    private final NoteDetailSection mSection;

    private EditNoteFragment(long noteId) {
        TextContentEntry entry;
        entry = InternalDataProvider.i().getNoteDataHolder().findEntryById(noteId);
        mEntry = entry;
        mSection = new NoteDetailSection(mEntry);
    }

    @Override
    protected void prepareViews(@NonNull View view) {
        super.prepareViews(view);
        view.findViewById(R.id.bottom_bar_container).setVisibility(View.GONE);
    }

    public static EditNoteFragment getInstance(long noteId) {
        return new EditNoteFragment(noteId);
    }

    @Override
    protected BaseRecyclerSection getNoteSection() {
        return mSection;
    }
}
