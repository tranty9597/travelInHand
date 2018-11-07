package com.alltravel.tytv.travelinhand.model.base;

import java.io.Serializable;

public class StepTravel implements Serializable {
    private int ID;
    private int traveID;
    private String fromCity;
    private String toCity;
    private String transportationID;
    private String hotelID;
    private int restaurantBookID;
    private String startDate;
    private String endDate;

    public StepTravel(int ID, int traveID, String fromCity, String toCity, String transportationID, String hotelID, int restaurantBookID, String startDate, String endDate) {
        this.ID = ID;
        this.traveID = traveID;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.transportationID = transportationID;
        this.hotelID = hotelID;
        this.restaurantBookID = restaurantBookID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public StepTravel(int traveID, String fromCity, String toCity, String transportationID, String hotelID, int restaurantBookID, String startDate, String endDate) {
        this.traveID = traveID;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.transportationID = transportationID;
        this.hotelID = hotelID;
        this.restaurantBookID = restaurantBookID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTraveID() {
        return traveID;
    }

    public void setTraveID(int traveID) {
        this.traveID = traveID;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getTransportationID() {
        return transportationID;
    }

    public void setTransportationID(String transportationID) {
        this.transportationID = transportationID;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public int getRestaurantID() {
        return restaurantBookID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantBookID = restaurantBookID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "StepTravel{" +
                "ID=" + ID +
                ", traveID=" + traveID +
                ", fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                ", transportationID='" + transportationID + '\'' +
                ", hotelID='" + hotelID + '\'' +
                ", restaurantBookID=" + restaurantBookID +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
