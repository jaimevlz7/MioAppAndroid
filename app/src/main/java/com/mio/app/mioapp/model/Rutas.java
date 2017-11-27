package com.mio.app.mioapp.model;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jorge Castaño on 26/11/2017.
 */

public class Rutas {

    private Activity actividad;
    private Map<String, String> lista;

    public Rutas(Activity ac ){
        actividad = ac;
        lista = new HashMap<>();

        obtenerRutas();
    }

    public Map<String, String> getLista() {
        return lista;
    }

    public void obtenerRutas() {

        BufferedReader reader = null;
        StringBuilder sb = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(actividad.getAssets().open("routes.txt")));
            sb = new StringBuilder();
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] ruta = line.split(",");
                lista.put(ruta[1],ruta[0]);
            }

            reader.close();
        } catch (IOException e) {
            //log the exception
        }
    }

public String obtenerNombreRuta(String id){
        return lista.get(id);
}
}
