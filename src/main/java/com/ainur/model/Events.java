package com.ainur.model;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Events {
    Data data;
    ArrayList<String> events = new ArrayList<>();

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }
}
