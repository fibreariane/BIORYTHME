package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Horoscope;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.CalculBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;

import io.fabric.sdk.android.Fabric;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.ManageRecordDB;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.ZoomOutPageTransformer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Context mContext;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private Preferences mPreferences;
    //
    private Biorythme mBiorythmeUser;
    private Biorythme mBiorythmeOfTheDay;
    private Horoscope mHoroscopeDay;
    //
    private ManageRecordDB db;
    //
    private ImageButton mBtnAccueil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        db = new ManageRecordDB();
        db.initTables();
        mContext = this;

        mPreferences = new Preferences();

        mPreferences.isFirstTimeApplication();

        String date_biorythme = mPreferences.getStringDatePref();
        if ((date_biorythme == "") || !(biorythmeCorrect(mPreferences.getStringBiorythmePref()))) {
            mPreferences.resetBiorythmePref();
            Intent intent = new Intent(mContext, SwitchBiorythmeActivity.class);
            startActivity(intent);
        } else {
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

            mBtnAccueil = (ImageButton) findViewById(R.id.btn_accueil);

            // Initialisation des variables globales
            Date date = new Date();
            mBiorythmeOfTheDay = getBiorythme(date, CalculBinomes.getStringBinome(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes()));
            mBiorythmeUser = getBiorythme(mPreferences.getDatePref(), mPreferences.getStringBiorythmePref());
            mHoroscopeDay = db.getHoroscope(mBiorythmeOfTheDay.getBinomeJour(), mBiorythmeUser);
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    switch (position) {
                        default:
                            break;
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }

            });

        }
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    AccueilFragment accueilFragment = AccueilFragment.newInstance(position, mBiorythmeOfTheDay.getBinomeJour(), mBiorythmeUser);
                    accueilFragment.setViewPager(mViewPager);
                    return accueilFragment;
                case 1: // Binome du jour
                    Calendar calendar = Calendar.getInstance();
                    String dateJour = calendar.get(Calendar.DAY_OF_MONTH) +
                            " " + getResources().getString(getResources().getIdentifier(
                            "mois" + calendar.get(Calendar.MONTH),
                            "string",
                            mContext.getPackageName())).toUpperCase() +
                            " " + calendar.get(Calendar.YEAR);
                    return ViewBinomeFragment.newInstance(position, mBiorythmeOfTheDay.getBinomeJour(), "Découvrez sous quelle énergie cette journée est placée", "ENERGIE DU " + dateJour);
                case 2: // Horoscope Jour
                    return ViewHoroscopeFragment.newInstance(position, mHoroscopeDay, mBiorythmeUser,mBiorythmeOfTheDay.getBinomeJour());
                case 3: // Biorythme
                    return ViewSyntheseFragment.newInstance(position, mBiorythmeUser);
                case 4:
                    return ParameterFragment.newInstance(position);
            }

            return AccueilFragment.newInstance(position, mBiorythmeOfTheDay.getBinomeJour(), mBiorythmeUser);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accueil:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.btn_parametre:
                mViewPager.setCurrentItem(4);
                break;
             default:
                break;
        }

    }

    private Biorythme getBiorythme(Date date, String stringBiorythme) {
        String[] binomes = stringBiorythme.split("\\.");
        Binome binomeAnnee = db.getBinome(binomes[0], "A");
        Binome binomeMois = db.getBinome(binomes[1], "M");
        Binome binomeJour = db.getBinome(binomes[2], "J");
        Binome binomeHeure = db.getBinome(binomes[3], "H");
        return new Biorythme(date.getYear(),
                date.getMonth(),
                date.getDate(),
                date.getHours(),
                date.getMinutes(),
                binomeAnnee,
                binomeMois,
                binomeJour,
                binomeHeure);
    }
    private Boolean biorythmeCorrect(String stringBiorythme) {
        String[] binomes = stringBiorythme.split("\\.");

        return (binomes[0] != "0") &&
                (binomes[1] != "0") &&
                (binomes[2] != "0") &&
                (binomes[3] != "0") ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        db.openDBs();
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.closeDBs();
    }
}
