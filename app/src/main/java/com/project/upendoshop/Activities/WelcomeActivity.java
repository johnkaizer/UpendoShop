package com.project.upendoshop.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.upendoshop.Adapters.OnBoardingViewPagerAdapter;
import com.project.upendoshop.Auth.LoginActivity;
import com.project.upendoshop.R;

public class WelcomeActivity extends AppCompatActivity {

    ViewPager mOnBoardingViewPagerAdapter;
    LinearLayout mShowLayout;
    Button skipbtn;

    TextView[] dots;
    OnBoardingViewPagerAdapter onBoardingViewPagerAdapter;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preference= getSharedPreferences("PREFERENCE",MODE_PRIVATE);
        String FirstTime= preference.getString("FirstTimeInstall","");
        if (FirstTime.equals("Yes")){
            Intent intent=new Intent(   WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }else {
            SharedPreferences.Editor editor= preference.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();


        }
        setContentView(R.layout.activity_welcome);
        skipbtn = findViewById(R.id.skip_btn);

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        });
        mOnBoardingViewPagerAdapter = (ViewPager) findViewById(R.id.viewPager1);
        mShowLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        onBoardingViewPagerAdapter = new OnBoardingViewPagerAdapter(this);
        mOnBoardingViewPagerAdapter.setAdapter(onBoardingViewPagerAdapter);

        setUpIndicator(0);
        mOnBoardingViewPagerAdapter.addOnPageChangeListener(viewListener);

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setUpIndicator(int position){
        dots = new TextView[3];
        mShowLayout.removeAllViews();

        for (int i = 0; i < dots.length ; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.grey,getApplicationContext().getTheme()));
            mShowLayout.addView(dots[i]);


        }

        dots[position].setTextColor(getResources().getColor(R.color.yellow,getApplicationContext().getTheme()));
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {

            setUpIndicator(position);


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getitem(int i){
        return mOnBoardingViewPagerAdapter.getCurrentItem() + i;
    }
}