package com.mio.app.mioapp.views;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
    private ArrayList<Ruta> rutas;
    private double MYLat, MYLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MYLng = 0;
        MYLat = 0;
        LocationManager locationManager;
        rutas = new ArrayList<Ruta>();
        setContentView(R.layout.activity_live_view);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Property for My Location
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mMap.clear();
                populatePuntoRecarga(location.getLatitude(), location.getLongitude());
                populateRoutes(location.getLatitude(), location.getLongitude());
                Log.d("LOCATION", "onLocationChanged: ");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });

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
        uiSettings.setMapToolbarEnabled(true);


        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            mMap.clear();
                            MYLat = location.getLatitude();
                            MYLng = location.getLongitude();
                            LatLng me = new LatLng(location.getLatitude(), location.getLongitude());
                            //mMap.addMarker(new MarkerOptions().position(me).title("ME!"));
                            mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(me));


                            if(liveData == null){
                                liveData = new GetLiveData(location.getLatitude(), location.getLongitude());

                            }



                                populatePuntoRecarga(location.getLatitude(), location.getLongitude());
                                populateRoutes(location.getLatitude(), location.getLongitude());

                           // updateLocations();


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
        //Log.d("JAPO", "populatePuntoRecargaSize: " + puntosRecarga.size());
        for (int i = 0; i < puntosRecarga.size(); i++) {
            PuntoRecarga tempPoint = puntosRecarga.get(i);
            double lng = tempPoint.getLatitud();
            double lat = tempPoint.getLongitud();
            LatLng tempPosition = new LatLng(lat,lng);
            String name = tempPoint.getNombre();

            //Filtra la distancia de los puntos antes de Cargarlos al mapa
            if(myLat-lat < dist && myLat-lat>-dist && myLng-lng < dist && myLng-lng> -dist) {
                mMap.addMarker(new MarkerOptions().position(tempPosition).title(name).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
            }
        }
    }

    public void populateRoutes(double myLat, double myLng){
        int height = 35;
        int width = 35;
        BitmapDrawable bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.bus);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        Log.d("LIVE2", "populateRoutes: Not empty - Rutas got on live view: " + liveData.getRutas() );
        if(liveData.getRutas() != null && !liveData.getRutas().isEmpty()){
            //If the array returned is not empty
            Log.d("LIVE2", "populateRoutes: Not empty");
            rutas = liveData.getRutas();
            Log.d("LIVE2", "populateRoutes: " + rutas.size());
            for (int i = 0; i < rutas.size(); i++) {
                LatLng tempPosition = new LatLng(rutas.get(i).getLat(), rutas.get(i).getLng() );
                mMap.addMarker(new MarkerOptions().position(tempPosition).title(rutas.get(i).getId()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                //After adding marker
            }
        }
        rutas = new ArrayList<Ruta>();

    }

}
