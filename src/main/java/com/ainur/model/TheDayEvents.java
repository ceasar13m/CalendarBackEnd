package com.ainur.model;
import  java.util.Date;
import java.util.ArrayList;

public class TheDayEvents {
    private Date date;
    private ArrayList<Event> descriptions = new ArrayList<>();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Event> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(ArrayList<Event> descriptions) {
        this.descriptions = descriptions;
    }

    public void addDayEvent(Event event) {
        descriptions.add(event);
    }
}
