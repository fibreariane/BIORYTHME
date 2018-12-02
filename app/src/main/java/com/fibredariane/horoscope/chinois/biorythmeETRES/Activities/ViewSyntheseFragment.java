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

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Element;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Synthese;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;


public class ViewSyntheseFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private ImageView mImageViewFeu;
    private ImageView mImageViewEau;
    private ImageView mImageViewTerre;
    private ImageView mImageViewMetal;
    private ImageView mImageViewBois;
    private ImageView mImageViewChevronSynthese;
    private Biorythme mBiorythme;
    private Synthese mSynthese;
    private Context mContext;
    private Preferences mPreferences;
    private LinearLayout mLinearLayoutSynthese;
    private LinearLayout mLinearLayoutLireSynthese;

    private View rootView;

    public ViewSyntheseFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ViewSyntheseFragment newInstance(int sectionNumber) {
        ViewSyntheseFragment fragment = new ViewSyntheseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_synthese, container, false);
        mContext = this.getActivity();
        if (mContext != null) {
            mPreferences = new Preferences(mContext);
            String date_biorythme = mPreferences.getStringDatePref();

            if (date_biorythme == ""){
                Intent intent = new Intent(mContext,SwitchBiorythmeActivity.class);
                startActivity(intent);
            }else{
                mBiorythme = mPreferences.getBiorythmePref();
                mSynthese = new Synthese(mBiorythme);
                initSyntheseLayout();
            }
        }
        return rootView;
    }


    private void initSyntheseLayout() {
        mImageViewFeu = (ImageView) rootView.findViewById(R.id.image_view_feu);
        mImageViewEau = (ImageView) rootView.findViewById(R.id.image_view_eau);
        mImageViewTerre = (ImageView) rootView.findViewById(R.id.image_view_terre);
        mImageViewMetal = (ImageView) rootView.findViewById(R.id.image_view_metal);
        mImageViewBois = (ImageView) rootView.findViewById(R.id.image_view_bois);
        mImageViewChevronSynthese = (ImageView) rootView.findViewById(R.id.image_view_chevron_synthese);

        mLinearLayoutSynthese = (LinearLayout) rootView.findViewById(R.id.linear_layout_synthese);
        mLinearLayoutLireSynthese = (LinearLayout) rootView.findViewById(R.id.linear_layout_lire_synthese);
        mLinearLayoutLireSynthese.setOnClickListener(this);

        setSynthese();
    }


    private void setSynthese() {
        setElement("FEU",mImageViewFeu);
        setElement("EAU",mImageViewEau);
        setElement("TERRE",mImageViewTerre);
        setElement("METAL",mImageViewMetal);
        setElement("BOIS",mImageViewBois);
    }
    private void setElement(String stringElement,ImageView imageView) {
        Element element = new Element(mContext, stringElement);
        int nbElement = mSynthese.getNbElement(stringElement);
        int idImage = element.getIntId();
        if (nbElement <= 1) {
            idImage = element.getIntIdVide();
            imageView.setPadding(15,15,15,15);
        }
        if (nbElement == 2 || nbElement == 3) {
            idImage = element.getIntIdMini();
            imageView.setPadding(15,15,15,15);
        }
        if (nbElement > 3) {
            idImage = element.getIntId();
            imageView.setPadding(0,0,0,0);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            imageView.setImageDrawable(getResources().getDrawable(idImage, mContext.getApplicationContext().getTheme()));
        } else {
            imageView.setImageDrawable(getResources().getDrawable(idImage));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_lire_synthese:
                if (mLinearLayoutSynthese.getVisibility() == View.GONE) {
                    mLinearLayoutSynthese.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevronSynthese.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_up, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevronSynthese.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_up));
                } else {
                    mLinearLayoutSynthese.setVisibility(View.GONE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevronSynthese.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_down, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevronSynthese.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_down));
                }
                break;
        }
    }
}