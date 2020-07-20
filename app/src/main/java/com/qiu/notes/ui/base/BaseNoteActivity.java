package com.qiu.notes.ui.base;

import android.os.Bundle;

import com.qiu.base.lib.widget.recycler.BaseRecyclerView;
import com.qiu.notes.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseNoteActivity extends BaseActivity {

    public BaseNoteActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_note);
    }
}
