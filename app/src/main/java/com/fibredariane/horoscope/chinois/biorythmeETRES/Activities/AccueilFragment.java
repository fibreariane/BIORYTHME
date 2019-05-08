package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.CalculBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.InfosBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;

import java.util.Calendar;

public class AccueilFragment extends Fragment implements View.OnClickListener{
    private Biorythme mCurrentBiorythme;
    private TextView mTextViewDay;
    private ImageView mImageViewDay;
    private ImageView mImageViewMeteoDay;
    private ImageView mImageViewUserAnnee;
    private ImageView mImageViewUserMois;
    private ImageView mImageViewUserJour;
    private ImageView mImageViewUserHeure;
    private ViewPager mViewPager;
    private Context mContext;
    private Preferences mPreference;
    private Binome mBinome;

    private View rootView;

    private LinearLayout mLinearLayoutHoroscope;
    private LinearLayout mLinearLayoutElement;
    private LinearLayout mLinearLayoutSynthese;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_BINOME = "binome";
    private static final String ARG_BIORYTHME = "biorythme";

    public AccueilFragment() {
    }


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AccueilFragment newInstance(int sectionNumber, Binome binomeJour, Biorythme biorythme) {
        AccueilFragment fragment = new AccueilFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_BINOME,binomeJour);
        args.putSerializable(ARG_BIORYTHME,biorythme);
        fragment.setArguments(args);
        return fragment;
    }
    public void setViewPager(ViewPager viewPager){
        mViewPager = viewPager;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_accueil, container, false);
        mContext = this.getActivity();
        if(mContext != null){
            mPreference = new Preferences();
            String date_biorythme = mPreference.getStringDatePref();

            Bundle args = this.getArguments();
            mBinome = (Binome) args.getSerializable(ARG_BINOME);
            mCurrentBiorythme = (Biorythme) args.getSerializable(ARG_BIORYTHME);

            if (date_biorythme == ""){
                Intent intent = new Intent(mContext,SwitchBiorythmeActivity.class);
                startActivity(intent);
            }else{
                initMainLayout();

               mLinearLayoutHoroscope = (LinearLayout) rootView.findViewById(R.id.layout_horoscope);
               mLinearLayoutElement = (LinearLayout) rootView.findViewById(R.id.layout_energie);
                mLinearLayoutSynthese = (LinearLayout) rootView.findViewById(R.id.layout_synthese);

                mLinearLayoutHoroscope.setOnClickListener(this);
                mLinearLayoutElement.setOnClickListener(this);
                mLinearLayoutSynthese.setOnClickListener(this);
            }

        }

        return rootView;
    }
    public void initMainLayout(){

        mImageViewDay = (ImageView) rootView.findViewById(R.id.image_view_day);
        mImageViewMeteoDay = (ImageView) rootView.findViewById(R.id.image_view_meteo_day);
        mTextViewDay = (TextView) rootView.findViewById(R.id.text_view_day);
        mImageViewUserAnnee =(ImageView) rootView.findViewById(R.id.image_view_bio_annee);
        mImageViewUserMois = (ImageView) rootView.findViewById(R.id.image_view_bio_mois);
        mImageViewUserJour= (ImageView) rootView.findViewById(R.id.image_view_bio_jour);
        mImageViewUserHeure=(ImageView) rootView.findViewById(R.id.image_view_bio_heure);

        setmImageViewDay();
        setmTextViewDay();
        setmImageViewMeteoDay();
        setmImageViewBiorythme();
    }

    private void setmImageViewDay(){
        if (mBinome.getNom() != "") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mImageViewDay.setImageDrawable(getResources().getDrawable(mBinome.getIntId(), mContext.getApplicationContext().getTheme()));
            } else {
                mImageViewDay.setImageDrawable(getResources().getDrawable(mBinome.getIntId()));
            }
        }
    }

    private void setmTextViewDay(){
        Calendar calendar = Calendar.getInstance();
        mTextViewDay.setText(""+calendar.get(Calendar.DAY_OF_MONTH)+
                " "+getResources().getString(getResources().getIdentifier(
                "mois"+calendar.get(Calendar.MONTH),
                "string",
                mContext.getPackageName())).toUpperCase()+
                " "+calendar.get(Calendar.YEAR));
        }

    private void setmImageViewMeteoDay(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageViewMeteoDay.setImageDrawable(getResources().getDrawable(InfosBinomes.getIdTotInfluenceAccueil(mContext), mContext.getApplicationContext().getTheme()));
        } else {
            mImageViewMeteoDay.setImageDrawable(getResources().getDrawable(InfosBinomes.getIdTotInfluenceAccueil(mContext)));
        }
    }

    private void setmImageViewBiorythme(){
        if (mCurrentBiorythme.getBinomeAnnee().getNom() != "") {
            mImageViewUserAnnee.setImageDrawable(getResources().getDrawable(mCurrentBiorythme.getBinomeAnnee().getIntIdMini(), mContext.getApplicationContext().getTheme()));
            mImageViewUserMois.setImageDrawable(getResources().getDrawable(mCurrentBiorythme.getBinomeMois().getIntIdMini(), mContext.getApplicationContext().getTheme()));
            mImageViewUserJour.setImageDrawable(getResources().getDrawable(mCurrentBiorythme.getBinomeJour().getIntIdMini(), mContext.getApplicationContext().getTheme()));
            mImageViewUserHeure.setImageDrawable(getResources().getDrawable(mCurrentBiorythme.getBinomeHeure().getIntIdMini(), mContext.getApplicationContext().getTheme()));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_energie:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.layout_horoscope:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.layout_synthese :
                mViewPager.setCurrentItem(3);
                break;

            default:
                break;
        }


    }
}

