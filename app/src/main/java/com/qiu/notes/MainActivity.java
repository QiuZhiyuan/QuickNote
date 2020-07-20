package com.qiu.notes;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.qiu.base.lib.eventbus.EventDispatcher;
import com.qiu.notes.data.InternalDataProvider;
import com.qiu.notes.event.ShowFragmentEvent;
import com.qiu.notes.ui.base.BaseNoteActivity;
import com.qiu.notes.ui.list.NoteListFragment;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseNoteActivity {

    private class EventHandler {

        @Subscribe
        public void showFragment(ShowFragmentEvent event) {
            addFragmentToBackStack(event.mFragment);
        }
    }

    private EventHandler mEventHandler = new EventHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InternalDataProvider.i().getNoteDataHolder().updateAll();
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
        fm.beginTransaction().add(R.id.content, fragment).addToBackStack(null).commit();
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
}
