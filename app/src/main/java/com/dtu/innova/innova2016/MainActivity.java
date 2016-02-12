package com.dtu.innova.innova2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.faradaj.blurbehind.BlurBehind;
import com.faradaj.blurbehind.OnBlurCompleteListener;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;

public class MainActivity extends AppCompatActivity {
    KenBurnsView background;
    ImageView about, gallery, events, contact;
    int[] backgrounds = {R.drawable.background2, R.drawable.background3, R.drawable.background4, R.drawable.background1};
    int i = 0;
    int[] clickPoints = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background = (KenBurnsView) findViewById(R.id.main_background);
        //background.pause();

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
                BlurBehind.getInstance().execute(MainActivity.this, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {
                        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                });
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                startActivity(intent);
                //overridePendingTransition(0, 0);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
                startActivity(intent);
                //overridePendingTransition(0, 0);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GuestLectureActivity.class);
                startActivity(intent);
                //overridePendingTransition(0, 0);
            }
        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        clickPoints[0] = (int) ev.getX();
        clickPoints[1] = (int) ev.getY();
        return super.dispatchTouchEvent(ev);
    }
}
