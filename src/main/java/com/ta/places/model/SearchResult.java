package com.ta.places.model;

public class SearchResult {
    private  String address;
    private Latlong latlong;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Latlong getLatlong() {
        return latlong;
    }

    public void setLatlong(Latlong latlong) {
        this.latlong = latlong;
    }
}
