package com.ainur.model;

import java.util.ArrayList;

public class TheMonthEvents {
    private ArrayList<TheDayEvents> arrayList = new ArrayList<>();

    public ArrayList<TheDayEvents> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<TheDayEvents> arrayList) {
        this.arrayList = arrayList;
    }

}
