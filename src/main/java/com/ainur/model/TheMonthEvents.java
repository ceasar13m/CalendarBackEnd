package com.ainur.model;

import java.util.ArrayList;

public class TheMonthEvents {
    private ArrayList<Event> arrayList = new ArrayList<>();

    public ArrayList<Event> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Event> arrayList) {
        this.arrayList = arrayList;
    }

    public void addEvent (Event event) {
        arrayList.add(event);
    }

}
