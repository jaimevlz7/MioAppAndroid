package com.mio.app.mioapp.control;

import android.util.Log;

import com.google.transit.realtime.GtfsRealtime;
import com.mio.app.mioapp.model.Ruta;

import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;


public class GetLiveData extends Thread {


    private String respuesta;
    private HttpURLConnection urlConnection;
    public ArrayList<Ruta> rutas;
    private String dirWeb = "http://190.216.202.35:90/gtfs/realtime/";


    public GetLiveData() {
        rutas = new ArrayList<Ruta>();
        start();

    }

    public ArrayList<Ruta> getRutas() {
        return rutas;
    }


    public void run() {
        super.run();

        try {
            while (true) {
                clienteHttp();
                sleep(20000);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }

    public void clienteHttp() throws IOException {

        try {

            URL urlAuth = new URL("http://190.216.202.35:90/gtfs/realtime/");
            HttpURLConnection urlConnection = (HttpURLConnection) urlAuth.openConnection();
            Integer codigoRespuesta = urlConnection.getResponseCode();

            if (codigoRespuesta == HttpURLConnection.HTTP_UNAUTHORIZED) {
                Authenticator.setDefault(new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("appconcurso", "JcYbIry5sA".toCharArray());
                    }
                });
            }

            //URL url = new URL("http://japomedia.com/vehiclePositions.pb");
            URL url = new URL("http", "190.216.202.35", 90, "/gtfs/realtime/vehiclePositions.pb");
            urlConnection = (HttpURLConnection) url.openConnection();
            GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream());
            urlConnection.disconnect();

            for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
                if (entity.hasVehicle()) {
                    String id_Route = entity.getVehicle().getTrip().getRouteId();

                    Log.d("LIVE", "clienteHttp RouteId: " + id_Route);

                    if (rutas != null && !rutas.isEmpty()) {

                        for (int i = 0; i < rutas.size(); i++) {

                            if (id_Route.equals(rutas.get(i).getRoute_id())) {
                                //Change new coordenates
                                float latitud = entity.getVehicle().getPosition().getLatitude();
                                float longitud = entity.getVehicle().getPosition().getLongitude();
                                rutas.get(i).setNewLatLng(latitud, longitud);
                               // Log.d("LIVE", "Route coordenates changed");
                            } else {
                                //Add new Route if it doesnt exists
                                float latitud = entity.getVehicle().getPosition().getLatitude();
                                float longitud = entity.getVehicle().getPosition().getLongitude();

                                Ruta n = new Ruta(latitud, longitud, id_Route);
                                rutas.add(n);
                                //Log.d("LIVE", "New Route Added");
                            }

                        }
                    } else {
                        //Add the first Route in the Arraylist
                        float latitud = entity.getVehicle().getPosition().getLatitude();
                        float longitud = entity.getVehicle().getPosition().getLongitude();

                        Ruta n = new Ruta(latitud, longitud, id_Route);
                        rutas.add(n);
                        Log.d("LIVE", "First Route Added to ArrayList");
                    }
                }
            }
        } catch (MalformedURLException e) {
            // body = e.toString(); //Error URL incorrecta
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            // body = e.toString(); //Error: Finalizado el timeout esperando la respuesta del servidor.
            e.printStackTrace();
        } catch (Exception e) {
            //body = e.toString();//Error diferente a los anteriores.
            e.printStackTrace();
        }
    }
}