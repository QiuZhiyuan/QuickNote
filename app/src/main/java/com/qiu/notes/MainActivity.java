package com.qiu.notes;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.qiu.base.lib.eventbus.EventDispatcher;
import com.qiu.notes.data.NoteDataHolder;
import com.qiu.notes.event.AddNewNoteEvent;
import com.qiu.notes.event.DeleteNoteEvent;
import com.qiu.notes.event.ShowFragmentEvent;
import com.qiu.notes.ui.base.BaseNoteActivity;
import com.qiu.notes.ui.edit.EditNoteFragment;
import com.qiu.notes.ui.list.NoteListFragment;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseNoteActivity implements View.OnClickListener {

    private class EventHandler {

        @Subscribe
        public void showFragment(ShowFragmentEvent event) {
            addFragmentToBackStack(event.mFragment);
        }

        @Subscribe
        public void addNewNote(AddNewNoteEvent event) {
            addFragmentToBackStack(
                    EditNoteFragment.getInstance(NoteDataHolder.CREATE_NEW_ENTRY_ID));
        }

        @Subscribe
        public void deleteNote(DeleteNoteEvent event) {

        }
    }

    private EventHandler mEventHandler = new EventHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventDispatcher.register(mEventHandler);
        EventDispatcher.post(new ShowFragmentEvent(new NoteListFragment()));
        prepareButtons();
    }

    @Override
    protected void onDestroy() {
        EventDispatcher.unregister(mEventHandler);
        super.onDestroy();
    }

    private void prepareButtons() {
        findViewById(R.id.btn_add_note).setOnClickListener(this);
        findViewById(R.id.btn_delete_note).setOnClickListener(this);
        findViewById(R.id.btn_edit_undo).setOnClickListener(this);
        findViewById(R.id.btn_left_menu).setOnClickListener(this);
    }

    private void addFragmentToBackStack(@NonNull Fragment fragment) {
        final FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content, fragment).addToBackStack(null).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_note:
                EventDispatcher.post(new AddNewNoteEvent());
                break;
            case R.id.btn_delete_note:
            case R.id.btn_edit_undo:
            case R.id.btn_left_menu:
        }
    }
}
