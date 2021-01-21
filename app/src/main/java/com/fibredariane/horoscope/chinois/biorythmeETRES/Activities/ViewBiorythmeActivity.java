package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;


public class ViewBiorythmeActivity extends AppCompatActivity  implements View.OnClickListener{

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_biorythme);

        mContext = this;
        mPreferences = new Preferences();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mBiorythme = (Biorythme) extras.getSerializable(ARG_BIORYTHME);
            initBiorythmeLayout();
        }

    }
    public void initBiorythmeLayout() {
        // Année
        mTextViewNomBinomeA = findViewById(R.id.text_view_nom_binomeA);
        mTextViewPolariteBinomeA = findViewById(R.id.text_view_polarite_binomeA);
        mTextViewDescBinomeA = findViewById(R.id.text_view_desc_binomeA);
        mImageViewBinomeA = findViewById(R.id.image_view_binomeA);

        setBinome(mBiorythme.getBinomeAnnee(),
                mTextViewNomBinomeA,
                mTextViewPolariteBinomeA,
                mTextViewDescBinomeA,
                mImageViewBinomeA);

        // Mois
        mTextViewNomBinomeM = findViewById(R.id.text_view_nom_binomeM);
        mTextViewPolariteBinomeM = findViewById(R.id.text_view_polarite_binomeM);
        mTextViewDescBinomeM = findViewById(R.id.text_view_desc_binomeM);
        mImageViewBinomeM = findViewById(R.id.image_view_binomeM);

        setBinome(mBiorythme.getBinomeMois(),
                mTextViewNomBinomeM,
                mTextViewPolariteBinomeM,
                mTextViewDescBinomeM,
                mImageViewBinomeM);

        // Jour
        mTextViewNomBinomeJ = findViewById(R.id.text_view_nom_binomeJ);
        mTextViewPolariteBinomeJ = findViewById(R.id.text_view_polarite_binomeJ);
        mTextViewDescBinomeJ = findViewById(R.id.text_view_desc_binomeJ);
        mImageViewBinomeJ = findViewById(R.id.image_view_binomeJ);

        setBinome(mBiorythme.getBinomeJour(),
                mTextViewNomBinomeJ,
                mTextViewPolariteBinomeJ,
                mTextViewDescBinomeJ,
                mImageViewBinomeJ);

        // Heure
        mTextViewNomBinomeH = findViewById(R.id.text_view_nom_binomeH);
        mTextViewPolariteBinomeH = findViewById(R.id.text_view_polarite_binomeH);
        mTextViewDescBinomeH = findViewById(R.id.text_view_desc_binomeH);
        mImageViewBinomeH = findViewById(R.id.image_view_binomeH);

        setBinome(mBiorythme.getBinomeHeure(),
                mTextViewNomBinomeH,
                mTextViewPolariteBinomeH,
                mTextViewDescBinomeH,
                mImageViewBinomeH);

    }

    private void setBinome(Binome binome, TextView nomBinome, TextView polariteBinome, TextView texteBinome, ImageView imageBinome) {
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
    public void onClick(View view) {

    }

}
