package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.InfosBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;


public class ViewBinomeActivity extends AppCompatActivity {

    private Biorythme mBiorythme;
    private TextView mTextViewNomBinome;
    private TextView mTextViewDescBinome;
    private TextView getmTextViewYear;
    private TextView getmTextViewYearDesc;
    private ImageView mImageViewBinome;
    private Context mContext;
    private Preferences mPreferences;
    private static final int DIALOG_ALERT = 10;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_binome);

        mContext = this;
        mPreferences = new Preferences();

        mTextViewNomBinome =   (TextView) findViewById(R.id.text_view_name_binome);
        mTextViewDescBinome =   (TextView) findViewById(R.id.text_view_desc_binome);
        getmTextViewYear =   (TextView) findViewById(R.id.text_view_year);
        getmTextViewYearDesc =   (TextView) findViewById(R.id.text_view_year_desc);
        mImageViewBinome = (ImageView) findViewById(R.id.image_view_binome);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mBiorythme = (Biorythme) extras.getSerializable("BIORYTHME");
            mTextViewNomBinome.setText(mBiorythme.getBinomeAnnee().getNom());
            mTextViewDescBinome.setText(mBiorythme.getBinomeAnnee().getDescription());
            getmTextViewYear.setText(InfosBinomes.getStringAnnee(mBiorythme));
            getmTextViewYearDesc.setText(InfosBinomes.getStringAnneeTotale(mContext,mBiorythme));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                 mImageViewBinome.setImageDrawable(getResources().getDrawable(mBiorythme.getBinomeAnnee().getIntId(), getApplicationContext().getTheme()));
            } else {
                mImageViewBinome.setImageDrawable(getResources().getDrawable(mBiorythme.getBinomeAnnee().getIntId()));
            }
        }

    }

    public void onClickSauv(View view) {
        String date_biorythme = mPreferences.getStringDatePref();
       if (date_biorythme == "" || date_biorythme.equals(mBiorythme.getDateAnniversaire()) ) {
           mPreferences.setBiorythmePref(mBiorythme);
           Intent intent = new Intent(mContext, MainActivity.class);
           startActivity(intent);
       }else{
           showDialog(DIALOG_ALERT);
       }
    }
    public void onClickRetour(View view){
        finish();
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ALERT:
                // Create out AlterDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.changer_date_enregistree);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.sauvegarder, new OkOnClickListener());
                builder.setNegativeButton(R.string.retour_accueil, new CancelOnClickListener());
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onCreateDialog(id);

    }
    private final class CancelOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    }

    private final class OkOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            mPreferences.setBiorythmePref(mBiorythme);
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
        }
    }
}
