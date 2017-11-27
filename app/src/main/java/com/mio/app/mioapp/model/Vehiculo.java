package com.mio.app.mioapp.model;

/**
 * Created by PI2 on 27/11/17.
 */

public class Vehiculo {

    private float latitud;
    private float longitud;
    private String nombreVehiculo;


    public Vehiculo(float latitud, float longitud, String nombreVehiculo){
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombreVehiculo = nombreVehiculo;
    }


    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getNombreVehiculo() {
        return nombreVehiculo;
    }

    public void setNombreVehiculo(String nombreVehiculo) {
        this.nombreVehiculo = nombreVehiculo;
    }
}
