package com.dtu.innova.innova2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {
    KenBurnsView background;
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
                if(i == 4)
                    i = 0;
            }
        });
    }
}
