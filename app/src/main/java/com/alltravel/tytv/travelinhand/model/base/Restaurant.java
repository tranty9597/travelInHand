package com.alltravel.tytv.travelinhand.model.base;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String ID;
    private String cityID;
    private String travelLocationID;
    private String phone;
    private String restaurantNm;

    public Restaurant(String ID, String cityID, String travelLocationID, String phone, String restaurantNm) {
        this.ID = ID;
        this.cityID = cityID;
        this.travelLocationID = travelLocationID;
        this.phone = phone;
        this.restaurantNm = restaurantNm;
    }

    public Restaurant(String cityID, String travelLocationID, String phone, String restaurantNm) {
        this.cityID = cityID;
        this.travelLocationID = travelLocationID;
        this.phone = phone;
        this.restaurantNm = restaurantNm;
    }

    public Restaurant() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getTravelLocationID() {
        return travelLocationID;
    }

    public void setTravelLocationID(String travelLocationID) {
        this.travelLocationID = travelLocationID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRestaurantNm() {
        return restaurantNm;
    }

    public void setRestaurantNm(String restaurantNm) {
        this.restaurantNm = restaurantNm;
    }
}
