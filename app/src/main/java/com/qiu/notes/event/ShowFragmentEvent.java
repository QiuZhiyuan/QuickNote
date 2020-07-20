package com.qiu.notes.event;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ShowFragmentEvent {

    @NonNull
    public final Fragment mFragment;

    public ShowFragmentEvent(@NonNull Fragment fragment) {
        mFragment = fragment;
    }
}
