package com.dtu.innova.innova2016;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.faradaj.blurbehind.BlurBehind;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        BlurBehind.getInstance().setBackground(this);
    }
}
