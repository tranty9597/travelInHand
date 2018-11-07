package com.alltravel.tytv.travelinhand.model.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Travel implements Serializable {
    @SerializedName("ID")
    private int ID;
    @SerializedName("ussername")
    private  String username;
    @SerializedName("dateCreated")
    private String dateCreated;
    @SerializedName("travelNm")
    private String travelNm;
    @SerializedName("travelDes")
    private String travelDes;
    @SerializedName("status")
    private int status;

    public Travel(int ID, String username, String dateCreated, String travelNm, String travelDes, int status) {
        this.ID = ID;
        this.username = username;
        this.dateCreated = dateCreated;
        this.travelNm = travelNm;
        this.travelDes = travelDes;
        this.status = status;
    }

    public Travel(String ussername, String dateCreated, String travelNm, String travelDes, int status) {
        this.username = ussername;
        this.dateCreated = dateCreated;
        this.travelNm = travelNm;
        this.travelDes = travelDes;
        this.status = status;
    }

    public Travel() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUssername() {
        return username;
    }

    public void setUssername(String ussername) {
        this.username = ussername;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTravelNm() {
        return travelNm;
    }

    public void setTravelNm(String travelNm) {
        this.travelNm = travelNm;
    }

    public String getTravelDes() {
        return travelDes;
    }

    public void setTravelDes(String travelDes) {
        this.travelDes = travelDes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", travelNm='" + travelNm + '\'' +
                ", travelDes='" + travelDes + '\'' +
                ", status=" + status +
                '}';
    }
}
