package com.mio.app.mioapp.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Japo on 26/11/17.
 */

public class Ruta {
    String id;
    double lat, lng, lat2, lng2;
    int route_id;
    MarkerOptions marker;

    public Ruta (double _lat, double _lng, int _route_id){
        lat = _lat;
        lng = _lng;
        lat2 = _lat;
        lng2 = _lng;
        route_id = _route_id;
        LatLng tempLatLng = new LatLng(lat,lng);
       // marker = new MarkerOptions().position(tempLatLng).title(route_id).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus));
    }

    public String getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public double getLat2() {
        return lat2;
    }

    public double getLng2() {
        return lng2;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setLat2(double lat2) {
        this.lat2 = lat2;
    }

    public void setLng2(double lng2) {
        this.lng2 = lng2;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public MarkerOptions getMarker() {
        return marker;
    }

    public void setNewLatLng(double _lat, double _lng){
        lat2=_lat;
        lng2=_lng;

        LatLng tempLatLng = new LatLng(lat2,lng2);
        //marker.position(tempLatLng).title(route_id).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus));
    }
}
