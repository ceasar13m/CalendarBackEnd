package com.ainur.model;

import java.util.ArrayList;

public class Events {
    private ArrayList<Event> events = new ArrayList<>();

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void addEvent (Event event) {
        events.add(event);
    }

}



