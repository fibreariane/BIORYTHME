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
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;


public class ViewBinomeFragment extends Fragment  {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_BINOME = "binome";
    private static final String ARG_BINOME_EXPL = "binome_expl";
    private static final String ARG_BINOME_TYPE = "binome_type";


    private Binome mBinome;
    private String mBinomeExpl;
    private String mBinomeType;
    private TextView mTextViewNomBinome;
    private TextView mTextViewPolariteBinome;
    private TextView mTextViewDescBinome;
    private TextView mTextViewBinomeType;
    private TextView mTextViewBinomeExpl;
    private ImageView mImageViewBinome;
    private Context mContext;
    private Preferences mPreferences;

    private View rootView;

    private LinearLayout mLinearLayoutElement;


    public ViewBinomeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ViewBinomeFragment newInstance(int sectionNumber, Binome binome, String binomeExpl,String binomeType) {
        ViewBinomeFragment fragment = new ViewBinomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_BINOME, binome);
        args.putString(ARG_BINOME_EXPL,binomeExpl);
        args.putString(ARG_BINOME_TYPE,binomeType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_binome, container, false);
        mContext = this.getActivity();
        if (mContext != null) {
            mPreferences = new Preferences();
            String date_biorythme = mPreferences.getStringDatePref();
            Bundle args = this.getArguments();
            mBinome = (Binome) args.getSerializable(ARG_BINOME);
            mBinomeExpl = args.getString(ARG_BINOME_EXPL);
            mBinomeType = args.getString(ARG_BINOME_TYPE);
            if (date_biorythme == ""){
                Intent intent = new Intent(mContext,SwitchBiorythmeActivity.class);
                startActivity(intent);
            }else{
                initBinomeLayout();
            }
        }
        return rootView;
    }


    public void initBinomeLayout(){

        mTextViewNomBinome = (TextView) rootView.findViewById(R.id.text_view_nom_binome);
        mTextViewPolariteBinome = (TextView) rootView.findViewById(R.id.text_view_polarite_binome);
        mTextViewDescBinome = (TextView) rootView.findViewById(R.id.text_view_desc_binome);
        mTextViewBinomeType = (TextView) rootView.findViewById(R.id.text_view_binome_type);;
        mTextViewBinomeExpl = (TextView) rootView.findViewById(R.id.text_view_binome_expl);;

        mImageViewBinome = (ImageView) rootView.findViewById(R.id.image_view_binome);

        setBinome();
    }

    private void setBinome() {
        if(mBinome.getNom() != "") {
            mImageViewBinome.setImageDrawable(getResources().getDrawable(mBinome.getIntId(), mContext.getApplicationContext().getTheme()));

            mTextViewNomBinome.setText(mBinome.getNom());
            mTextViewPolariteBinome.setText(mBinome.getPolarite());
            mTextViewDescBinome.setText(mBinome.getDescription());
            mTextViewNomBinome.setTextColor(getResources().getColor(mBinome.getElement().getIdColor()));
            mTextViewPolariteBinome.setTextColor(getResources().getColor(mBinome.getElement().getIdColor()));
            mTextViewBinomeExpl.setText(mBinomeExpl);
            mTextViewBinomeType.setText(mBinomeType);
        }
    }

}