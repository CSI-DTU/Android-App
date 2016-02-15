package com.dtu.innova.innova2016;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter{
    CharSequence[] tabTitles;
    CharSequence[] descriptions;
    int[] posters;
    public PagerAdapter(FragmentManager fm, CharSequence[] tabTitles, CharSequence[] descriptions, int[] posters) {
        super(fm);
        this.tabTitles = tabTitles;
        this.descriptions = descriptions;
        this.posters = posters;
    }

    @Override
    public Fragment getItem(int position) {
        return GuestLectureFragment.newInstance(descriptions[position], posters[position]);
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
