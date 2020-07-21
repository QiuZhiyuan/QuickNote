package com.qiu.notes.event;

import com.qiu.notes.data.TextContentEntry;

import androidx.annotation.NonNull;

public class DeleteNoteEvent {
    @NonNull
    public final TextContentEntry mEntry;

    public DeleteNoteEvent(@NonNull TextContentEntry entry) {
        mEntry = entry;
    }
}
