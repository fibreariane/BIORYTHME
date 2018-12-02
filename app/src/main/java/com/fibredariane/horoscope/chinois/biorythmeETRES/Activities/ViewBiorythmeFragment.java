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


public class ViewBiorythmeFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_BINOME = "binome";


    private Binome mBinome;
    private TextView mTextViewNomBinome;
    private TextView mTextViewElementBinome;
    private TextView mTextViewElementPolariteBinome;
    private TextView mTextViewElementMotCle1Binome;
    private TextView mTextViewElementMotCle2Binome;
    private TextView mTextViewDescBinome;
    private ImageView mImageViewBinome;
    private ImageView mImageViewElementBinome;
    private Context mContext;
    private Preferences mPreferences;

    private View rootView;

    private LinearLayout mLinearLayoutElement;


    public ViewBiorythmeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ViewBiorythmeFragment newInstance(int sectionNumber, Binome binome) {
        ViewBiorythmeFragment fragment = new ViewBiorythmeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_BINOME, binome);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_biorythme, container, false);
        mContext = this.getActivity();
        if (mContext != null) {
            mPreferences = new Preferences(mContext);
            String date_biorythme = mPreferences.getStringDatePref();
            Bundle args = this.getArguments();
           // mStringTypeBio = args.getString(ARG_TYPE_BIO);
            mBinome = (Binome) args.getSerializable(ARG_BINOME);

            if (date_biorythme == ""){
                Intent intent = new Intent(mContext,SwitchBiorythmeActivity.class);
                startActivity(intent);
            }else{
               // mBiorythme = mPreferences.getBiorythmePref();
                initBiorythmeLayout();
                mLinearLayoutElement = (LinearLayout) rootView.findViewById(R.id.linear_layout_element);
                mLinearLayoutElement.setOnClickListener(this);
            }
        }
        return rootView;
    }


    public void initBiorythmeLayout(){

        mTextViewNomBinome = (TextView) rootView.findViewById(R.id.text_view_nom_binome);
        mTextViewElementBinome = (TextView) rootView.findViewById(R.id.text_view_element_binome);
        mTextViewElementPolariteBinome = (TextView) rootView.findViewById(R.id.text_view_element_polarite_binome);
        mTextViewElementMotCle1Binome = (TextView) rootView.findViewById(R.id.text_view_element_mot_cle1_binome);
        mTextViewElementMotCle2Binome = (TextView) rootView.findViewById(R.id.text_view_element_mot_cle2_binome);
        mTextViewDescBinome = (TextView) rootView.findViewById(R.id.text_view_desc_binome);

        mImageViewBinome = (ImageView) rootView.findViewById(R.id.image_view_binome);
        mImageViewElementBinome = (ImageView) rootView.findViewById(R.id.image_view_element_binome);

        setBinome();
    }

    private void setBinome() {
       // Binome binome = CalculBinomes.getBinomeBiorythme(mBiorythme, typeBinome);
        if(mBinome.getNom() != "") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mImageViewBinome.setImageDrawable(getResources().getDrawable(mBinome.getIntIdMini(), mContext.getApplicationContext().getTheme()));
                mImageViewElementBinome.setImageDrawable(getResources().getDrawable(mBinome.getElement().getIntIdMini(), mContext.getApplicationContext().getTheme()));

            } else {
                mImageViewBinome.setImageDrawable(getResources().getDrawable(mBinome.getIntIdMini()));
                mImageViewElementBinome.setImageDrawable(getResources().getDrawable(mBinome.getElement().getIntIdMini()));

            }
            mTextViewNomBinome.setText(mBinome.getNom());
            mTextViewElementBinome.setText(mBinome.getElement().getNom());
            mTextViewElementPolariteBinome.setText(mBinome.getPolarite());
            mTextViewDescBinome.setText(mBinome.getDescription());
            mTextViewElementMotCle1Binome.setText(mBinome.getKey1());
            mTextViewElementMotCle2Binome.setText(mBinome.getKey2());
            mTextViewNomBinome.setTextColor(getResources().getColor(mBinome.getElement().getIdColor()));
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_element:
                Intent intent = new Intent(mContext,ViewElementActivity.class);
                intent.putExtra("ELEMENT",mTextViewElementBinome.getText());
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}