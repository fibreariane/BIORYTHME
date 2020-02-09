package com.fibredariane.horoscope.chinois.biorythmeETRES.Test;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Activities.MainActivity;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.InfosBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.ManageRecordDB;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;

import java.util.ArrayList;
import java.util.List;


public class TestBinomeActivity extends AppCompatActivity  implements View.OnClickListener  {

    private TextView mTextViewNomBinome;
    private TextView mTextViewDescBinome;
    private TextView mTextViewBrancheOrgane;
    private TextView mTextViewBranchePolarite;
    private TextView mTextViewBrancheElement;
    private TextView mTextViewTroncOrgane;
    private TextView mTextViewTroncPolarite;
    private TextView mTextViewTroncElement;
    private ImageView mImageViewBinome;
    private ImageView mImageViewBinomeMini;
    private Context mContext;
    private ManageRecordDB db;
    private Spinner spinnerBinome;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_view_binome);

        mContext = this;

        db = new ManageRecordDB();
        db.initTables();

        mTextViewNomBinome =   (TextView) findViewById(R.id.text_view_name_binome);
        mTextViewDescBinome =   (TextView) findViewById(R.id.text_view_desc_binome);

        mTextViewBrancheOrgane = (TextView) findViewById(R.id.text_view_branche_organe);
        mTextViewBranchePolarite = (TextView) findViewById(R.id.text_view_branche_polarite);
        mTextViewBrancheElement = (TextView) findViewById(R.id.text_view_branche_element);

        mTextViewTroncOrgane = (TextView) findViewById(R.id.text_view_tronc_organe);
        mTextViewTroncPolarite = (TextView) findViewById(R.id.text_view_tronc_polarite);
        mTextViewTroncElement = (TextView) findViewById(R.id.text_view_tronc_element);

        mImageViewBinome = (ImageView) findViewById(R.id.image_view_binome);
        mImageViewBinomeMini = (ImageView) findViewById(R.id.image_view_binome_mini);

        spinnerBinome = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i <= 60; i++) {
            list.add(Integer.toString(i));
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBinome.setAdapter(dataAdapter);
        Binome binome = db.getBinome("59","");


        mTextViewNomBinome.setText(binome.getNom());
        mTextViewDescBinome.setText(binome.getDescription());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageViewBinome.setImageDrawable(getResources().getDrawable(binome.getIntId(), getApplicationContext().getTheme()));
            mImageViewBinomeMini.setImageDrawable(getResources().getDrawable(binome.getIntIdMini(), getApplicationContext().getTheme()));
        }else{
            mImageViewBinome.setImageDrawable(getResources().getDrawable(binome.getIntId()));
            mImageViewBinomeMini.setImageDrawable(getResources().getDrawable(binome.getIntIdMini()));
        }

        mTextViewBrancheOrgane.setText(binome.getOrganeTroncCeleste().getNom());
        mTextViewBranchePolarite.setText(binome.getOrganeTroncCeleste().getPolarite());
        mTextViewBrancheElement.setText(binome.getOrganeTroncCeleste().getElement().getNom());

        mTextViewTroncOrgane.setText(binome.getOrganeBrancheTerrestre().getNom());
        mTextViewTroncPolarite.setText(binome.getOrganeBrancheTerrestre().getPolarite());
        mTextViewTroncElement.setText(binome.getOrganeBrancheTerrestre().getElement().getNom());

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                Binome binome = db.getBinome(String.valueOf(spinnerBinome.getSelectedItem()),"");

                mTextViewNomBinome.setText(binome.getNom());
                mTextViewDescBinome.setText(binome.getDescription());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageViewBinome.setImageDrawable(getResources().getDrawable(binome.getIntId(), getApplicationContext().getTheme()));
                    mImageViewBinomeMini.setImageDrawable(getResources().getDrawable(binome.getIntIdMini(), getApplicationContext().getTheme()));
                }else{
                    mImageViewBinome.setImageDrawable(getResources().getDrawable(binome.getIntId()));
                    mImageViewBinomeMini.setImageDrawable(getResources().getDrawable(binome.getIntIdMini()));
                }
                mTextViewBrancheOrgane.setText(binome.getOrganeTroncCeleste().getNom());
                mTextViewBranchePolarite.setText(binome.getOrganeTroncCeleste().getPolarite());
                mTextViewBrancheElement.setText(binome.getOrganeTroncCeleste().getElement().getNom());

                mTextViewTroncOrgane.setText(binome.getOrganeBrancheTerrestre().getNom());
                mTextViewTroncPolarite.setText(binome.getOrganeBrancheTerrestre().getPolarite());
                mTextViewTroncElement.setText(binome.getOrganeBrancheTerrestre().getElement().getNom());

                break;
            default:
                break;
        }

    }

}
