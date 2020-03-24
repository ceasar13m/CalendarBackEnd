package com.ainur.model;

import java.util.ArrayList;

public class Counts {
    private ArrayList<Count> counts = new ArrayList();

    public ArrayList getCounts() {
        return counts;
    }

    public void setCounts(ArrayList counts) {
        this.counts = counts;
    }

    public void addCount (Count count) {
        counts.add(count);
    }
}
