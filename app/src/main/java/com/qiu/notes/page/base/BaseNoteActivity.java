package com.qiu.notes.page.base;

import android.os.Bundle;

import com.qiu.notes.R;

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
