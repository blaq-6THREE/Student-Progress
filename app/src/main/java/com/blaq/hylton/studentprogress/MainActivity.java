package com.blaq.hylton.studentprogress;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SectionPageManager mSectionPageManager;
    private ViewPager mPager;
    private TabLayout  mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tabs);

        setupViewPager(mPager);
        mTabLayout.setupWithViewPager(mPager);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setupViewPager(ViewPager viewPager)
    {
        mSectionPageManager = new SectionPageManager(getSupportFragmentManager());
        mSectionPageManager.addAdapter(new GradePointAverage(), "GPA Calc");
        mSectionPageManager.addAdapter(new ProgressTracker(), "Progress Tracker");

        viewPager.setAdapter(mSectionPageManager);
    }

}
