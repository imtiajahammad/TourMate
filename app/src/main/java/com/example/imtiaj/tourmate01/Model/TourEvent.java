package com.example.imtiaj.tourmate01.Model;

/**
 * Created by imtiaj on 2/8/2017.
 */

public class TourEvent {
    private int eventId;
    private  String destination;
    private String fromDate;
    private String toDate;
    private double eventBudget;



    public double getEventBudget() {
        return eventBudget;
    }

    public void setEventBudget(double eventBudget) {
        this.eventBudget = eventBudget;
    }




    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public TourEvent(int eventId, String destination, String fromDate, String toDate, double eventBudget) {
        this.eventId = eventId;
        this.destination = destination;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.eventBudget = eventBudget;
    }

    public TourEvent(String destination, String fromDate, String toDate, double eventBudget) {
        this.destination = destination;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.eventBudget = eventBudget;
    }


}


