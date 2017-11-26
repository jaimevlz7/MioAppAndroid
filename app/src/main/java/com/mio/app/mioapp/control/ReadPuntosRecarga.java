package com.mio.app.mioapp.control;

import android.content.Context;
import android.content.res.AssetManager;

import com.mio.app.mioapp.model.PuntoRecarga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Japo on 22/11/17.
 */

public class ReadPuntosRecarga {

    Context context;
    AssetManager mngr;
    public ReadPuntosRecarga(Context _context){
        context =_context;
        mngr = context.getAssets();
    }

    public String leer() {
        BufferedReader reader = null;
        StringBuilder sb = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(mngr.open("paradas.txt")));
            sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            //log the exception
        }
        return sb.toString();
    }



    public ArrayList<PuntoRecarga> obtenerPuntosRecarga(String respuesta){
        JSONObject json;
        ArrayList<PuntoRecarga> recargas = new ArrayList<PuntoRecarga>();
        try {
            json = new JSONObject(respuesta);
            JSONObject jsonObj = json.getJSONObject("feed");
            JSONArray elem1 = jsonObj.getJSONArray("entry");
            for (int i = 0; i < elem1.length(); i++) {
                JSONObject mJsonObjectProperty = elem1.getJSONObject(i);
                String nombre, direccion, estado, coord;
                double latitud, longitud;
                if(!mJsonObjectProperty.getJSONObject("gsx$coordenadas").getString("$t").isEmpty()){

                    nombre = mJsonObjectProperty.getJSONObject("gsx$nombredelestablecimiento").getString("$t");
                    direccion = mJsonObjectProperty.getJSONObject("gsx$direccionestablecimiento").getString("$t");
                    //tipo = mJsonObjectProperty.getString("gsx$direccionestablecimiento");
                    coord = mJsonObjectProperty.getJSONObject("gsx$coordenadas").getString("$t");
                    estado = mJsonObjectProperty.getJSONObject("gsx$estado").getString("$t");
                    PuntoRecarga punto = new PuntoRecarga();
                    punto.setDirección(direccion);
                    punto.setNombre(nombre);
                    punto.setEstado(estado);
                    String[] lonLat = coord.split(",");
                    longitud = Double.parseDouble(lonLat[0].trim());
                    latitud = Double.parseDouble(lonLat[1].trim());
                    punto.setLongitud(longitud);
                    punto.setLatitud(latitud);

                    recargas.add(punto);
                }

            }
            //cedulas = cedRec;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return recargas;
    }

}
