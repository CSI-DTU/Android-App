package com.dtu.innova.innova2016;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter{
    CharSequence[] tabTitles;
    public PagerAdapter(FragmentManager fm, CharSequence[] tabTitles) {
        super(fm);
        this.tabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return new GuestLectureFragment();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
