package com.alltravel.tytv.travelinhand.model.base;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TravelStep implements Serializable {
    private int id;
    private String startDate;
    private String endDate;
    private String fromCity;
    private String toCity;
    private int status;

    public TravelStep(int id, String stardDate, String endDate, String fromCity, String toCity, int status) {
        this.id = id;
        this.startDate = stardDate;
        this.endDate = endDate;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.status = status;
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

    public String getEndDate() {
        return endDate;
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
}
