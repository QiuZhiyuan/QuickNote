package com.qiu.notes.edit;

import android.os.Bundle;

import com.qiu.base.lib.widget.recycler.BaseRecyclerAdapter;
import com.qiu.notes.data.NoteDatabaseImpl;
import com.qiu.notes.edit.widget.EditNoteSection;
import com.qiu.notes.ui.base.BaseNoteActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

public class EditNoteActivity extends BaseNoteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoteDatabaseImpl.i()
                .insert(223, System.currentTimeMillis(), System.currentTimeMillis(), "Hello World 223");
        if (mContentView != null) {
            mContentView.setLayoutManager(new LinearLayoutManager(this));
            mContentView.setAdapter(new BaseRecyclerAdapter(new EditNoteSection()));
        }
    }
}
