package com.alltravel.tytv.travelinhand.model.base;

public class StepTravel1 {
    private int ID;
    private int travelID;
    private String fromCityID;
    private String toCityID;
    private String tranpostationID;
    private String hotelID;
    private int restaurantBookingID;
    private String startDate;
    private String endDate;

    public StepTravel1(int ID, int travelID, String fromCityID, String toCityID, String tranpostationID, String hotelID, int restaurantBookingID, String startDate, String endDate) {
        this.ID = ID;
        this.travelID = travelID;
        this.fromCityID = fromCityID;
        this.toCityID = toCityID;
        this.tranpostationID = tranpostationID;
        this.hotelID = hotelID;
        this.restaurantBookingID = restaurantBookingID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public StepTravel1(int travelID, String fromCityID, String toCityID, String tranpostationID, String hotelID, int restaurantBookingID, String startDate, String endDate) {
        this.travelID = travelID;
        this.fromCityID = fromCityID;
        this.toCityID = toCityID;
        this.tranpostationID = tranpostationID;
        this.hotelID = hotelID;
        this.restaurantBookingID = restaurantBookingID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTravelID() {
        return travelID;
    }

    public void setTravelID(int travelID) {
        this.travelID = travelID;
    }

    public String getFromCityID() {
        return fromCityID;
    }

    public void setFromCityID(String fromCityID) {
        this.fromCityID = fromCityID;
    }

    public String getToCityID() {
        return toCityID;
    }

    public void setToCityID(String toCityID) {
        this.toCityID = toCityID;
    }

    public String getTranpostationID() {
        return tranpostationID;
    }

    public void setTranpostationID(String tranpostationID) {
        this.tranpostationID = tranpostationID;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public int getRestaurantBookingID() {
        return restaurantBookingID;
    }

    public void setRestaurantBookingID(int restaurantBookingID) {
        this.restaurantBookingID = restaurantBookingID;
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
        return "StepTravel1{" +
                "ID=" + ID +
                ", travelID=" + travelID +
                ", fromCityID='" + fromCityID + '\'' +
                ", toCityID='" + toCityID + '\'' +
                ", tranpostationID='" + tranpostationID + '\'' +
                ", hotelID='" + hotelID + '\'' +
                ", restaurantBookingID='" + restaurantBookingID + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
