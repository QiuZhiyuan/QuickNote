package com.qiu.notes.page;

import android.view.View;

import androidx.annotation.NonNull;

import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.notes.R;
import com.qiu.notes.data.InternalDataProvider;
import com.qiu.notes.data.NoteDataEntry;
import com.qiu.notes.page.base.BaseNoteFragment;
import com.qiu.notes.widget.NoteDetailSection;

public class NoteDetailFragment extends BaseNoteFragment {

    private static final String KEY_NOTE_ID = "note_id";

    @NonNull
    private final NoteDataEntry mEntry;
    @NonNull
    private final NoteDetailSection mSection;

    private NoteDetailFragment(long noteId) {
        NoteDataEntry entry;
        entry = InternalDataProvider.i().getNoteDataHelper().findEntryById(noteId);
        mEntry = entry;
        mSection = new NoteDetailSection(mEntry);
    }

    @Override
    protected void prepareViews(@NonNull View view) {
        super.prepareViews(view);
        view.findViewById(R.id.bottom_bar_container).setVisibility(View.GONE);
    }

    public static NoteDetailFragment getInstance(long noteId) {
        return new NoteDetailFragment(noteId);
    }

    @Override
    protected BaseRecyclerSection getNoteSection() {
        return mSection;
    }
}
