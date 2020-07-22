package com.qiu.notes.event;

import com.qiu.notes.data.TextContentEntry;

import androidx.annotation.NonNull;

public class UpdateTextNoteEvent {
    @NonNull
    public final TextContentEntry mEntry;

    public UpdateTextNoteEvent(@NonNull TextContentEntry entry) {
        mEntry = entry;
    }
}
