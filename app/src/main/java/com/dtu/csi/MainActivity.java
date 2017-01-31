package com.dtu.csi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import butterknife.ButterKnife;
import butterknife.Bind;

public class MainActivity extends AppCompatActivity {
    private static final long RIPPLE_DURATION = 250;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.root)
    FrameLayout root;
    @Bind(R.id.content_hamburger)
    View contentHamburger;
    int current_fragment = 0;
    private static int about_fragment = 1, event_fragment = 2, gallery_fragment = 3, profile_fragment = 4, contact_fragment = 5, feed_fragment = 6;
    View currentView = null;
    boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
    @Override
    protected void onStart() {
        super.onStart();
        SignUpFragment.googleApiClient.connect();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }
        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        final GuillotineAnimation animation = new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
        final LinearLayout about = (LinearLayout) guillotineMenu.findViewById(R.id.about_group);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.setBackgroundColor(Color.parseColor("#031D4B"));
                if (current_fragment != about_fragment) {
                    current_fragment = about_fragment;
                    if(currentView != null)
                        changeTextColor(currentView);
                    TextView label = (TextView) about.findViewById(R.id.about_option);
                    label.setTextColor(Color.parseColor("#60C2D3"));
                    currentView = about;
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new AboutFragment()).commit();
                }
                animation.close();
            }
        });
        final LinearLayout events = (LinearLayout) guillotineMenu.findViewById(R.id.event_group);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.setBackgroundColor(Color.parseColor("#031D4B"));
                if(!isConnected()) {
                    Snackbar.make(v, "Not Connected to the Internet", Snackbar.LENGTH_LONG)
                            .setAction("Settings", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(Settings.ACTION_SETTINGS));
                                }
                            }).show();
                } else {
                    if (current_fragment != event_fragment) {
                        current_fragment = event_fragment;
                        if (currentView != null)
                            changeTextColor(currentView);
                        TextView label = (TextView) events.findViewById(R.id.events_option);
                        label.setTextColor(Color.parseColor("#60C2D3"));
                        currentView = events;
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new EventsFragment()).commit();
                    }
                }
                animation.close();
            }
        });
        final LinearLayout contact = (LinearLayout) guillotineMenu.findViewById(R.id.contact_group);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.setBackgroundColor(Color.parseColor("#031D4B"));
                if(current_fragment != contact_fragment) {
                    current_fragment = contact_fragment;
                    if(currentView != null)
                        changeTextColor(currentView);
                    TextView label = (TextView) contact.findViewById(R.id.contact_option);
                    label.setTextColor(Color.parseColor("#60C2D3"));
                    currentView = contact;
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new ContactFragment()).commit();
                }
                animation.close();
            }
        });
        final LinearLayout gallery = (LinearLayout) guillotineMenu.findViewById(R.id.gallery_group);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.setBackgroundColor(Color.parseColor("#031D4B"));
                if(current_fragment != gallery_fragment) {
                    current_fragment = gallery_fragment;
                    if(currentView != null)
                        changeTextColor(currentView);
                    TextView label = (TextView) gallery.findViewById(R.id.gallery_option);
                    label.setTextColor(Color.parseColor("#60C2D3"));
                    currentView = gallery;
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new GalleryFragment()).commit();
                }
                animation.close();
            }
        });
        final LinearLayout profile = (LinearLayout) guillotineMenu.findViewById(R.id.profile_group);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.setBackgroundColor(Color.parseColor("#031D4B"));
                if(current_fragment != profile_fragment) {
                    current_fragment = profile_fragment;
                    if(currentView != null)
                        changeTextColor(currentView);
                    TextView label = (TextView) profile.findViewById(R.id.profile_option);
                    label.setTextColor(Color.parseColor("#60C2D3"));
                    currentView = profile;
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
                }
                animation.close();
            }
        });

        final LinearLayout logOut = (LinearLayout) guillotineMenu.findViewById(R.id.log_out_group);
        SharedPreferences prefs = getSharedPreferences("creds", 0);
//        if(prefs.getString("id", null) == null)
//            logOut.setVisibility(View.INVISIBLE);
//        else {
//            logOut.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    root.setBackgroundColor(Color.parseColor("#031D4B"));
//                    SignUpFragment.googleApiClient.connect();
//                    Auth.GoogleSignInApi.signOut(SignUpFragment.googleApiClient).setResultCallback(
//                            new ResultCallback<Status>() {
//                                @Override
//                                public void onResult(Status status) {
//                                    Snackbar.make(logOut, "Signed Out!", Snackbar.LENGTH_LONG).show();
//                                    SharedPreferences prefs = getSharedPreferences("creds", 0);
//                                    prefs.edit().clear().apply();
//                                }
//                            });
//                    startActivity(new Intent(getApplicationContext(), IntroActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                    finish();
//                }
//            });
//        }
        logOut.setVisibility(View.INVISIBLE);
        final LinearLayout feed = (LinearLayout) guillotineMenu.findViewById(R.id.feed_group);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected()) {
                    Snackbar.make(v, "Not Connected to the Internet", Snackbar.LENGTH_LONG)
                            .setAction("Settings", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(Settings.ACTION_SETTINGS));
                                }
                            }).show();
                } else {
                    root.setBackgroundColor(Color.parseColor("#031D4B"));
                    if (current_fragment != feed_fragment) {
                        current_fragment = feed_fragment;
                        if (currentView != null)
                            changeTextColor(currentView);
                        TextView label = (TextView) feed.findViewById(R.id.feed_option);
                        label.setTextColor(Color.parseColor("#60C2D3"));
                        currentView = feed;
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new NewsFeedFragment()).commit();
                    }
                }
                animation.close();
            }
        });
        root.addView(guillotineMenu);
        root.setBackgroundColor(Color.parseColor("#031D4B"));
        if(current_fragment != feed_fragment) {
            current_fragment = feed_fragment;
            if(currentView != null)
                changeTextColor(currentView);
            TextView label = (TextView) feed.findViewById(R.id.feed_option);
            label.setTextColor(Color.parseColor("#60C2D3"));
            currentView = feed;
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new NewsFeedFragment()).commit();
        }
    }
    void changeTextColor(View currentView) {
        TextView label;
        switch (currentView.getId()) {
            case R.id.about_group:
                label = (TextView) currentView.findViewById(R.id.about_option);
                label.setTextColor(Color.WHITE);
                break;
            case R.id.event_group:
                label = (TextView) currentView.findViewById(R.id.events_option);
                label.setTextColor(Color.WHITE);
                break;
            case R.id.gallery_group:
                label = (TextView) currentView.findViewById(R.id.gallery_option);
                label.setTextColor(Color.WHITE);
                break;
            case R.id.contact_group:
                label = (TextView) currentView.findViewById(R.id.contact_option);
                label.setTextColor(Color.WHITE);
                break;
            case R.id.log_out:
                label = (TextView) currentView.findViewById(R.id.log_out);
                label.setTextColor(Color.WHITE);
                break;
            case R.id.profile_group:
                label = (TextView) currentView.findViewById(R.id.profile_option);
                label.setTextColor(Color.WHITE);
                break;
            case R.id.feed_group:
                label = (TextView) currentView.findViewById(R.id.feed_option);
                label.setTextColor(Color.WHITE);
                break;
        }
    }
}