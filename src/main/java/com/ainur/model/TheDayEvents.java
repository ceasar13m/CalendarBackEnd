package com.ainur.model;
import  java.util.Date;
import java.util.ArrayList;

public class TheDayEvents {
    private Date date;
    private ArrayList<String> events = new ArrayList<>();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }
}
