package com.dtu.innova.innova2016;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zys.brokenview.BrokenCallback;
import com.zys.brokenview.BrokenTouchListener;
import com.zys.brokenview.BrokenView;

import andy.ayaseruri.lib.CircularRevealActivity;
import tyrantgit.explosionfield.ExplosionField;

public class ContactActivity extends AppCompatActivity implements OnMapReadyCallback{
    MapFragment mapFragment;
    ImageView git, facebook, website;
    TextView phone, email;
    ExplosionField explosionField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        explosionField = ExplosionField.attach2Window(this);
        git = (ImageView) findViewById(R.id.git_logo);
        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.explode(v);
                startActivity(new Intent(Intent.ACTION_VIEW).setData(
                        Uri.parse("https://github.com/asdzxc2/InnovaApp2016.git")));
            }
        });
        facebook = (ImageView) findViewById(R.id.facebook_logo);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.explode(v);
                startActivity(new Intent(Intent.ACTION_VIEW).setData(
                        Uri.parse("https://www.facebook.com/innovadtu/?fref=ts")));
            }
        });
        website = (ImageView) findViewById(R.id.web_logo);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.explode(v);
                startActivity(new Intent(Intent.ACTION_VIEW).setData(
                        Uri.parse("http://www.innovafest.com/")));
            }
        });
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        phone.setPaintFlags(phone.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.explode(v);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone.getText().toString()));
                startActivity(intent);
            }
        });
        email.setPaintFlags(email.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.explode(v);
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:" + email.getText().toString()));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                startActivity(emailIntent);
                v.setScaleX(1);
                v.setScaleY(1);
                v.setAlpha(1);
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(28.7499, 77.1170);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(28.7499, 77.1170))
                .title("Delhi Technological University"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }
}
