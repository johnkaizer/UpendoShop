package com.project.upendoshop.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.project.upendoshop.Adapters.ViewPagerAdapter;
import com.project.upendoshop.R;

public class AdminActivity extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        viewPager = findViewById(R.id.viewpager_home);
        tabLayout = findViewById(R.id.tabLayout_home);

        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.add(new HomeFragment(), "HOME");
        viewPagerAdapter.add(new ProductsFragment(), "PRODUCTS");
        viewPagerAdapter.add(new OrdersFragment(), "ORDERS");
        viewPager.setAdapter(viewPagerAdapter);
    }

}