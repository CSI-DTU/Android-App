package com.dtu.innova.innova2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;

import butterknife.Bind;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class MainActivity extends AppCompatActivity {
    KenBurnsView background;
    ImageView about, gallery, events, contact;
    int[] backgrounds = {R.drawable.background2, R.drawable.background3, R.drawable.background4, R.drawable.background1};
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background = (KenBurnsView) findViewById(R.id.main_background);
        background.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                background.setImageResource(backgrounds[i]);
                i++;
                if (i == 4)
                    i = 0;
            }
        });
        about = (ImageView) findViewById(R.id.about);
        gallery = (ImageView) findViewById(R.id.gallery);
        events = (ImageView) findViewById(R.id.events);
        contact = (ImageView) findViewById(R.id.contact);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ContactActivity.class));
            }
        });
    }
}
