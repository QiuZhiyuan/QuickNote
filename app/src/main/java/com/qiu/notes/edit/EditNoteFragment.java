package com.qiu.notes.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.qiu.base.lib.widget.recycler.BaseRecyclerAdapter;
import com.qiu.notes.data.NoteDatabaseImpl;
import com.qiu.notes.edit.widget.EditNoteSection;
import com.qiu.notes.ui.base.BaseNoteFragment;

public class EditNoteFragment extends BaseNoteFragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NoteDatabaseImpl.i()
                .insert(223, System.currentTimeMillis(), System.currentTimeMillis(),
                        "Hello World 223");
        if (mContentView != null) {
            mContentView.setLayoutManager(new LinearLayoutManager(getContext()));
            mContentView.setAdapter(new BaseRecyclerAdapter(new EditNoteSection()));
        }
    }
}
