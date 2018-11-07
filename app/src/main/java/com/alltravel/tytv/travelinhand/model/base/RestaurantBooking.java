package com.alltravel.tytv.travelinhand.model.base;

import java.io.Serializable;

public class RestaurantBooking implements Serializable {
    private int id, travelStepID, imageID;
    private String restaurantID, bookingTime, restaurantNm;;
    public RestaurantBooking(int id, int travelStepID, int imageID, String restaurantID, String bookingTime, String restaurantNm) {
        this.id = id;
        this.travelStepID = travelStepID;
        this.imageID = imageID;
        this.restaurantID = restaurantID;
        this.bookingTime = bookingTime;
        this.restaurantNm = restaurantNm;
    }

    public RestaurantBooking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getRestaurantNm() {
        return restaurantNm;
    }

    public void setRestaurantNm(String restaurantNm) {
        this.restaurantNm = restaurantNm;
    }
}
