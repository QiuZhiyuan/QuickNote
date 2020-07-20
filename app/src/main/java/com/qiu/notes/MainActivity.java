package com.qiu.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.qiu.notes.edit.EditNoteFragment;
import com.qiu.notes.ui.base.BaseNoteActivity;

public class MainActivity extends BaseNoteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(new EditNoteFragment());
    }

    private void showFragment(@NonNull Fragment fragment) {
        final FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.content, fragment, null).addToBackStack("edit_note")
                .commit();
    }
}
