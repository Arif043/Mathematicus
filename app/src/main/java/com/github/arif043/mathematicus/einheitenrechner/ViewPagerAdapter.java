package com.github.arif043.mathematicus.einheitenrechner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.Arrays;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final String[] TAB_NAMES = {"Länge", "Volumen", "Daten"};
    private List<FragmentRechner> rechner = Arrays.asList(new Leangenrechner(), new Volumenrechner(), new Datenrechner());

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return rechner.get(position);
    }

    @Override
    public int getCount() {
        return TAB_NAMES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_NAMES[position];
    }
}