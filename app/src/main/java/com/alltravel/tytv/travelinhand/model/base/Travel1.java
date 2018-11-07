package com.alltravel.tytv.travelinhand.model.base;

public class Travel1 {
    private int ID;
    private String username;
    private String travelNm;
    private String travelDes;
    private String dateCreated;

    public Travel1(int ID, String username, String travelNm, String travelDes, String dateCreated) {
        this.username = username;
        this.travelNm = travelNm;
        this.travelDes = travelDes;
        this.dateCreated = dateCreated;
    }

    public Travel1(String username, String travelNm, String travelDes, String dateCreated) {
        this.username = username;
        this.travelNm = travelNm;
        this.travelDes = travelDes;
        this.dateCreated = dateCreated;
    }

    public Travel1() {
    }

    public String getUsername() {
        return username;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Travel1{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", travelNm='" + travelNm + '\'' +
                ", travelDes='" + travelDes + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }
}
