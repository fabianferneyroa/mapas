package com.veterinaria.mapasapp;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.veterinaria.mapasapp.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    Button btnSatelital, btnHibrido, btnTopografico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        btnHibrido = findViewById(R.id.btnHibrido);
        btnSatelital = findViewById(R.id.btnsatelital);
        btnTopografico = findViewById(R.id.btnTopografico);


        btnHibrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            }
        });


        btnSatelital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            }
        });

        btnTopografico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            }
        });


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

        definirMarcadores(googleMap);
        verificarPermisos(googleMap);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }

    public void definirMarcadores(GoogleMap googleMap) {


        mMap = googleMap;

        LatLng unab = new LatLng(7.121585978023794, -73.11116486220509);
        LatLng pan = new LatLng(7.116754741769921, -73.1063077249223);

        mMap.addMarker(new MarkerOptions().position(unab).title("UNAB B/Manga").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        mMap.addMarker(new MarkerOptions().position(pan).title("Panarte B/Manga").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(unab, 15));
    }


    public void verificarPermisos(GoogleMap googleMap) {


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
        googleMap.setMyLocationEnabled(true);

    }

}