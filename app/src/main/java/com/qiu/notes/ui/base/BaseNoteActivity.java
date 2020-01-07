package com.qiu.notes.ui.base;

import android.os.Bundle;

import com.qiu.base.lib.widget.recycler.BaseRecyclerView;
import com.qiu.notes.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseNoteActivity extends BaseActivity {

    public enum State {
        START,
        LOADING,
        PREPARED,
        FINISH;
    }

    public interface OnStateChangeListener {
        public void onChanged(@NonNull State state);
    }

    @NonNull
    private State mState;
    @Nullable
    private List<OnStateChangeListener> mStateChangeListenerList;
    @Nullable
    protected BaseRecyclerView mContentView;

    public BaseNoteActivity() {
        super();
        mState = State.START;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_note);
        mContentView = findViewById(R.id.base_note_content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mStateChangeListenerList != null) {
            mStateChangeListenerList.clear();
            mStateChangeListenerList = null;
        }
    }

    protected void setState(@NonNull State state) {
        mState = state;
        onStateChanged(state);

    }

    protected State getState() {
        return mState;
    }

    protected void onStateChanged(@NonNull State state) {
        if (mStateChangeListenerList != null) {
            for (OnStateChangeListener listener : mStateChangeListenerList) {
                listener.onChanged(state);
            }
        }
    }

    public void setStateChangeListener(@NonNull OnStateChangeListener listener) {
        if (mStateChangeListenerList == null) {
            mStateChangeListenerList = new ArrayList<>();
        }
        mStateChangeListenerList.add(listener);
    }
}
