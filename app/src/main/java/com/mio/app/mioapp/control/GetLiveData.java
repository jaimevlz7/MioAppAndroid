package com.mio.app.mioapp.control;

import com.mio.app.mioapp.model.Ruta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;


public class GetLiveData extends Thread{


    private String respuesta;
    private HttpURLConnection urlConnection;
    public ArrayList<Ruta> rutas;
    public GetLiveData() {
        rutas = new ArrayList<Ruta>();
        start();

    }

    public ArrayList<Ruta> getRutas(){
        return rutas;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void run() {
        super.run();

        try {
            while (true) {
                respuesta = clienteHttp("http://190.216.202.35:90/gtfs/realtime/");
               // Log.d("LIVE TXT DATA", "GetLiveData: " + getRespuesta());
                getLiveRoutes();
                sleep(20000);
            }


        }catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e){
            e.getStackTrace();
        }
    }

    public String clienteHttp(String dirweb) throws IOException {

        String body = " ";

        try {

            URL url = new URL(dirweb);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Integer codigoRespuesta = urlConnection.getResponseCode();

            if(codigoRespuesta==HttpURLConnection.HTTP_UNAUTHORIZED){
                Authenticator.setDefault(new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("appconcurso", "JcYbIry5sA".toCharArray());
                    }
                });
            }

            url = new URL(dirweb+"vehiclePositions.pb.txt");
            urlConnection = (HttpURLConnection) url.openConnection();
            body = readStream(urlConnection.getInputStream());


            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            body = e.toString(); //Error URL incorrecta
            e.printStackTrace();
        } catch (SocketTimeoutException e){
            body = e.toString(); //Error: Finalizado el timeout esperando la respuesta del servidor.
            e.printStackTrace();
        } catch (Exception e) {
            body = e.toString();//Error diferente a los anteriores.
            e.printStackTrace();
        }
        return body;
    }


    private String readStream(InputStream in) throws IOException{

        BufferedReader reader = null;
        StringBuilder sb = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(in));
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

        /*
        BufferedReader r = null;
        r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        if(r != null){
            r.close();
        }
        in.close();
        return total.toString();
        */
    }


    public void getLiveRoutes() {

    }
}
