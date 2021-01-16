package com.fibredariane.horoscope.chinois.biorythmeETRES.Test;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.CalculBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.ManageRecordDB;

import java.util.ArrayList;
import java.util.List;
import java.time.*;


public class TestBiorythmeActivity extends AppCompatActivity  implements TimePickerDialog.OnTimeSetListener,View.OnClickListener  {

    private TextView mTextViewBinomeAnnee;
    private TextView mTextViewBinomeMois;
    private TextView mTextViewBinomeJour;
    private TextView mTextViewBinomeHeure;
    private EditText mEditTextDateJour;
    private EditText mEditTextDateMois;
    private EditText mEditTextDateAnnee;
    private LinearLayout mLinearHeure;
    private TextView mTextViewHour;
    static final int TIME_DIALOG_ID = 999;

    private int mHour;
    private int mMinute;

    private Context mContext;
    private ManageRecordDB db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_view_biorythme);

        mContext = this;

        db = new ManageRecordDB();
        db.initTables();

        mTextViewBinomeAnnee =   (TextView) findViewById(R.id.text_view_name_binome_annee);
        mTextViewBinomeMois =   (TextView) findViewById(R.id.text_view_name_binome_mois);
        mTextViewBinomeJour =   (TextView) findViewById(R.id.text_view_name_binome_jour);
        mTextViewBinomeHeure=   (TextView) findViewById(R.id.text_view_name_binome_heure);

        mEditTextDateJour = (EditText) findViewById(R.id.text_view_date_jour);
        mEditTextDateMois = (EditText) findViewById(R.id.text_view_date_mois);
        mEditTextDateAnnee = (EditText) findViewById(R.id.text_view_date_annee);

        mLinearHeure = (LinearLayout) findViewById(R.id.linear_layout_heure);
        mTextViewHour = (TextView) findViewById(R.id.text_view_hour);
        mLinearHeure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });


        mMinute = LocalDateTime.now().getMinute();
        mHour = LocalDateTime.now().getHour();
        // set current time into textview
        mTextViewHour.setText(
                new StringBuilder().append(pad(mHour))
                        .append(":").append(pad(mMinute)));



        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= 60; i++) {
            list.add(Integer.toString(i));
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // spinnerBinome.setAdapter(dataAdapter);

        int year = 2019;// Integer.parseInt(mEditTextDateAnnee.getText().toString());
        int month = 2;//Integer.parseInt(mEditTextDateMois.getText().toString());
        int day =3;// Integer.parseInt(mEditTextDateJour.getText().toString());

        Biorythme biorythme = new Biorythme(
                year,
                month,
                day,
                mHour,
                mMinute,
                db.getBinome(CalculBinomes.calcBinomeAnnee(year, month, day,mHour, mMinute),""),
                db.getBinome(CalculBinomes.calcBinomeMois(year, month, day,mHour, mMinute),""),
                db.getBinome(CalculBinomes.calcBinomeJour(year, month, day,mHour, mMinute),""),
                db.getBinome(CalculBinomes.calcBinomeHeure(year, month, day,mHour, mMinute),""));

        mTextViewBinomeAnnee.setText(biorythme.getBinomeAnnee().getNom());
        mTextViewBinomeMois.setText(biorythme.getBinomeMois().getNom());
        mTextViewBinomeJour.setText(biorythme.getBinomeJour().getNom());
        mTextViewBinomeHeure.setText(biorythme.getBinomeHeure().getNom());

    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;

        // set current time into textview
        mTextViewHour.setText(new StringBuilder().append(pad(mHour))
                .append(":").append(pad(mMinute)));
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this,
                        R.style.DialogTheme, TestBiorythmeActivity.this, mHour, mMinute,true);

        }
        return null;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                int year =  Integer.parseInt(mEditTextDateAnnee.getText().toString());
                int month =Integer.parseInt(mEditTextDateMois.getText().toString());
                int day =Integer.parseInt(mEditTextDateJour.getText().toString());

                Biorythme biorythme = new Biorythme(
                        year,
                        month,
                        day,
                        mHour,
                        mMinute,
                        db.getBinome(CalculBinomes.calcBinomeAnnee(year, month, day,mHour, mMinute),""),
                        db.getBinome(CalculBinomes.calcBinomeMois(year, month, day,mHour, mMinute),""),
                        db.getBinome(CalculBinomes.calcBinomeJour(year, month, day,mHour, mMinute),""),
                        db.getBinome(CalculBinomes.calcBinomeHeure(year, month, day,mHour, mMinute),""));

                mTextViewBinomeAnnee.setText(biorythme.getBinomeAnnee().getNom());
                mTextViewBinomeMois.setText(biorythme.getBinomeMois().getNom());
                mTextViewBinomeJour.setText(biorythme.getBinomeJour().getNom());
                mTextViewBinomeHeure.setText(biorythme.getBinomeHeure().getNom());

                break;
            default:
                break;
        }

    }

}
