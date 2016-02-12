package com.dtu.innova.innova2016;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GuestLectureActivity extends AppCompatActivity {
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.tabs)
    TabLayout tabs;
    PagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        CharSequence[] titles = {"Dr. A.S. Pillai", "Diwakar Vaish", "Shiv Khera", "Arjun Vajpai"};
        adapter = new PagerAdapter(getSupportFragmentManager(), titles);
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
    }
}
