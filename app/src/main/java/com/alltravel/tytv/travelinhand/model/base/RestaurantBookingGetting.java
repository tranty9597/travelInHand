package com.alltravel.tytv.travelinhand.model.base;

import java.io.Serializable;

public class RestaurantBookingGetting implements Serializable {
    private int ID;
    private int travelStepID;
    private String restaurantID;
    private String bookingTime;

    public RestaurantBookingGetting() {
    }

    public RestaurantBookingGetting(int ID, int travelStepID, String restaurantID, String bookingTime) {
        this.ID = ID;
        this.travelStepID = travelStepID;
        this.restaurantID = restaurantID;
        this.bookingTime = bookingTime;
    }

    public RestaurantBookingGetting(int travelStepID, String restaurantID, String bookingTime) {
        this.travelStepID = travelStepID;
        this.restaurantID = restaurantID;
        this.bookingTime = bookingTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTravelStepID() {
        return travelStepID;
    }

    public void setTravelStepID(int travelStepID) {
        this.travelStepID = travelStepID;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "RestaurantBookingGetting{" +
                "ID=" + ID +
                ", travelStepID=" + travelStepID +
                ", restaurantID='" + restaurantID + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                '}';
    }
}
