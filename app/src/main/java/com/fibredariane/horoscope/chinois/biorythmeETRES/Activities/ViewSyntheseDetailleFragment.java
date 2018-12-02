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

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Element;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Synthese;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;


public class ViewSyntheseDetailleFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_TYPE_DETAIL = "type_detail";

    private TextView mTextViewTitreSynthese;
    private ImageView mImageViewElement1;
    private ImageView mImageViewChevron1;
    private TextView mTextViewNomElement1;
    private TextView mTextViewDescElement1;
    private TextView mTextViewDescDetailElement1;
    private LinearLayout mLinearLayoutElement1;
    private ImageView mImageViewElement2;
    private ImageView mImageViewChevron2;
    private TextView mTextViewNomElement2;
    private TextView mTextViewDescElement2;
    private TextView mTextViewDescDetailElement2;
    private LinearLayout mLinearLayoutElement2;
    private ImageView mImageViewElement3;
    private ImageView mImageViewChevron3;
    private TextView mTextViewNomElement3;
    private TextView mTextViewDescElement3;
    private TextView mTextViewDescDetailElement3;
    private LinearLayout mLinearLayoutElement3;
    private ImageView mImageViewElement4;
    private ImageView mImageViewChevron4;
    private TextView mTextViewNomElement4;
    private TextView mTextViewDescElement4;
    private TextView mTextViewDescDetailElement4;
    private LinearLayout mLinearLayoutElement4;
    private ImageView mImageViewElement5;
    private ImageView mImageViewChevron5;
    private TextView mTextViewNomElement5;
    private TextView mTextViewDescElement5;
    private TextView mTextViewDescDetailElement5;
    private LinearLayout mLinearLayoutElement5;

    private TextView mTextViewPasElement;

    private Biorythme mBiorythme;
    private Synthese mSynthese;
    private Context mContext;
    private Preferences mPreferences;

    private String mTypeDetail;

    private View rootView;

    public ViewSyntheseDetailleFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ViewSyntheseDetailleFragment newInstance(int sectionNumber,String typeDetail) {
        ViewSyntheseDetailleFragment fragment = new ViewSyntheseDetailleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_TYPE_DETAIL, typeDetail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_synthese_detaille, container, false);
        mContext = this.getActivity();
        if (mContext != null) {
            mPreferences = new Preferences(mContext);
            String date_biorythme = mPreferences.getStringDatePref();
            Bundle args = this.getArguments();
            mTypeDetail = args.getString(ARG_TYPE_DETAIL);

            if (date_biorythme == ""){
                Intent intent = new Intent(mContext,SwitchBiorythmeActivity.class);
                startActivity(intent);
            }else{
                mBiorythme = mPreferences.getBiorythmePref();
                mSynthese = new Synthese(mBiorythme);
                initSyntheseDetailleLayout();
                SetSyntheseDetail(mTypeDetail);
            }
        }
        return rootView;
    }
    private void initSyntheseDetailleLayout() {

        mTextViewTitreSynthese = (TextView) rootView.findViewById(R.id.text_view_titre_synthese);

        mImageViewElement1 = (ImageView) rootView.findViewById(R.id.image_view_element1);
        mImageViewChevron1 = (ImageView) rootView.findViewById(R.id.image_view_chevron_element1);
        mImageViewChevron1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mTextViewDescDetailElement1.getVisibility() == View.GONE) {
                    mTextViewDescDetailElement1.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevron1.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_up, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevron1.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_up));
                } else {
                    mTextViewDescDetailElement1.setVisibility(View.GONE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevron1.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_down, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevron1.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_down));
                }
            }
        });
        mTextViewNomElement1 = (TextView) rootView.findViewById(R.id.text_view_nom_element1);
        mTextViewDescElement1 = (TextView) rootView.findViewById(R.id.text_view_desc_element1);
        mTextViewDescDetailElement1 = (TextView) rootView.findViewById(R.id.text_view_desc_detail_element1);
        mLinearLayoutElement1 = (LinearLayout) rootView.findViewById(R.id.linear_layout_element1);

        mImageViewElement2 = (ImageView) rootView.findViewById(R.id.image_view_element2);
        mImageViewChevron2 = (ImageView) rootView.findViewById(R.id.image_view_chevron_element2);
        mImageViewChevron2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mTextViewDescDetailElement2.getVisibility() == View.GONE) {
                    mTextViewDescDetailElement2.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevron2.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_up, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevron2.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_up));
                } else {
                    mTextViewDescDetailElement2.setVisibility(View.GONE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevron2.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_down, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevron2.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_down));
                }
            }
        });
        mTextViewNomElement2 = (TextView) rootView.findViewById(R.id.text_view_nom_element2);
        mTextViewDescElement2 = (TextView) rootView.findViewById(R.id.text_view_desc_element2);
        mTextViewDescDetailElement2 = (TextView) rootView.findViewById(R.id.text_view_desc_detail_element2);
        mLinearLayoutElement2 = (LinearLayout) rootView.findViewById(R.id.linear_layout_element2);

        mImageViewElement3 = (ImageView) rootView.findViewById(R.id.image_view_element3);
        mImageViewChevron3 = (ImageView) rootView.findViewById(R.id.image_view_chevron_element3);
        mImageViewChevron3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mTextViewDescDetailElement3.getVisibility() == View.GONE) {
                    mTextViewDescDetailElement3.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevron3.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_up, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevron3.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_up));
                } else {
                    mTextViewDescDetailElement3.setVisibility(View.GONE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevron3.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_down, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevron3.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_down));
                }
            }
        });
        mTextViewNomElement3 = (TextView) rootView.findViewById(R.id.text_view_nom_element3);
        mTextViewDescElement3 = (TextView) rootView.findViewById(R.id.text_view_desc_element3);
        mTextViewDescDetailElement3 = (TextView) rootView.findViewById(R.id.text_view_desc_detail_element3);
        mLinearLayoutElement3 = (LinearLayout) rootView.findViewById(R.id.linear_layout_element3);

        mImageViewElement4 = (ImageView) rootView.findViewById(R.id.image_view_element4);
        mImageViewChevron4 = (ImageView) rootView.findViewById(R.id.image_view_chevron_element4);
        mImageViewChevron4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mTextViewDescDetailElement4.getVisibility() == View.GONE) {
                    mTextViewDescDetailElement4.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevron4.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_up, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevron4.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_up));
                } else {
                    mTextViewDescDetailElement4.setVisibility(View.GONE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevron4.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_down, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevron4.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chevron_down));
                }
            }
        });
        mTextViewNomElement4 = (TextView) rootView.findViewById(R.id.text_view_nom_element4);
        mTextViewDescElement4 = (TextView) rootView.findViewById(R.id.text_view_desc_element4);
        mTextViewDescDetailElement4 = (TextView) rootView.findViewById(R.id.text_view_desc_detail_element4);
        mLinearLayoutElement4 = (LinearLayout) rootView.findViewById(R.id.linear_layout_element4);

        mImageViewElement5 = (ImageView) rootView.findViewById(R.id.image_view_element5);
        mImageViewChevron5 = (ImageView) rootView.findViewById(R.id.image_view_chevron_element5);
        mImageViewChevron5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mTextViewDescDetailElement5.getVisibility() == View.GONE) {
                    mTextViewDescDetailElement5.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevron5.setImageDrawable(rootView.getResources().getDrawable(R.drawable.chevron_up, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevron5.setImageDrawable(rootView.getResources().getDrawable(R.drawable.chevron_up));
                } else {
                    mTextViewDescDetailElement5.setVisibility(View.GONE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        mImageViewChevron5.setImageDrawable(rootView.getResources().getDrawable(R.drawable.chevron_down, mContext.getApplicationContext().getTheme()));
                    else
                        mImageViewChevron5.setImageDrawable(rootView.getResources().getDrawable(R.drawable.chevron_down));
                }
            }
        });
        mTextViewNomElement5 = (TextView) rootView.findViewById(R.id.text_view_nom_element5);
        mTextViewDescElement5 = (TextView) rootView.findViewById(R.id.text_view_desc_element5);
        mTextViewDescDetailElement5 = (TextView) rootView.findViewById(R.id.text_view_desc_detail_element5);
        mLinearLayoutElement5 = (LinearLayout) rootView.findViewById(R.id.linear_layout_element5);

        mTextViewPasElement = (TextView) rootView.findViewById(R.id.text_view_pas_element_synthese);
    }


    private void SetSyntheseDetail(String type) {

        switch (type) {
            case "DOMINANT":
                mTextViewTitreSynthese.setText(R.string.element_dominant);
                if (mSynthese.getNbDominant() == 0)
                    mTextViewPasElement.setText(R.string.pas_element_dominant);
                else
                    mTextViewPasElement.setText("");
                setDetailElement("FEU","DOMINANT", mImageViewElement1, mTextViewNomElement1, mTextViewDescElement1, mTextViewDescDetailElement1,mLinearLayoutElement1);
                setDetailElement("TERRE","DOMINANT", mImageViewElement2, mTextViewNomElement2, mTextViewDescElement2, mTextViewDescDetailElement2,mLinearLayoutElement2);
                setDetailElement("METAL","DOMINANT", mImageViewElement3, mTextViewNomElement3, mTextViewDescElement3, mTextViewDescDetailElement3,mLinearLayoutElement3);
                setDetailElement("EAU","DOMINANT", mImageViewElement4, mTextViewNomElement4, mTextViewDescElement4, mTextViewDescDetailElement4,mLinearLayoutElement4);
                setDetailElement("BOIS","DOMINANT", mImageViewElement5, mTextViewNomElement5, mTextViewDescElement5, mTextViewDescDetailElement5,mLinearLayoutElement5);
                break;
            case "VIDE":

                mTextViewTitreSynthese.setText(R.string.element_vide);
                if (mSynthese.getNbVide() == 0)
                    mTextViewPasElement.setText(R.string.pas_element_vide);
                else
                    mTextViewPasElement.setText("");
                setDetailElement("FEU","VIDE", mImageViewElement1, mTextViewNomElement1, mTextViewDescElement1, mTextViewDescDetailElement1,mLinearLayoutElement1);
                setDetailElement("TERRE","VIDE", mImageViewElement2, mTextViewNomElement2, mTextViewDescElement2, mTextViewDescDetailElement2,mLinearLayoutElement2);
                setDetailElement("METAL","VIDE", mImageViewElement3, mTextViewNomElement3, mTextViewDescElement3, mTextViewDescDetailElement3,mLinearLayoutElement3);
                setDetailElement("EAU","VIDE", mImageViewElement4, mTextViewNomElement4, mTextViewDescElement4, mTextViewDescDetailElement4,mLinearLayoutElement4);
                setDetailElement("BOIS","VIDE", mImageViewElement5, mTextViewNomElement5, mTextViewDescElement5, mTextViewDescDetailElement5,mLinearLayoutElement5);

                break;
            case "NEUTRE":

                mTextViewTitreSynthese.setText(R.string.element_neutre);
                if (mSynthese.getNbNeutre() == 0)
                    mTextViewPasElement.setText(R.string.pas_element_neutre);
                else
                    mTextViewPasElement.setText("");
                setDetailElement("FEU","NEUTRE", mImageViewElement1, mTextViewNomElement1, mTextViewDescElement1, mTextViewDescDetailElement1,mLinearLayoutElement1);
                setDetailElement("TERRE","NEUTRE", mImageViewElement2, mTextViewNomElement2, mTextViewDescElement2, mTextViewDescDetailElement2,mLinearLayoutElement2);
                setDetailElement("METAL","NEUTRE", mImageViewElement3, mTextViewNomElement3, mTextViewDescElement3, mTextViewDescDetailElement3,mLinearLayoutElement3);
                setDetailElement("EAU","NEUTRE", mImageViewElement4, mTextViewNomElement4, mTextViewDescElement4, mTextViewDescDetailElement4,mLinearLayoutElement4);
                setDetailElement("BOIS","NEUTRE", mImageViewElement5, mTextViewNomElement5, mTextViewDescElement5, mTextViewDescDetailElement5,mLinearLayoutElement5);

                break;
        }
    }

    private void setDetailElement(String stringElement,String type, ImageView imageView, TextView textViewNom, TextView textViewDesc, TextView textViewDescDetail,LinearLayout linearLayout) {
        Element element = new Element(mContext, stringElement);
        int nbElement = mSynthese.getNbElement(stringElement);

        int idImage = element.getIntId();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(idImage, mContext.getApplicationContext().getTheme()));
        } else {
            imageView.setImageDrawable(mContext.getResources().getDrawable(idImage));
        }

        switch (type){
            case "DOMINANT":
                textViewDescDetail.setText(element.getTextDominant());
                if (mSynthese.isDominant(stringElement))
                    linearLayout.setVisibility(View.VISIBLE);
                else
                    linearLayout.setVisibility(View.GONE);

                break;
            case "NEUTRE":
                textViewDescDetail.setText(element.getTextNeutre());
                if (mSynthese.isNeutre(stringElement))
                    linearLayout.setVisibility(View.VISIBLE);
                else
                    linearLayout.setVisibility(View.GONE);
                break;
            case "VIDE":
                textViewDescDetail.setText(element.getTextVide());
                if (mSynthese.isVide(stringElement))
                    linearLayout.setVisibility(View.VISIBLE);
                else
                    linearLayout.setVisibility(View.GONE);
                break;
        }

        textViewDesc.setText(element.getDesc());
        textViewNom.setText(element.getNom());

    }

}