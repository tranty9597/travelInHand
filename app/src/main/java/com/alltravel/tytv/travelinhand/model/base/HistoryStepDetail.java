package com.alltravel.tytv.travelinhand.model.base;

public class HistoryStepDetail {
    private int id;
    private int travelID;
    private String fromCityNm, toCityNm, tranpostationNm, hotelNm, restaurantNm, startDate, endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTravelID() {
        return travelID;
    }

    public void setTravelID(int travelID) {
        this.travelID = travelID;
    }

    public String getFromCityNm() {
        return fromCityNm;
    }

    public void setFromCityNm(String fromCityNm) {
        this.fromCityNm = fromCityNm;
    }

    public String getToCityNm() {
        return toCityNm;
    }

    public void setToCityNm(String toCityNm) {
        this.toCityNm = toCityNm;
    }

    public String getTranpostationNm() {
        return tranpostationNm;
    }

    public void setTranpostationNm(String tranpostationNm) {
        this.tranpostationNm = tranpostationNm;
    }

    public String getHotelNm() {
        return hotelNm;
    }

    public void setHotelNm(String hotelNm) {
        this.hotelNm = hotelNm;
    }

    public String getRestaurantNm() {
        return restaurantNm;
    }

    public void setRestaurantNm(String restaurantNm) {
        this.restaurantNm = restaurantNm;
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
}
