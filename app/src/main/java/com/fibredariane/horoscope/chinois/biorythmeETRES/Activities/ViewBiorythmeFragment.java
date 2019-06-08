package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;


public class ViewBiorythmeFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_BIORYTHME = "biorythme";


    private Biorythme mBiorythme;

    private TextView mTextViewNomBinomeA;
    private TextView mTextViewPolariteBinomeA;
    private TextView mTextViewDescBinomeA;
    private ImageView mImageViewBinomeA;

    private TextView mTextViewNomBinomeM;
    private TextView mTextViewPolariteBinomeM;
    private TextView mTextViewDescBinomeM;
    private ImageView mImageViewBinomeM;

    private TextView mTextViewNomBinomeJ;
    private TextView mTextViewPolariteBinomeJ;
    private TextView mTextViewDescBinomeJ;
    private ImageView mImageViewBinomeJ;

    private TextView mTextViewNomBinomeH;
    private TextView mTextViewPolariteBinomeH;
    private TextView mTextViewDescBinomeH;
    private ImageView mImageViewBinomeH;

    private Context mContext;
    private Preferences mPreferences;

    private View rootView;

    public ViewBiorythmeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ViewBiorythmeFragment newInstance(int sectionNumber, Biorythme biorythme) {
        ViewBiorythmeFragment fragment = new ViewBiorythmeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_BIORYTHME, biorythme);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_biorythme, container, false);
        mContext = this.getActivity();
        if (mContext != null) {
            mPreferences = new Preferences();
            String date_biorythme = mPreferences.getStringDatePref();
            Bundle args = this.getArguments();
            mBiorythme = (Biorythme) args.getSerializable(ARG_BIORYTHME);

            if (date_biorythme == "") {
                Intent intent = new Intent(mContext, SwitchBiorythmeActivity.class);
                startActivity(intent);
            } else {
                initBiorythmeLayout();
            }
        }
        return rootView;
    }


    public void initBiorythmeLayout() {
        // Année
        mTextViewNomBinomeA = (TextView) rootView.findViewById(R.id.text_view_nom_binomeA);
        mTextViewPolariteBinomeA = (TextView) rootView.findViewById(R.id.text_view_polarite_binomeA);
        mTextViewDescBinomeA = (TextView) rootView.findViewById(R.id.text_view_desc_binomeA);
        mImageViewBinomeA = (ImageView) rootView.findViewById(R.id.image_view_binomeA);

        setBinome(mBiorythme.getBinomeAnnee(),
                mTextViewNomBinomeA,
                mTextViewPolariteBinomeA,
                mTextViewDescBinomeA,
                mImageViewBinomeA);

        // Mois
        mTextViewNomBinomeM = (TextView) rootView.findViewById(R.id.text_view_nom_binomeM);
        mTextViewPolariteBinomeM = (TextView) rootView.findViewById(R.id.text_view_polarite_binomeM);
        mTextViewDescBinomeM = (TextView) rootView.findViewById(R.id.text_view_desc_binomeM);
        mImageViewBinomeM = (ImageView) rootView.findViewById(R.id.image_view_binomeM);

        setBinome(mBiorythme.getBinomeMois(),
                mTextViewNomBinomeM,
                mTextViewPolariteBinomeM,
                mTextViewDescBinomeM,
                mImageViewBinomeM);

        // Jour
        mTextViewNomBinomeJ = (TextView) rootView.findViewById(R.id.text_view_nom_binomeJ);
        mTextViewPolariteBinomeJ = (TextView) rootView.findViewById(R.id.text_view_polarite_binomeJ);
        mTextViewDescBinomeJ = (TextView) rootView.findViewById(R.id.text_view_desc_binomeJ);
        mImageViewBinomeJ = (ImageView) rootView.findViewById(R.id.image_view_binomeJ);

        setBinome(mBiorythme.getBinomeJour(),
                mTextViewNomBinomeJ,
                mTextViewPolariteBinomeJ,
                mTextViewDescBinomeJ,
                mImageViewBinomeJ);

        // Heure
        mTextViewNomBinomeH = (TextView) rootView.findViewById(R.id.text_view_nom_binomeH);
        mTextViewPolariteBinomeH = (TextView) rootView.findViewById(R.id.text_view_polarite_binomeH);
        mTextViewDescBinomeH = (TextView) rootView.findViewById(R.id.text_view_desc_binomeH);
        mImageViewBinomeH = (ImageView) rootView.findViewById(R.id.image_view_binomeH);

        setBinome(mBiorythme.getBinomeHeure(),
                mTextViewNomBinomeH,
                mTextViewPolariteBinomeH,
                mTextViewDescBinomeH,
                mImageViewBinomeH);

    }

    private void setBinome(Binome binome,TextView nomBinome,TextView polariteBinome, TextView texteBinome, ImageView imageBinome) {
        if (binome.getNom() != "") {
            imageBinome.setImageDrawable(getResources().getDrawable(binome.getIntId()));
            nomBinome.setText(binome.getNom());
            polariteBinome.setText(binome.getPolarite());
            texteBinome.setText(binome.getDescription());
            nomBinome.setTextColor(getResources().getColor(binome.getElement().getIdColor()));
            polariteBinome.setTextColor(getResources().getColor(binome.getElement().getIdColor()));
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