package com.dtu.innova.innova2016;
//AIzaSyD2VVDUv9MRShjTjVBu31vcxsugAoH3uLU
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactActivity extends AppCompatActivity implements OnMapReadyCallback{
    MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(28.7499, 77.1170);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(28.7499, 77.1170))
                .title("Delhi Technological University"));
        LatLngBounds bounds = new LatLngBounds.Builder().include(latLng).build();
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }
}
