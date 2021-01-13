package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Element;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Synthese;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;


public class ViewSyntheseFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_BIORYTHME = "biorythme";

    private ImageView mImageViewFeu;
    private ImageView mImageViewEau;
    private ImageView mImageViewTerre;
    private ImageView mImageViewMetal;
    private ImageView mImageViewBois;
    private ImageView mImageViewSyntheseBioAnnee;
    private ImageView mImageViewSyntheseBioMois;
    private ImageView mImageViewSyntheseBioJour;
    private ImageView mImageViewSyntheseBioHeure;
    private TextView mTextViewNomBinomeA;
    private TextView mTextViewNomBinomeM;
    private TextView mTextViewNomBinomeJ;
    private TextView mTextViewNomBinomeH;
    private TextView mSyntheseType;
    private TextView mTextViewSyntheseElement;
    private Biorythme mBiorythme;
    private Synthese mSynthese;
    private Context mContext;
    private Preferences mPreferences;

    private View rootView;

    public ViewSyntheseFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ViewSyntheseFragment newInstance(int sectionNumber, Biorythme biorythme) {
        ViewSyntheseFragment fragment = new ViewSyntheseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_BIORYTHME, biorythme);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_synthese, container, false);
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
                mSynthese = new Synthese(mBiorythme);
                initSyntheseLayout();

                TextView textViewEnSavoirPlus = (TextView) rootView.findViewById(R.id.text_view_en_savoir_plus);
                textViewEnSavoirPlus.setOnClickListener(this);

                ImageView imageViewSwitchBiorythme = (ImageView) rootView.findViewById(R.id.image_view_switch_biorythme);
                imageViewSwitchBiorythme.setOnClickListener(this);
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

        mImageViewSyntheseBioAnnee = (ImageView) rootView.findViewById(R.id.image_view_synthese_bio_annee);
        mImageViewSyntheseBioMois = (ImageView) rootView.findViewById(R.id.image_view_synthese_bio_mois);
        mImageViewSyntheseBioJour = (ImageView) rootView.findViewById(R.id.image_view_synthese_bio_jour);
        mImageViewSyntheseBioHeure = (ImageView) rootView.findViewById(R.id.image_view_synthese_bio_heure);

        mTextViewNomBinomeA = (TextView) rootView.findViewById(R.id.text_view_nom_binomeA);
        mTextViewNomBinomeM = (TextView) rootView.findViewById(R.id.text_view_nom_binomeM);
        mTextViewNomBinomeJ = (TextView) rootView.findViewById(R.id.text_view_nom_binomeJ);
        mTextViewNomBinomeH = (TextView) rootView.findViewById(R.id.text_view_nom_binomeH);

        mSyntheseType = (TextView) rootView.findViewById(R.id.text_view_synthese_type);
        mTextViewSyntheseElement= (TextView) rootView.findViewById(R.id.text_view_synthese_element);
        setSynthese();
    }


    private void setSynthese() {

        mSyntheseType.setText("SYNTHESE DU " + mBiorythme.getDateString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageViewSyntheseBioAnnee.setImageDrawable(getResources().getDrawable(mBiorythme.getBinomeAnnee().getIntIdMini(), mContext.getApplicationContext().getTheme()));
            mImageViewSyntheseBioMois.setImageDrawable(getResources().getDrawable(mBiorythme.getBinomeMois().getIntIdMini(), mContext.getApplicationContext().getTheme()));
            mImageViewSyntheseBioJour.setImageDrawable(getResources().getDrawable(mBiorythme.getBinomeJour().getIntIdMini(), mContext.getApplicationContext().getTheme()));
            mImageViewSyntheseBioHeure.setImageDrawable(getResources().getDrawable(mBiorythme.getBinomeHeure().getIntIdMini(), mContext.getApplicationContext().getTheme()));
        }else{
            mImageViewSyntheseBioAnnee.setImageDrawable(getResources().getDrawable(mBiorythme.getBinomeAnnee().getIntIdMini()));
            mImageViewSyntheseBioMois.setImageDrawable(getResources().getDrawable(mBiorythme.getBinomeMois().getIntIdMini()));
            mImageViewSyntheseBioJour.setImageDrawable(getResources().getDrawable(mBiorythme.getBinomeJour().getIntIdMini()));
            mImageViewSyntheseBioHeure.setImageDrawable(getResources().getDrawable(mBiorythme.getBinomeHeure().getIntIdMini()));
        }
        mTextViewNomBinomeA.setText(mBiorythme.getBinomeAnnee().getNom());
        mTextViewNomBinomeM.setText(mBiorythme.getBinomeMois().getNom());
        mTextViewNomBinomeJ.setText(mBiorythme.getBinomeJour().getNom());
        mTextViewNomBinomeH.setText(mBiorythme.getBinomeHeure().getNom());

        setElement("FEU", mImageViewFeu);
        setElement("EAU", mImageViewEau);
        setElement("TERRE", mImageViewTerre);
        setElement("METAL", mImageViewMetal);
        setElement("BOIS", mImageViewBois);

        if ((mSynthese.getNbDominant() == 0) && (mSynthese.getNbVide() == 0)){
            mTextViewSyntheseElement.setText("Mes éléments sont équilibrés.");
        }else{
            mTextViewSyntheseElement.setText("Il y a "+mSynthese.getNbDominant()+" élément(s) dominant(s) et "+mSynthese.getNbVide()+" élément(s) vide(s).");
        }

    }

    private void setElement(String stringElement, ImageView imageView) {
        Element element = new Element(stringElement);
        int nbElement = mSynthese.getNbElement(stringElement);
        int idImage = element.getIntId();
        if (nbElement <= 1) {
            idImage = element.getIntIdVide();
            imageView.setPadding(15, 15, 15, 15);
        }
        if (nbElement == 2 || nbElement == 3) {
            idImage = element.getIntIdMini();
            imageView.setPadding(15, 15, 15, 15);
        }
        if (nbElement > 3) {
            idImage = element.getIntId();
            imageView.setPadding(0, 0, 0, 0);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setImageDrawable(getResources().getDrawable(idImage, mContext.getApplicationContext().getTheme()));
        } else {
            imageView.setImageDrawable(getResources().getDrawable(idImage));
        }

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.image_view_switch_biorythme:
                intent = new Intent(mContext, SwitchBiorythmeActivity.class);
                startActivity(intent);
                break;

            case R.id.text_view_en_savoir_plus:
                Bundle extras = new Bundle();
                extras.putSerializable(ARG_BIORYTHME, mBiorythme);
                intent = new Intent(mContext, ViewBiorythmeActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
                break;

        }

    }
}