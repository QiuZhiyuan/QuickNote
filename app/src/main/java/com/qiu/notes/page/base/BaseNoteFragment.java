package com.qiu.notes.page.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.qiu.base.lib.eventbus.EventDispatcher;
import com.qiu.base.lib.widget.recycler.BaseRecyclerAdapter;
import com.qiu.base.lib.widget.recycler.BaseRecyclerSection;
import com.qiu.base.lib.widget.recycler.BaseRecyclerView;
import com.qiu.notes.R;
import com.qiu.notes.event.AddNewNoteEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseNoteFragment extends Fragment implements View.OnClickListener {

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
        prepareViews(view);
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

    protected void prepareViews(@NonNull View view) {
        prepareButtons(view);
        prepareContentView(view);
    }

    private void prepareButtons(@NonNull View view) {
        view.findViewById(R.id.btn_add_note).setOnClickListener(this);
        view.findViewById(R.id.btn_edit_undo).setOnClickListener(this);
        view.findViewById(R.id.btn_left_menu).setOnClickListener(this);
    }

    private void prepareContentView(@NonNull View view) {
        final BaseRecyclerView contentView = view.findViewById(R.id.base_note_content);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_note:
                EventDispatcher.post(new AddNewNoteEvent());
                break;
            case R.id.btn_edit_undo:
            case R.id.btn_left_menu:
        }
    }

    protected abstract BaseRecyclerSection getNoteSection();
}
