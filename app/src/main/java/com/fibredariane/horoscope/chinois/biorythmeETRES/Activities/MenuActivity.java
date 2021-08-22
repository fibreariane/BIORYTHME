package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Horoscope;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.CalculBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.ManageRecordDB;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.ZoomOutPageTransformer;

import java.time.*;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {


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
        setContentView(R.layout.fragment_main);

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

            mViewPager = findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

            mBtnAccueil = findViewById(R.id.btn_accueil);
            findViewById(R.id.layout_menu_secondaire).bringToFront();


            // Initialisation des variables globales
            LocalDateTime date = LocalDateTime.now();
            mBiorythmeOfTheDay = getBiorythme(date, CalculBinomes.getStringBinome(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
            mBiorythmeUser = getBiorythme(date_biorythme, mPreferences.getStringBiorythmePref());
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
                    LocalDate localDate = LocalDate.now();
                    String dateJour = localDate.getDayOfMonth() +
                            " " + getResources().getString(getResources().getIdentifier(
                            "mois" + localDate.getMonthValue(),
                            "string",
                            mContext.getPackageName())).toUpperCase() +
                            " " + localDate.getYear();
                    return ViewBinomeFragment.newInstance(position, mBiorythmeOfTheDay.getBinomeJour(), "Découvrez sous quelle énergie cette journée est placée", "ENERGIE DU " + dateJour);
                case 2: // Horoscope Jour
                    return ViewHoroscopeFragment.newInstance(position, mHoroscopeDay, mBiorythmeUser, mBiorythmeOfTheDay.getBinomeJour());
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
        String url;
        Intent i;
        switch (v.getId()) {
            case R.id.btn_accueil:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.btn_parametre:
                mViewPager.setCurrentItem(4);
                break;
            case R.id.btn_etres:
                url = "https://www.etrescosmetique.com/cosmetique-presentation";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.btn_etres_produit:
                url = "https://www.etrescosmetique.com/la-gamme-des-produits-naturels";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.btn_etres_pros:
                url = "https://www.etrescosmetique.com/la-carte-geographique-des-membres";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            default:
                break;
        }

    }

    private Biorythme getBiorythme(LocalDateTime date, String stringBiorythme) {
        String[] binomes = stringBiorythme.split("\\.");
        Binome binomeAnnee = db.getBinome(binomes[0], "A");
        Binome binomeMois = db.getBinome(binomes[1], "M");
        Binome binomeJour = db.getBinome(binomes[2], "J");
        Binome binomeHeure = db.getBinome(binomes[3], "H");
        return new Biorythme(date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth(),
                date.getHour(),
                date.getMinute(),
                binomeAnnee,
                binomeMois,
                binomeJour,
                binomeHeure);
    }

    private Biorythme getBiorythme(String dateString, String stringBiorythme) {
        String[] dates = dateString.split("\\.");
        String[] binomes = stringBiorythme.split("\\.");
        Binome binomeAnnee = db.getBinome(binomes[0], "A");
        Binome binomeMois = db.getBinome(binomes[1], "M");
        Binome binomeJour = db.getBinome(binomes[2], "J");
        Binome binomeHeure = db.getBinome(binomes[3], "H");
        return new Biorythme(Integer.parseInt(dates[0]),
                Integer.parseInt(dates[1]),
                Integer.parseInt(dates[2]),
                Integer.parseInt(dates[3]),
                Integer.parseInt(dates[4]),
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
                (binomes[3] != "0");
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
