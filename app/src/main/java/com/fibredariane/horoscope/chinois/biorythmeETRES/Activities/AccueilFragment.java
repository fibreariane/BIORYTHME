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
   // private Biorythme mCurrentBiorythme;
    private TextView mTextViewDayDay;
    private TextView mTextViewDayMonth;
    private TextView mTextViewDayYear;
    private TextView mTextViewElementDay;
    private TextView mTextViewElementPolariteDay;
    private TextView mTextViewEnergyDay;
    private ImageView mImageViewDay;
    private ImageView mImageViewMeteoDay;
    private ViewPager mViewPager;
    private Context mContext;
    private Preferences mPreference;
    private Binome mBinome;

    private View rootView;

    private LinearLayout mLinearLayoutHoroscope;
    private LinearLayout mLinearLayoutElement;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_BINOME = "binome";

    public AccueilFragment() {
    }


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AccueilFragment newInstance(int sectionNumber, Binome binome) {
        AccueilFragment fragment = new AccueilFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_BINOME,binome);
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

            if (date_biorythme == ""){
                Intent intent = new Intent(mContext,SwitchBiorythmeActivity.class);
                startActivity(intent);
            }else{
                initMainLayout();

                mLinearLayoutHoroscope = (LinearLayout) rootView.findViewById(R.id.linear_layout_horoscope);
                mLinearLayoutElement = (LinearLayout) rootView.findViewById(R.id.linear_layout_element);

                mLinearLayoutHoroscope.setOnClickListener(this);
                mLinearLayoutElement.setOnClickListener(this);
            }
        }

        return rootView;
    }
    public void initMainLayout(){

    //    mCurrentBiorythme = CalculBinomes.getCurrentBiorythme(mContext,mPreference);

        mImageViewDay = (ImageView) rootView.findViewById(R.id.image_view_day);
        mImageViewMeteoDay = (ImageView) rootView.findViewById(R.id.image_view_meteo_day);
        mTextViewDayDay = (TextView) rootView.findViewById(R.id.text_view_day_day);
        mTextViewDayMonth = (TextView) rootView.findViewById(R.id.text_view_day_month);
        mTextViewDayYear = (TextView) rootView.findViewById(R.id.text_view_day_year);
        mTextViewElementDay = (TextView) rootView.findViewById(R.id.text_view_element_day);
        mTextViewElementPolariteDay = (TextView) rootView.findViewById(R.id.text_view_element_polarite_day);
        mTextViewEnergyDay = (TextView) rootView.findViewById(R.id.text_view_energy_day);

        setmImageViewDay();
        setmTextViewDay();
        setmTextViewElementDay();
        setmTextViewElementPolariteDay();
        setmImageViewMeteoDay();
        setmTextViewEnergyDay();
    }

    private void setmImageViewDay(){
        if (mBinome.getNom() != "") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mImageViewDay.setImageDrawable(getResources().getDrawable(mBinome.getIntIdMini(), mContext.getApplicationContext().getTheme()));
            } else {
                mImageViewDay.setImageDrawable(getResources().getDrawable(mBinome.getIntIdMini()));
            }
        }
    }
    private void setmTextViewElementDay(){
        if (mBinome.getNom() != "")
            mTextViewElementDay.setText(mBinome.getElement().getNom());
    }
    private void setmTextViewElementPolariteDay(){
        if (mBinome.getNom() != "")
        mTextViewElementPolariteDay.setText(mBinome.getPolarite());
    }

    private void setmTextViewDay(){
        Calendar calendar = Calendar.getInstance();
        mTextViewDayDay.setText(""+calendar.get(Calendar.DAY_OF_MONTH));
        mTextViewDayMonth.setText(""+getResources().getString(getResources().getIdentifier(
                "mois"+calendar.get(Calendar.MONTH),
                "string",
                mContext.getPackageName())).toUpperCase());
        mTextViewDayYear.setText(""+calendar.get(Calendar.YEAR));
    }

    private void setmImageViewMeteoDay(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageViewMeteoDay.setImageDrawable(getResources().getDrawable(InfosBinomes.getIdTotInfluenceAccueil(mContext), mContext.getApplicationContext().getTheme()));
        } else {
            mImageViewMeteoDay.setImageDrawable(getResources().getDrawable(InfosBinomes.getIdTotInfluenceAccueil(mContext)));
        }
    }
    private void setmTextViewEnergyDay(){
        String txt_tendance = getString(R.string.influence_creation);
        if(( mBinome.getElement().getNom().equals("BOIS") &&
                mBinome.getPolarite().equals("YANG")) ||
                ( mBinome.getElement().getNom().equals("TERRE") &&
                        mBinome.getPolarite().equals("YIN")))
            txt_tendance = getString(R.string.influence_creation);

        if((mBinome.getElement().getNom().equals("FEU") &&
                mBinome.getPolarite().equals("YANG")) ||
                ( mBinome.getElement().getNom().equals("METAL") &&
                        mBinome.getPolarite().equals("YIN")))
            txt_tendance = getString(R.string.influence_communication);

        if(( mBinome.getElement().getNom().equals("TERRE") &&
                mBinome.getPolarite().equals("YANG")) ||
                ( mBinome.getElement().getNom().equals("EAU") &&
                        mBinome.getPolarite().equals("YIN")))
            txt_tendance = getString(R.string.influence_realisation);

        if(( mBinome.getElement().getNom().equals("METAL") &&
                mBinome.getPolarite().equals("YANG")) ||
                ( mBinome.getElement().getNom().equals("BOIS") &&
                        mBinome.getPolarite().equals("YIN")))
            txt_tendance = getString(R.string.influence_sensation);

        if(( mBinome.getElement().getNom().equals("EAU") &&
                mBinome.getPolarite().equals("YANG")) ||
                ( mBinome.getElement().getNom().equals("FEU") &&
                        mBinome.getPolarite().equals("YIN")))
            txt_tendance = getString(R.string.influence_lucidite);

        mTextViewEnergyDay.setText(txt_tendance);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_horoscope:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.linear_layout_element:
                Intent intent = new Intent(mContext,ViewElementActivity.class);
                intent.putExtra("ELEMENT",mBinome.getElement().getNom());
                startActivity(intent);
                break;
            default:
                break;
        }


    }
}

