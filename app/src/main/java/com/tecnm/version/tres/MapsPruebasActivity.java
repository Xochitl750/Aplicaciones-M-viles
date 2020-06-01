package com.tecnm.version.tres;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tecnm.version.tres.data.Loader;
import com.tecnm.version.tres.data.Restaurante;
import com.tecnm.version.tres.utils.Notify;
import com.tecnm.version.tres.utils.Sesion;

public class MapsPruebasActivity extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;

    private LocationManager ubicacion;
    private double latitud;
    private double longitud;
    private Spinner spinnerRestaurantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("MapsPruebas.onCreate", "Se está inicializando el componente");
        setContentView(R.layout.activity_maps_pruebas);

        Loader.loadData();

        Log.d("MapsPruebas.onCreate", "Se está cargando el mapa");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Log.d("onCreate", "Se está inicializando el LocationManager");
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String[] nombresRestaurantes = new String[Loader.restaurantes.size()];
        for(int i = 0; i < Loader.restaurantes.size(); i ++)
        {
            nombresRestaurantes[i] = Loader.restaurantes.get(i).nombre;
        }
        spinnerRestaurantes = (Spinner) findViewById(R.id.spinner);
        spinnerRestaurantes.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, nombresRestaurantes));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MapsPruebas.onStart", "Se está inicializando el onStart");

        LocationManager locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d("MapsPruebas.onStart", "Se está verificando si el GPS se encuentra conectado");
        if (!gpsEnabled) {
            Log.d("MapsPruebas.onStart", "El GPS se encuentra desactivado, se llama a ACTION_LOCATION_SOURCE_SETTINGS");
            enableLocationSettings();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("MapsPruebas.onStart", "No se encuentran activados los permisos");
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
            }, 1000);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                10000,
                10,
                listener);
    }

    private void enableLocationSettings() {
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }

    public void buttonMapaVegana_Click(View v)
    {
        int contador = 0;
        int indexRestaurante = -1;
        for(Restaurante aux : Loader.restaurantes)
        {
            if (aux.vegano)
            {
                indexRestaurante = contador;
            }
            contador = contador + 1;
        }
        if (indexRestaurante < 0)
        {
            Notify.showToast(this, "Lo sentimos, no tenemos restaurantes veganos");
            return;
        }
        Sesion.restaurante = contador;
        Intent intent = new Intent(getApplicationContext(), RestauranteActivity.class);
        startActivityForResult(intent, 0);
    }

    public void buttonMapaRandom_Click(View v)
    {
        double random = Math.random()*6 + 1;
        Sesion.restaurante = (int) random;
        Intent intent = new Intent(getApplicationContext(), RestauranteActivity.class);
        startActivityForResult(intent, 0);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);

        //LatLng sydney = new LatLng(latitud, longitud);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

    }

    private final LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitud = location.getLatitude();
            longitud = location.getLongitude();

            mMap.clear();

            for(Restaurante var : Loader.restaurantes)
            {
                LatLng aux = new LatLng(var.latitud, var.longitud);
                mMap.addMarker(new MarkerOptions().position(aux).title(var.nombre));
            }

            LatLng sydney = new LatLng(latitud, longitud);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Tu ubicación").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            Log.d("LocationListener", "La posición del equipo ha cambiado Latitud: " + String.valueOf(latitud) + " Longitud: " + String.valueOf(longitud));
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
    };
}
