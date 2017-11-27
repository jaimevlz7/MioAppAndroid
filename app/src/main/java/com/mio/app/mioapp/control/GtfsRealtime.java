package com.mio.app.mioapp.control;


import android.app.Activity;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.mio.app.mioapp.model.Vehiculo;

public class GtfsRealtime {

   private ArrayList<Vehiculo> vehiculos;
   private String vehiculo;
   private boolean terminado;

public GtfsRealtime(ArrayList<Vehiculo> vehiculos){
    this.vehiculos = vehiculos;
    terminado = false;
    this.vehiculo = vehiculo;
}



public void descargar(String dirWeb, String id, String nombreVehiculo) throws IOException{

    URL url = new URL(dirWeb);
    FeedMessage feed = FeedMessage.parseFrom(url.openStream());

    for (FeedEntity entity : feed.getEntityList()) {
        if (entity.hasVehicle()) {
            String id_Route = entity.getVehicle().getTrip().getRouteId();
            if(id_Route.equals(id)) {
                float latitud = entity.getVehicle().getPosition().getLatitude();
                float longitud = entity.getVehicle().getPosition().getLongitude();

                Vehiculo n = new Vehiculo(latitud, longitud, nombreVehiculo);
                vehiculos.add(n);
            }
        }
    }
    terminado = true;
}


    public boolean isTerminado() {
        return terminado;
    }
}