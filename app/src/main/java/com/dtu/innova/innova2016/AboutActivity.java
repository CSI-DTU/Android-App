package com.dtu.innova.innova2016;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.faradaj.blurbehind.BlurBehind;

import andy.ayaseruri.lib.CircularRevealActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        BlurBehind.getInstance().setBackground(this);
    }
}
