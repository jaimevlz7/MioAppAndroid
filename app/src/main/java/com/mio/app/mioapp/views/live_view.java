package com.mio.app.mioapp.views;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mio.app.mioapp.R;
import com.mio.app.mioapp.control.GetLiveData;
import com.mio.app.mioapp.control.ReadPuntosRecarga;
import com.mio.app.mioapp.model.PuntoRecarga;
import com.mio.app.mioapp.model.Ruta;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceAlignmentEnum;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;

public class live_view extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private GetLiveData liveData;
    private ArrayList<Ruta> rutas;
    private double MYLat, MYLng;
    private static final String TAG = "live_view";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private static final int PLACE_PICKER_REQUEST = 1;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));
    //vars
    private Boolean mLocationPermissionsGranted = false;
    public LocationManager locationManager;

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_live_view);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb1);
        MYLng = 0;
        MYLat = 0;
        LocationManager locationManager;
        rutas = new ArrayList<Ruta>();
        createLoop();
        getLocationPermission();

        HamButton.Builder builder1 = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_traza_ruta)
                .normalText("Planear ruta")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(live_view.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                    }
                });
        bmb.addBuilder(builder1);

        HamButton.Builder builder2 = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_real_time)
                .normalText("Ubicar Rutas")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(live_view.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                    }
                });
        bmb.addBuilder(builder2);

        HamButton.Builder builder3 = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_noticias)
                .normalText("Noticias")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(live_view.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(live_view.this, TwitterActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
        bmb.addBuilder(builder3);

        HamButton.Builder builder4 = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_puntos_recarga)
                .normalText("Puntos de recarga")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(live_view.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                    }
                });
        bmb.addBuilder(builder4);
    }

    public void initMap(){
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
                /*
                mMap.clear();
                populatePuntoRecarga(location.getLatitude(), location.getLongitude());
                populateRoutes(location.getLatitude(), location.getLongitude());
                Log.d("LOCATION", "onLocationChanged: ");
                */
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

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
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

    public void createLoop(){
        new CountDownTimer(5000, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {
               // Log.d("TICK2", "onTick: ");
            }

            public void onFinish() {
                feedHere();
                Log.d("LIVE2", "Feeded");
                createLoop();
            }
        }.start();
    }

    public void feedHere(){
        if(mMap!= null) {
            mMap.clear();
        }
        populatePuntoRecarga(MYLat,MYLng);
        populateRoutes(MYLat,MYLng);
    }

}
