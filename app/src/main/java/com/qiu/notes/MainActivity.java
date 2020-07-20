package com.qiu.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.qiu.notes.data.InternalDataProvider;
import com.qiu.notes.ui.base.BaseNoteActivity;
import com.qiu.notes.ui.edit.EditNoteFragment;
import com.qiu.notes.ui.list.NoteListFragment;

public class MainActivity extends BaseNoteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InternalDataProvider.i().getNoteDataHolder().updateAll();
        showFragment(new NoteListFragment());
    }

    private void showFragment(@NonNull Fragment fragment) {
        final FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() == 0) {
            fm.beginTransaction().add(R.id.content, fragment, null).commit();
        } else {
            fm.beginTransaction().add(R.id.content, fragment, null).addToBackStack("edit_note")
                    .commit();
        }
    }
}
