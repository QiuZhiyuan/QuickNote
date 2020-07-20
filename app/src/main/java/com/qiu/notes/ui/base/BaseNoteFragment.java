package com.qiu.notes.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qiu.base.lib.widget.recycler.BaseRecyclerView;
import com.qiu.notes.R;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseNoteFragment extends Fragment {

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

    public BaseNoteFragment() {
        super();
        mState = State.START;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_base_note, container, false);
        mContentView = view.findViewById(R.id.base_note_content);
        return view;
    }

    @Override
    public void onDestroyView() {
        if (mStateChangeListenerList != null) {
            mStateChangeListenerList.clear();
            mStateChangeListenerList = null;
        }
        super.onDestroyView();
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
