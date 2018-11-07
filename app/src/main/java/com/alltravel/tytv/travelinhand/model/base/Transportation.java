package com.alltravel.tytv.travelinhand.model.base;

import java.io.Serializable;

public class Transportation implements Serializable {

    private String id;
    private String transportation_name;
    private int from_city_id;
    private String travel_location_id;
    private String open_time;
    private int time_distance;
    private String phone;
    private String to_city_id;
    private String description;
    private double price;

    public Transportation(String id, String transportation_name, int from_city_id, String travel_location_id, String open_time, int time_distance, String phone, String to_city_id, String description) {
        this.id = id;
        this.transportation_name = transportation_name;
        this.from_city_id = from_city_id;
        this.travel_location_id = travel_location_id;
        this.open_time = open_time;
        this.time_distance = time_distance;
        this.phone = phone;
        this.to_city_id = to_city_id;
        this.description = description;
    }

    public Transportation(String id, String transportation_name, String phone,String open_time, int time_distance,  Double price, String description) {
        this.id = id;
        this.transportation_name = transportation_name;
        this.price = price;
        this.open_time = open_time;
        this.time_distance = time_distance;
        this.phone = phone;
        this.description = description;
    }
    public Transportation(String id, String transportation_name, String phone, String open_time, int time_distance) {
        this.id = id;
        this.transportation_name = transportation_name;
        this.phone = phone;
        this.open_time = open_time;
        this.time_distance = time_distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTransportation_name() {
        return transportation_name;
    }

    public void setTransportation_name(String transportation_name) {
        this.transportation_name = transportation_name;
    }

    public int getFrom_city_id() {
        return from_city_id;
    }

    public void setFrom_city_id(int from_city_id) {
        this.from_city_id = from_city_id;
    }

    public String getTravel_location_id() {
        return travel_location_id;
    }

    public void setTravel_location_id(String travel_location_id) {
        this.travel_location_id = travel_location_id;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public int getTime_distance() {
        return time_distance;
    }

    public void setTime_distance(int time_distance) {
        this.time_distance = time_distance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTo_city_id() {
        return to_city_id;
    }

    public void setTo_city_id(String to_city_id) {
        this.to_city_id = to_city_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
