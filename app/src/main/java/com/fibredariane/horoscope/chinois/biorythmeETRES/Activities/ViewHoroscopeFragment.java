package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Element;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Horoscope;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.AdInterstitial;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.CalculBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.InfosBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;


public class ViewHoroscopeFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_HOROSCOPE = "horoscope";

  //  private Biorythme mCurrentBiorythme;
   // private Biorythme mPersoBiorythme;
   // private Binome mCurrentBinome;
    private TextView mTextViewHoroscopeYear;
    private TextView mTextViewHoroscopeMonth;
    private TextView mTextViewHoroscopeDay;
    private TextView mTextViewHoroscopeHour;
    private TextView mTextViewHoroscope;
    private TextView mTextViewHoroscopePolarite;
    private ImageView mImageViewHoroscopeMeteoYear;
    private ImageView mImageViewHoroscopeMeteoMonth;
    private ImageView mImageViewHoroscopeMeteoDay;
    private ImageView mImageViewHoroscopeMeteoHour;
    private ImageView mImageViewHoroscope;
    private Context mContext;
    private View rootView;
    private String mStringTypeHoro;
    private AdInterstitial mAdInterstitial;
    private Preferences mPreferences;
    private Horoscope mHoroscope;

    private LinearLayout mLinearLayoutElement;
    private LinearLayout mLinearLayoutHoroscope;
    private LinearLayout mLinearLayoutPasCredit;
    private Button mButtonAddCredit;

    public ViewHoroscopeFragment() {
    }

    public static ViewHoroscopeFragment newInstance(int sectionNumber, Horoscope horoscope) {
        ViewHoroscopeFragment fragment = new ViewHoroscopeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_HOROSCOPE,horoscope);
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

            if (date_biorythme == "") {
                Intent intent = new Intent(mContext, SwitchBiorythmeActivity.class);
                startActivity(intent);
            } else {
             //   mAdInterstitial = new AdInterstitial(this.getActivity(),mPreferences);

                initDayLayout();
                setVisibilityHoroscope();
            }
        }
        return rootView;
    }
    public void setVisibilityHoroscope() {

     if (!mPreferences.isDateSaved()) {

            if (mPreferences.getCreditPref() > 0) {
                mPreferences.removeCreditPref();
                mPreferences.setDateSauv();
                mLinearLayoutHoroscope.setVisibility(View.VISIBLE);
                mLinearLayoutPasCredit.setVisibility(View.GONE);
            } else {
                 mLinearLayoutHoroscope.setVisibility(View.GONE);
                mLinearLayoutPasCredit.setVisibility(View.VISIBLE);
            }

        }
        else{

         mLinearLayoutHoroscope.setVisibility(View.VISIBLE);
         mLinearLayoutPasCredit.setVisibility(View.GONE);
     }
    }

    public void initDayLayout() {

      //  mCurrentBiorythme = CalculBinomes.getCurrentBiorythme(mContext,mPreferences);
     //   mPersoBiorythme = mPreferences.getBiorythmePref();

        mTextViewHoroscopeYear = (TextView) rootView.findViewById(R.id.text_view_horoscope_annee);
        mTextViewHoroscopeMonth = (TextView) rootView.findViewById(R.id.text_view_horoscope_mois);
        mTextViewHoroscopeDay = (TextView) rootView.findViewById(R.id.text_view_horoscope_jour);
        mTextViewHoroscopeHour = (TextView) rootView.findViewById(R.id.text_view_horoscope_heure);

        mImageViewHoroscopeMeteoYear = (ImageView) rootView.findViewById(R.id.image_view_horoscope_meteo_annee);
        mImageViewHoroscopeMeteoMonth = (ImageView) rootView.findViewById(R.id.image_view_horoscope_meteo_mois);
        mImageViewHoroscopeMeteoDay = (ImageView) rootView.findViewById(R.id.image_view_horoscope_meteo_jour);
        mImageViewHoroscopeMeteoHour = (ImageView) rootView.findViewById(R.id.image_view_horoscope_meteo_heure);

        mTextViewHoroscope = (TextView) rootView.findViewById(R.id.text_view_horoscope_element);
        mTextViewHoroscopePolarite = (TextView) rootView.findViewById(R.id.text_view_horoscope_element_polarite);
        mImageViewHoroscope = (ImageView) rootView.findViewById(R.id.image_view_horoscope_element);


        mLinearLayoutPasCredit  = (LinearLayout) rootView.findViewById(R.id.linear_layout_pas_credit);
        mLinearLayoutHoroscope = (LinearLayout) rootView.findViewById(R.id.linear_layout_horoscope);
        mButtonAddCredit = (Button) rootView.findViewById(R.id.button_add_credit);
        mButtonAddCredit.setOnClickListener(this);

        mLinearLayoutElement = (LinearLayout) rootView.findViewById(R.id.linear_layout_element);
        mLinearLayoutElement.setOnClickListener(this);

        setHoroscope();

    }

    private void setHoroscope() {

     //   mCurrentBinome = CalculBinomes.getBinomeBiorythme(mCurrentBiorythme, typeHoro);
      //  Horoscope horo = new Horoscope(mContext,mPersoBiorythme,mCurrentBinome,typeHoro);

        if (mHoroscope.getIdImageInfluenceAnnee() == 0) {
            Log.v("TAG", "ViewHoroscopeFragment - JSON - erreur horoscope ");
        }else {
            mTextViewHoroscopeYear.setText(mHoroscope.getTextInfluenceAnnee());
            mTextViewHoroscopeMonth.setText(mHoroscope.getTextInfluenceMois());
            mTextViewHoroscopeDay.setText(mHoroscope.getTextInfluenceJour());
            mTextViewHoroscopeHour.setText(mHoroscope.getTextInfluenceHeure());

            mTextViewHoroscope.setText(mHoroscope.getElement());
            mTextViewHoroscopePolarite.setText(mHoroscope.getPolarite());

          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageViewHoroscopeMeteoYear.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceAnnee(), mContext.getApplicationContext().getTheme()));
            mImageViewHoroscopeMeteoMonth.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceMois(), mContext.getApplicationContext().getTheme()));
            mImageViewHoroscopeMeteoDay.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceJour(), mContext.getApplicationContext().getTheme()));
            mImageViewHoroscopeMeteoHour.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceHeure(), mContext.getApplicationContext().getTheme()));
            mImageViewHoroscope.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageElement(), mContext.getApplicationContext().getTheme()));
        } else {
            mImageViewHoroscopeMeteoYear.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceAnnee()));
            mImageViewHoroscopeMeteoMonth.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceMois()));
            mImageViewHoroscopeMeteoDay.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceJour()));
            mImageViewHoroscopeMeteoHour.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageInfluenceHeure()));
            mImageViewHoroscope.setImageDrawable(getResources().getDrawable(mHoroscope.getIdImageElement()));
        }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_element:
                Intent intent = new Intent(mContext,ViewElementActivity.class);
                intent.putExtra("ELEMENT",mTextViewHoroscope.getText());
                startActivity(intent);
                break;
            case R.id.button_add_credit:
                mAdInterstitial.setAd();
                break;
            default:
                break;
        }
    }
}

