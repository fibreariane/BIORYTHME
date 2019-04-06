package com.fibredariane.horoscope.chinois.biorythmeETRES.Test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.App;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Horoscope;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.ManageRecordDB;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Carlotina on 16/12/2018.
 */

public class TestMain  extends AppCompatActivity implements View.OnClickListener {

    // private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private BottomNavigationView mNavigation;
    private BottomNavigationView mNavigationHoroscope;
    private BottomNavigationView mNavigationBiorythme;
    private BottomNavigationView mNavigationSynthese;
    private LinearLayout mLinearLayoutCredit;
    private TextView mTextViewCredit;
    private Preferences mPreferences;
    //
    private Biorythme mBiorythmeUser;
    private Biorythme mBiorythmeOfTheDay;
    private Horoscope mHoroscopeYear;
    private Horoscope mHoroscopeMonth;
    private Horoscope mHoroscopeDay;
    //
    private ManageRecordDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.test_activity_main);
     }

    @Override
    public void onClick(View v) {
        Context c = App.getContext();
        switch (v.getId()) {

            case R.id.btn_binome:
                Intent intent = new Intent(c, TestBinomeActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_biorythme:
                Intent intent2 = new Intent(c, TestBiorythmeActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_horoscope:
                Intent intent3 = new Intent(c, TestHoroscopeActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }

    }



    @Override
    protected void onResume() {
        super.onResume();
        //      db.openDBs();
    }

    @Override
    protected void onPause()  {
        super.onPause();
        //    db.closeDBs();
    }
}
