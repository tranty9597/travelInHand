package com.alltravel.tytv.travelinhand.model.base;

import java.io.Serializable;

public class Hotel implements Serializable {
    private String ID;
    private String hotelNm;
    private String phone;
    private String cityID;
    private String travelLocationID;

    public Hotel() {
    }

    public Hotel(String ID, String hotelNm, String phone, String cityID, String travelLocationID) {
        this.ID = ID;
        this.hotelNm = hotelNm;
        this.phone = phone;
        this.cityID = cityID;
        this.travelLocationID = travelLocationID;
    }

    public Hotel(String hotelNm, String phone, String cityID, String travelLocationID) {
        this.hotelNm = hotelNm;
        this.phone = phone;
        this.cityID = cityID;
        this.travelLocationID = travelLocationID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHotelNm() {
        return hotelNm;
    }

    public void setHotelNm(String hotelNm) {
        this.hotelNm = hotelNm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
