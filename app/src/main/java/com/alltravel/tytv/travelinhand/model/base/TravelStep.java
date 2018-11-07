package com.alltravel.tytv.travelinhand.model.base;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TravelStep implements Serializable {
    private int id;
    private int travel_id;
    private String fromCityID;
    private String toCityID;
    private String restaurantID;
    private String tranpositationID;
    private String hotelID;
    private String startDate;
    private String endDate;
    private String fromCity;
    private String toCity;
    private int status;
    private int imageId;

    public TravelStep(int id, String stardDate, String endDate, String fromCity, String toCity, int status, int imageID) {
        this.id = id;
        this.startDate = stardDate;
        this.endDate = endDate;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.status = status;
        this.imageId = imageID;
    }

    public TravelStep() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStardDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
            Date date = sdf.parse(startDate);

            return date;
        }catch (Exception ex){
            return null;
        }

    }

    public void setStardDate(String stardDate) {
        this.startDate = stardDate;
    }

    public Date getEndDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
            Date date = sdf.parse(startDate);

            return date;
        }catch (Exception ex){
            return null;
        }
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTravel_id() {
        return travel_id;
    }

    public void setTravel_id(int travel_id) {
        this.travel_id = travel_id;
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

    public String getTranpositationID() {
        return tranpositationID;
    }

    public void setTranpositationID(String tranpositationID) {
        this.tranpositationID = tranpositationID;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
