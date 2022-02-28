package com.kenjie.auth.core.entities;

import java.util.List;

public class PaginatedList<T> {

    private List<T> data;

    private boolean hasNextData;

    public PaginatedList(List<T> data, boolean hasNextData) {
        this.data = data;
        this.hasNextData = hasNextData;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean isHasNextData() {
        return hasNextData;
    }

    public void setHasNextData(boolean hasNextData) {
        this.hasNextData = hasNextData;
    }
}
