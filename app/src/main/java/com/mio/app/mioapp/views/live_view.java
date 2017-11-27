package com.mio.app.mioapp.views;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mio.app.mioapp.R;
import com.mio.app.mioapp.control.GetLiveData;
import com.mio.app.mioapp.control.ReadPuntosRecarga;
import com.mio.app.mioapp.model.PuntoRecarga;
import com.mio.app.mioapp.model.Ruta;

import java.util.ArrayList;

public class live_view extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private GetLiveData liveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_view);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Property for My Location
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //Initializar Live Routes feed
        liveData = new GetLiveData();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);
            return;
        }
        mMap.setMyLocationEnabled(true);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);


        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            LatLng me = new LatLng(location.getLatitude(), location.getLongitude());
                            //mMap.addMarker(new MarkerOptions().position(me).title("ME!"));
                            mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(me));

                            populatePuntoRecarga(location.getLatitude(), location.getLongitude());
                            populateRoutes(location.getLatitude(), location.getLongitude());



                        }
                    }
                });



    }

    public void populatePuntoRecarga(double myLat, double myLng){
        double dist = 0.009;
        ArrayList<PuntoRecarga> puntosRecarga;
        ReadPuntosRecarga readPuntos = new ReadPuntosRecarga(this.getApplicationContext());


        int height = 25;
        int width = 35;
        BitmapDrawable bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.charge_enable);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        puntosRecarga = readPuntos.obtenerPuntosRecarga(readPuntos.leer());
        Log.d("JAPO", "populatePuntoRecargaSize: " + puntosRecarga.size());
        for (int i = 0; i < puntosRecarga.size(); i++) {
            PuntoRecarga tempPoint = puntosRecarga.get(i);
            double lng = tempPoint.getLatitud();
            double lat = tempPoint.getLongitud();
            LatLng tempPosition = new LatLng(lat,lng);
            String name = tempPoint.getNombre();

            //Filtra la distancia de los puntos antes de Cargarlos al mapa
            if(myLat-lat < dist && myLat-lat>-dist && myLng-lng < dist && myLng-lng> -dist) {
                mMap.addMarker(new MarkerOptions().position(tempPosition).title(name).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                //Log.d("JAPO", "Punto Name: " + tempPoint.getNombre() + " - Latitud: " + lat + " - Longitud: " + lng);
            }
        }
    }

    public void populateRoutes(double myLat, double myLng){
        ArrayList<Ruta> rutas = liveData.getRutas();
        for (int i = 0; i < rutas.size(); i++) {
            mMap.addMarker(rutas.get(i).getMarker());
            Log.d("LIVE", "populateRoutes: " + rutas.get(i).getId());
        }
    }
}
