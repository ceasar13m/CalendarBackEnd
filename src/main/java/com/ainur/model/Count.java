package com.ainur.model;

import java.util.Date;

public class Count {
    private long date;
    private int count;

    public Date getDate() {
        return new Date(this.date);
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
