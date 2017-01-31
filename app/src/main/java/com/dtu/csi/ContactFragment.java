package com.dtu.csi;


import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import tyrantgit.explosionfield.ExplosionField;

public class ContactFragment extends Fragment {
    ImageView git, facebook, website;
    TextView phone, email, map;
    ExplosionField explosionField;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, null);
        explosionField = ExplosionField.attach2Window(getActivity());
        git = (ImageView) view.findViewById(R.id.git_logo);
        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(
                                Uri.parse("https://github.com/CSI-DTU/Android-App")));
            }
        });
        facebook = (ImageView) view.findViewById(R.id.facebook_logo);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(
                        Uri.parse("https://www.facebook.com/dtu.csi/")));

            }
        });
        website = (ImageView) view.findViewById(R.id.web_logo);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(
                        Uri.parse("https://csi-dtu.github.io/")));
            }
        });
        phone = (TextView) view.findViewById(R.id.phone);
        email = (TextView) view.findViewById(R.id.email);
        phone.setPaintFlags(phone.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone.getText().toString()));
                startActivity(intent);
            }
        });
        map = (TextView) view.findViewById(R.id.map);
        map.setPaintFlags(map.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:28.7499, 77.1170");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        return view;
    }
}
