package com.qiu.notes;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.qiu.base.lib.eventbus.EventDispatcher;
import com.qiu.notes.data.NoteDataHelper;
import com.qiu.notes.event.AddNewNoteEvent;
import com.qiu.notes.event.ShowFragmentEvent;
import com.qiu.notes.page.base.BaseNoteActivity;
import com.qiu.notes.page.NoteDetailFragment;
import com.qiu.notes.page.NoteListFragment;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseNoteActivity {

    private class EventHandler {

        @Subscribe
        public void showFragment(ShowFragmentEvent event) {
            addFragmentToBackStack(event.mFragment);
        }

        @Subscribe
        public void addNewNote(AddNewNoteEvent event) {
            addFragmentToBackStack(
                    NoteDetailFragment.getInstance(NoteDataHelper.CREATE_NEW_ENTRY_ID));
        }
    }

    private EventHandler mEventHandler = new EventHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventDispatcher.register(mEventHandler);
        EventDispatcher.post(new ShowFragmentEvent(new NoteListFragment()));
    }

    @Override
    protected void onDestroy() {
        EventDispatcher.unregister(mEventHandler);
        super.onDestroy();
    }


    private void addFragmentToBackStack(@NonNull Fragment fragment) {
        final FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content, fragment)
                .addToBackStack(fragment.getClass().getSimpleName()).commit();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
                finish();
            } else {
                getSupportFragmentManager().popBackStack();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }
}
