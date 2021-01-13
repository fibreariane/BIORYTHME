package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Horoscope;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.InfosBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;

import java.util.Calendar;


public class ViewHoroscopeFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_HOROSCOPE = "horoscope";
    private static final String ARG_BIORYTHME = "biorythme";
    private static final String ARG_BINOME = "binome";

    private TextView mTextViewHoroscopeYear;
    private TextView mTextViewHoroscopeMonth;
    private TextView mTextViewHoroscopeDay;
    private TextView mTextViewHoroscopeHour;
    private ImageView mImageViewHoroscopeMeteoYear;
    private ImageView mImageViewHoroscopeMeteoMonth;
    private ImageView mImageViewHoroscopeMeteoDay;
    private ImageView mImageViewHoroscopeMeteoHour;
    private ImageView mImageViewHoroscopeBioAnnee;
    private ImageView mImageViewHoroscopeBioMois;
    private ImageView mImageViewHoroscopeBioJour;
    private ImageView mImageViewHoroscopeBioHeure;

    private TextView mTextViewHoroscopeType;
    private TextView mTextViewHoroscopeExpl;
    private ImageView mImageViewHoroscope;

    private Context mContext;
    private View rootView;
    private Preferences mPreferences;
    private Horoscope mHoroscope;
    private Biorythme mBiorythmeUser;
    private Binome mBinomeCurrent;

    public ViewHoroscopeFragment() {
    }

    public static ViewHoroscopeFragment newInstance(int sectionNumber, Horoscope horoscope, Biorythme biorythme, Binome binome) {
        ViewHoroscopeFragment fragment = new ViewHoroscopeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_HOROSCOPE, horoscope);
        args.putSerializable(ARG_BIORYTHME, biorythme);
        args.putSerializable(ARG_BINOME, binome);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_horoscope, container, false);
        mContext = this.getContext();
        if (mContext != null) {
            mPreferences = new Preferences();
            String date_biorythme = mPreferences.getStringDatePref();
            Bundle args = this.getArguments();
            // mStringTypeHoro = args.getString(ARG_TYPE_HORO);
            mHoroscope = (Horoscope) args.getSerializable(ARG_HOROSCOPE);
            mBiorythmeUser = (Biorythme) args.getSerializable(ARG_BIORYTHME);
            mBinomeCurrent = (Binome) args.getSerializable(ARG_BINOME);

            if (date_biorythme == "") {
                Intent intent = new Intent(mContext, SwitchBiorythmeActivity.class);
                startActivity(intent);
            } else {

                initDayLayout();
            }
        }
        return rootView;
    }


    public void initDayLayout() {

        mTextViewHoroscopeYear = (TextView) rootView.findViewById(R.id.text_view_horoscope_annee);
        mTextViewHoroscopeMonth = (TextView) rootView.findViewById(R.id.text_view_horoscope_mois);
        mTextViewHoroscopeDay = (TextView) rootView.findViewById(R.id.text_view_horoscope_jour);
        mTextViewHoroscopeHour = (TextView) rootView.findViewById(R.id.text_view_horoscope_heure);

        mImageViewHoroscopeMeteoYear = (ImageView) rootView.findViewById(R.id.image_view_horoscope_meteo_annee);
        mImageViewHoroscopeMeteoMonth = (ImageView) rootView.findViewById(R.id.image_view_horoscope_meteo_mois);
        mImageViewHoroscopeMeteoDay = (ImageView) rootView.findViewById(R.id.image_view_horoscope_meteo_jour);
        mImageViewHoroscopeMeteoHour = (ImageView) rootView.findViewById(R.id.image_view_horoscope_meteo_heure);

        mImageViewHoroscopeBioAnnee = (ImageView) rootView.findViewById(R.id.image_view_horoscope_bio_annee);
        mImageViewHoroscopeBioMois = (ImageView) rootView.findViewById(R.id.image_view_horoscope_bio_mois);
        mImageViewHoroscopeBioJour = (ImageView) rootView.findViewById(R.id.image_view_horoscope_bio_jour);
        mImageViewHoroscopeBioHeure = (ImageView) rootView.findViewById(R.id.image_view_horoscope_bio_heure);

        mTextViewHoroscopeType = (TextView) rootView.findViewById(R.id.text_view_horoscope_type);
        mTextViewHoroscopeExpl = (TextView) rootView.findViewById(R.id.text_view_horoscope_expl);
        mImageViewHoroscope = (ImageView) rootView.findViewById(R.id.image_view_meteo_day);
        ;

        setHoroscope();

    }

    private void setHoroscope() {
        Calendar calendar = Calendar.getInstance();
        String dateJour = calendar.get(Calendar.DAY_OF_MONTH) +
                " " + getResources().getString(getResources().getIdentifier(
                "mois" + calendar.get(Calendar.MONTH),
                "string",
                mContext.getPackageName())).toUpperCase() +
                " " + calendar.get(Calendar.YEAR);

        if (mHoroscope.getIdImageInfluenceAnnee() == 0) {
            Log.v("TAG", "ViewHoroscopeFragment - JSON - erreur horoscope ");
        } else {
            mTextViewHoroscopeType.setText("HOROSCOPE DU " + dateJour);
            mTextViewHoroscopeExpl.setText("Découvrez votre horoscope du jour");

            mTextViewHoroscopeYear.setText(mHoroscope.getTextInfluenceAnnee());
            mTextViewHoroscopeMonth.setText(mHoroscope.getTextInfluenceMois());
            mTextViewHoroscopeDay.setText(mHoroscope.getTextInfluenceJour());
            mTextViewHoroscopeHour.setText(mHoroscope.getTextInfluenceHeure());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mImageViewHoroscope.setImageDrawable(getResources().getDrawable(InfosBinomes.getIdTotInfluence(mContext, mBinomeCurrent, mBiorythmeUser), mContext.getApplicationContext().getTheme()));
                mImageViewHoroscopeMeteoYear.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceAnnee(), mContext.getApplicationContext().getTheme()));
                mImageViewHoroscopeMeteoMonth.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceMois(), mContext.getApplicationContext().getTheme()));
                mImageViewHoroscopeMeteoDay.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceJour(), mContext.getApplicationContext().getTheme()));
                mImageViewHoroscopeMeteoHour.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceHeure(), mContext.getApplicationContext().getTheme()));
            }else{
                mImageViewHoroscope.setImageDrawable(getResources().getDrawable(InfosBinomes.getIdTotInfluence(mContext, mBinomeCurrent, mBiorythmeUser)));
                mImageViewHoroscopeMeteoYear.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceAnnee()));
                mImageViewHoroscopeMeteoMonth.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceMois()));
                mImageViewHoroscopeMeteoDay.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceJour()));
                mImageViewHoroscopeMeteoHour.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceHeure()));
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageViewHoroscopeBioAnnee.setImageDrawable(getResources().getDrawable(mBiorythmeUser.getBinomeAnnee().getIntIdMini(), mContext.getApplicationContext().getTheme()));
            mImageViewHoroscopeBioMois.setImageDrawable(getResources().getDrawable(mBiorythmeUser.getBinomeMois().getIntIdMini(), mContext.getApplicationContext().getTheme()));
            mImageViewHoroscopeBioJour.setImageDrawable(getResources().getDrawable(mBiorythmeUser.getBinomeJour().getIntIdMini(), mContext.getApplicationContext().getTheme()));
            mImageViewHoroscopeBioHeure.setImageDrawable(getResources().getDrawable(mBiorythmeUser.getBinomeHeure().getIntIdMini(), mContext.getApplicationContext().getTheme()));
        }else{
            mImageViewHoroscopeBioAnnee.setImageDrawable(getResources().getDrawable(mBiorythmeUser.getBinomeAnnee().getIntIdMini()));
            mImageViewHoroscopeBioMois.setImageDrawable(getResources().getDrawable(mBiorythmeUser.getBinomeMois().getIntIdMini()));
            mImageViewHoroscopeBioJour.setImageDrawable(getResources().getDrawable(mBiorythmeUser.getBinomeJour().getIntIdMini()));
            mImageViewHoroscopeBioHeure.setImageDrawable(getResources().getDrawable(mBiorythmeUser.getBinomeHeure().getIntIdMini()));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;
        }
    }
}

