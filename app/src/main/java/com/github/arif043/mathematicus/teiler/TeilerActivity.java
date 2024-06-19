package com.github.arif043.mathematicus.teiler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import com.github.arif043.mathematicus.FragmentUtils;
import com.github.arif043.mathematicus.R;
import com.github.arif043.mathematicus.keyboard.Keyboard;

public class TeilerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teiler_activity);

        Toolbar bar = findViewById(R.id.toolbar);
        bar.setTitle("Teiler");
        setSupportActionBar(bar);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final ViewPagerAdapter viewPagerAdpter1 = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdpter1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                FragmentUtils.remove(TeilerActivity.this, Keyboard.TAG);
            }

            public void onTabUnselected(TabLayout.Tab tab) {}
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}