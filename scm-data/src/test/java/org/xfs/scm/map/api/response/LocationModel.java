package org.xfs.scm.map.api.response;

public class LocationModel {
    private double lat;
    private double lng;
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLat() {
        return lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    public double getLng() {
        return lng;
    }
}
