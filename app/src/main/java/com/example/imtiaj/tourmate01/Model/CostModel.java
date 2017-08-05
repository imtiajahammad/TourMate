package com.example.imtiaj.tourmate01.Model;

/**
 * Created by imtiaj on 2/9/2017.
 */

public class CostModel {

    private int costId;
    private  String costDetails;
    private  double costAmount;
    private  String date;
    private String time;
    private String eventName;



    public CostModel(int costId, String costDetails, double costAmount, String date, String time,String eventName) {
        this.costId = costId;
        this.costDetails = costDetails;
        this.costAmount = costAmount;
        this.date = date;
        this.time = time;
        this.eventName=eventName;
    }

    public CostModel(String costDetails, double costAmount, String date, String time, String eventName) {
        this.costDetails = costDetails;
        this.costAmount = costAmount;
        this.date = date;
        this.time = time;
        this.eventName=eventName;
    }

    public CostModel(String costDetails, double costAmount,String eventName) {
        this.costDetails = costDetails;
        this.costAmount = costAmount;
        this.eventName=eventName;
    }

    public String getEventName() {return eventName;}

    public void setEventName(String eventName) {this.eventName = eventName;}

    public int getCostId() {
        return costId;
    }

    public void setCostId(int costId) {
        this.costId = costId;
    }

    public String getCostDetails() {
        return costDetails;
    }

    public void setCostDetails(String costDetails) {
        this.costDetails = costDetails;
    }

    public double getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(double costAmount) {
        this.costAmount = costAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
