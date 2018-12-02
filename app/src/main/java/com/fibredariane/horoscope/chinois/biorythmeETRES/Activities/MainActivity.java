package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Element;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Horoscope;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.AdInterstitial;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.AdVideo;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.CalculBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.ZoomOutPageTransformer;

//public class MainActivity extends AppCompatActivity implements View.OnClickListener,RewardedVideoAdListener {
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private BottomNavigationView mNavigation;
    private BottomNavigationView mNavigationHoroscope;
    private BottomNavigationView mNavigationBiorythme;
    private BottomNavigationView mNavigationSynthese;
    private Context mContext;
    private AdInterstitial mAdInterstitial;
    private LinearLayout mLinearLayoutCredit;
    private TextView mTextViewCredit;
    private Preferences mPreferences;
    //
    private Biorythme mBiorythmeUser;
    private Biorythme mBiorythmeOfTheDay;
    private Horoscope mHoroscopeYear;
    private Horoscope mHoroscopeMonth;
    private Horoscope mHoroscopeDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mPreferences = new Preferences(mContext);

        mPreferences.isFirstTimeApplication();

        String date_biorythme = mPreferences.getStringDatePref();
        if (date_biorythme == ""){
            Intent intent = new Intent(mContext,SwitchBiorythmeActivity.class);
            startActivity(intent);
        }else {
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
            mNavigation = (BottomNavigationView) findViewById(R.id.menu_main);
            mNavigationHoroscope = (BottomNavigationView) findViewById(R.id.menu_horoscope);
            mNavigationBiorythme = (BottomNavigationView) findViewById(R.id.menu_biorythme);
            mNavigationSynthese = (BottomNavigationView) findViewById(R.id.menu_synthese);

            mTextViewCredit = (TextView) findViewById(R.id.text_view_credit);

            mAdInterstitial = new AdInterstitial(this, mPreferences);

            mLinearLayoutCredit = (LinearLayout) findViewById(R.id.linear_layout_credit);
            mLinearLayoutCredit.setOnClickListener(this);

            // Initialisation des menus
            initMenuMain();
            initMenuHoroscope();
            initMenuBiorythme();
            initMenuSynthese();


            // Initialisation des variables globales
            mBiorythmeOfTheDay = CalculBinomes.getCurrentBiorythme(mContext, mPreferences);
            mBiorythmeUser = mPreferences.getBiorythmePref();
            mHoroscopeYear = new Horoscope(mContext, mBiorythmeUser, mBiorythmeOfTheDay.getBinomeAnnee(), "A");
            mHoroscopeMonth = new Horoscope(mContext, mBiorythmeUser, mBiorythmeOfTheDay.getBinomeMois(), "M");
            ;
            mHoroscopeDay = new Horoscope(mContext, mBiorythmeUser, mBiorythmeOfTheDay.getBinomeJour(), "J");
            ;

            //
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    mTextViewCredit.setText("" + mPreferences.getCreditPref());
                    switch (position) {
                        case 0:
                            mNavigation.getMenu().findItem(R.id.navigation_home).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.INVISIBLE);
                            mNavigationBiorythme.setVisibility(View.GONE);
                            mNavigationSynthese.setVisibility(View.GONE);
                            break;
                        case 1:
                            mNavigation.getMenu().findItem(R.id.navigation_horoscope).setChecked(true);
                            mNavigationHoroscope.getMenu().findItem(R.id.navigation_jour).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.VISIBLE);
                            mNavigationBiorythme.setVisibility(View.GONE);
                            mNavigationSynthese.setVisibility(View.GONE);

                            break;
                        case 2:
                            mNavigation.getMenu().findItem(R.id.navigation_horoscope).setChecked(true);
                            mNavigationHoroscope.getMenu().findItem(R.id.navigation_mois).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.VISIBLE);
                            mNavigationBiorythme.setVisibility(View.GONE);
                            mNavigationSynthese.setVisibility(View.GONE);
                            break;
                        case 3:
                            mNavigation.getMenu().findItem(R.id.navigation_horoscope).setChecked(true);
                            mNavigationHoroscope.getMenu().findItem(R.id.navigation_annee).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.VISIBLE);
                            mNavigationBiorythme.setVisibility(View.GONE);
                            mNavigationSynthese.setVisibility(View.GONE);
                            break;
                        case 4:
                            mNavigation.getMenu().findItem(R.id.navigation_biorythme).setChecked(true);
                            mNavigationBiorythme.getMenu().findItem(R.id.navigation_binome_annee).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.GONE);
                            mNavigationBiorythme.setVisibility(View.VISIBLE);
                            mNavigationSynthese.setVisibility(View.GONE);
                            break;
                        case 5:
                            mNavigation.getMenu().findItem(R.id.navigation_biorythme).setChecked(true);
                            mNavigationBiorythme.getMenu().findItem(R.id.navigation_binome_mois).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.GONE);
                            mNavigationBiorythme.setVisibility(View.VISIBLE);
                            mNavigationSynthese.setVisibility(View.GONE);
                            break;
                        case 6:
                            mNavigation.getMenu().findItem(R.id.navigation_biorythme).setChecked(true);
                            mNavigationBiorythme.getMenu().findItem(R.id.navigation_binome_jour).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.GONE);
                            mNavigationBiorythme.setVisibility(View.VISIBLE);
                            mNavigationSynthese.setVisibility(View.GONE);
                            break;
                        case 7:
                            mNavigation.getMenu().findItem(R.id.navigation_biorythme).setChecked(true);
                            mNavigationBiorythme.getMenu().findItem(R.id.navigation_binome_heure).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.GONE);
                            mNavigationBiorythme.setVisibility(View.VISIBLE);
                            mNavigationSynthese.setVisibility(View.GONE);
                            break;
                        case 8:
                            mNavigation.getMenu().findItem(R.id.navigation_synthese).setChecked(true);
                            mNavigationSynthese.getMenu().findItem(R.id.navigation_synthese_accueil).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.GONE);
                            mNavigationBiorythme.setVisibility(View.GONE);
                            mNavigationSynthese.setVisibility(View.VISIBLE);
                            break;
                        case 9:
                            mNavigation.getMenu().findItem(R.id.navigation_synthese).setChecked(true);
                            mNavigationSynthese.getMenu().findItem(R.id.navigation_synthese_vide).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.GONE);
                            mNavigationBiorythme.setVisibility(View.GONE);
                            mNavigationSynthese.setVisibility(View.VISIBLE);
                            break;
                        case 10:
                            mNavigation.getMenu().findItem(R.id.navigation_synthese).setChecked(true);
                            mNavigationSynthese.getMenu().findItem(R.id.navigation_synthese_neutre).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.GONE);
                            mNavigationBiorythme.setVisibility(View.GONE);
                            mNavigationSynthese.setVisibility(View.VISIBLE);
                            break;
                        case 11:
                            mNavigation.getMenu().findItem(R.id.navigation_synthese).setChecked(true);
                            mNavigationSynthese.getMenu().findItem(R.id.navigation_synthese_dominant).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.GONE);
                            mNavigationBiorythme.setVisibility(View.GONE);
                            mNavigationSynthese.setVisibility(View.VISIBLE);
                            break;
                        case 12:
                            mNavigation.getMenu().findItem(R.id.navigation_param).setChecked(true);
                            mNavigationHoroscope.setVisibility(View.INVISIBLE);
                            mNavigationBiorythme.setVisibility(View.GONE);
                            mNavigationSynthese.setVisibility(View.GONE);
                            break;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_horoscope:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.linear_layout_credit:
               // mAdInterstitial.setAd();
                break;
            default:
                break;
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
                        AccueilFragment accueilFragment = AccueilFragment.newInstance(position,mBiorythmeOfTheDay.getBinomeJour());
                        accueilFragment.setViewPager(mViewPager);
                        return accueilFragment;
                    case 1: // Horoscope Jour
                        return ViewHoroscopeFragment.newInstance(position, mHoroscopeDay);
                    case 2: // Horoscope Mois
                        return ViewHoroscopeFragment.newInstance(position, mHoroscopeMonth);
                    case 3: // Horoscope Année
                        return ViewHoroscopeFragment.newInstance(position, mHoroscopeYear);
                    case 4: // Biorythme Année
                        return ViewBiorythmeFragment.newInstance(position, mBiorythmeUser.getBinomeAnnee());
                    case 5: // Biorythme Mois
                        return ViewBiorythmeFragment.newInstance(position, mBiorythmeUser.getBinomeMois());
                    case 6: // Biorythme Jour
                        return ViewBiorythmeFragment.newInstance(position, mBiorythmeUser.getBinomeJour());
                    case 7: // Biorythme Heure
                        return ViewBiorythmeFragment.newInstance(position, mBiorythmeUser.getBinomeHeure());
                    case 8:
                        return ViewSyntheseFragment.newInstance(position);
                    case 9:
                        return ViewSyntheseDetailleFragment.newInstance(position, "VIDE");
                    case 10:
                        return ViewSyntheseDetailleFragment.newInstance(position, "NEUTRE");
                    case 11:
                        return ViewSyntheseDetailleFragment.newInstance(position, "DOMINANT");
                    case 12:
                        return ParameterFragment.newInstance(position);
                }

            return AccueilFragment.newInstance(position,mBiorythmeOfTheDay.getBinomeJour());
        }

        @Override
        public int getCount() {
            return 13;
        }
    }


    public void initMenuMain() {
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_horoscope:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_biorythme:
                        mViewPager.setCurrentItem(4);
                        return true;
                    case R.id.navigation_synthese:
                        mViewPager.setCurrentItem(8);
                        return true;
                    case R.id.navigation_param:
                        mViewPager.setCurrentItem(12);
                        return true;
                }
                return false;
            }

        };
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void initMenuHoroscope() {
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_jour:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_mois:
                        mViewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_annee:
                        mViewPager.setCurrentItem(3);
                        return true;

                }
                return false;
            }

        };
        mNavigationHoroscope.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void initMenuBiorythme() {
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_switch:
                        Intent intent = new Intent(mContext, SwitchBiorythmeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_binome_annee:
                        mViewPager.setCurrentItem(4);
                        return true;
                    case R.id.navigation_binome_mois:
                        mViewPager.setCurrentItem(5);
                        return true;
                    case R.id.navigation_binome_jour:
                        mViewPager.setCurrentItem(6);
                        return true;
                    case R.id.navigation_binome_heure:
                        mViewPager.setCurrentItem(7);
                        return true;

                }
                return false;
            }

        };
        mNavigationBiorythme.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initMenuSynthese() {
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_synthese_accueil:
                        mViewPager.setCurrentItem(8);
                        return true;
                    case R.id.navigation_synthese_vide:
                        mViewPager.setCurrentItem(9);
                        return true;
                    case R.id.navigation_synthese_neutre:
                        mViewPager.setCurrentItem(10);
                        return true;
                    case R.id.navigation_synthese_dominant:
                        mViewPager.setCurrentItem(11);
                        return true;

                }
                return false;
            }

        };
        mNavigationSynthese.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
