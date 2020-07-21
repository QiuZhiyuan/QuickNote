package com.qiu.notes.ui.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.qiu.base.lib.widget.recycler.BaseRecyclerAdapter;
import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
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

    public BaseNoteFragment() {
        super();
        setState(State.START);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareContentView(view.findViewById(R.id.base_note_content));
        getNoteSection().onCreate();
    }

    @Override
    public void onDestroyView() {
        if (mStateChangeListenerList != null) {
            mStateChangeListenerList.clear();
            mStateChangeListenerList = null;
        }
        setState(State.FINISH);
        getNoteSection().onDestroy();
        super.onDestroyView();
    }

    protected void prepareContentView(@NonNull BaseRecyclerView contentView) {
        contentView.setLayoutManager(new LinearLayoutManager(getContext()));
        contentView.setAdapter(new BaseRecyclerAdapter(getNoteSection()));
        final int paddingH = contentView.getContext().getResources()
                .getDimensionPixelOffset(R.dimen.note_list_padding_h);
        contentView.setPadding(paddingH, 0, paddingH, 0);
        contentView.setBackgroundColor(Color.WHITE);
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

    protected abstract BaseRecyclerSection getNoteSection();
}
