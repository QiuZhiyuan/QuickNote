package com.qiu.notes.data;

import java.io.Serializable;

import androidx.annotation.Nullable;

public abstract class BaseContentEntry implements Serializable {

    @Nullable
    public abstract String getContent();
}
