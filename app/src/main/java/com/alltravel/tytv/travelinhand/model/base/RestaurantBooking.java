package com.alltravel.tytv.travelinhand.model.base;

import java.io.Serializable;

public class RestaurantBooking implements Serializable {
    private int ID;
    private int travelStepID;
    private String restaurantID;
    private String bookingTime;

    public RestaurantBooking(int ID, int travelStepID, String restaurantID, String bookTime) {
        this.ID = ID;
        this.travelStepID = travelStepID;
        this.restaurantID = restaurantID;
        this.bookingTime = bookTime;
    }

    public RestaurantBooking(int travelStepID, String restaurantID, String bookTime) {
        this.travelStepID = travelStepID;
        this.restaurantID = restaurantID;
        this.bookingTime = bookTime;
    }

    public RestaurantBooking() {
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

    public String getBookTime() {
        return bookingTime;
    }

    public void setBookTime(String bookTime) {
        this.bookingTime = bookTime;
    }

    @Override
    public String toString() {
        return "RestaurantBooking{" +
                "ID=" + ID +
                ", travelStepID=" + travelStepID +
                ", restaurantID='" + restaurantID + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                '}';
    }
}
