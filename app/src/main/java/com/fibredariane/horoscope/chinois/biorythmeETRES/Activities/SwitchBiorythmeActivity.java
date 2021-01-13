package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.CalculBinomes;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;

import java.time.*;

public class SwitchBiorythmeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, View.OnClickListener {

    private TextView mTextViewHour;
    private LinearLayout mLinearHeure;
    private EditText mEditTextDateJour;
    private EditText mEditTextDateMois;
    private EditText mEditTextDateAnnee;
    private Context mContext;
    private Button mButtonValider;
    private Preferences mPreferences;
    private boolean mAsDateSaved;
    private LocalDateTime mDatePref;

    private int mHour;
    private int mMinute;

    private Dialog mDialog;

    static final int TIME_DIALOG_ID = 999;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_biorythme);
        mContext = this;
        mPreferences = new Preferences();

        mButtonValider = (Button) findViewById(R.id.button_valider_anniv);
        mButtonValider.setOnClickListener(this);

        mEditTextDateJour = (EditText) findViewById(R.id.text_view_date_jour);
        mEditTextDateMois = (EditText) findViewById(R.id.text_view_date_mois);
        mEditTextDateAnnee = (EditText) findViewById(R.id.text_view_date_annee);

        mLinearHeure = (LinearLayout) findViewById(R.id.linear_layout_heure);
        mTextViewHour = (TextView) findViewById(R.id.text_view_hour);

        if (mPreferences.getStringDatePref() != "") {
            mDatePref = mPreferences.getDatePref();
            mAsDateSaved = true;
        } else {
            mAsDateSaved = false;
        }
        initDialog();
        // initTimezone();

        setCurrentTimeOnView();
        setCurrentDateOnView();
    }

    private void setCurrentDateOnView() {
        mEditTextDateJour = (EditText) findViewById(R.id.text_view_date_jour);
        mEditTextDateMois = (EditText) findViewById(R.id.text_view_date_mois);
        mEditTextDateAnnee = (EditText) findViewById(R.id.text_view_date_annee);

        if (mPreferences.getStringDatePref() != "") {
            String dateString = mPreferences.getStringDatePref();
            String[] dates = dateString.split("\\.");

            mEditTextDateJour.setText(dates[2]);
            mEditTextDateMois.setText(dates[1]);
            mEditTextDateAnnee.setText(dates[0]);
        }
    }

    public void setCurrentTimeOnView() {

        mLinearHeure = (LinearLayout) findViewById(R.id.linear_layout_heure);
        mTextViewHour = (TextView) findViewById(R.id.text_view_hour);
        mLinearHeure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

        if (mAsDateSaved) {
            mMinute = mDatePref.getMinute();
            mHour = mDatePref.getHour();
        } else {
            mMinute = LocalDateTime.now().getMinute();
            mHour = LocalDateTime.now().getHour();
        }

        // set current time into textview
        mTextViewHour.setText(
                new StringBuilder().append(pad(mHour))
                        .append(":").append(pad(mMinute)));


    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this,
                        R.style.DialogTheme, SwitchBiorythmeActivity.this, mHour, mMinute, true);

        }
        return null;
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;

        // set current time into textview
        mTextViewHour.setText(new StringBuilder().append(pad(mHour))
                .append(":").append(pad(mMinute)));
    }

    @Override
    public void onClick(View v) {
        boolean ok = true;
        mEditTextDateJour.setTextColor(Color.BLACK);
        mEditTextDateMois.setTextColor(Color.BLACK);
        mEditTextDateAnnee.setTextColor(Color.BLACK);

        try {
            int year = Integer.parseInt(mEditTextDateAnnee.getText().toString());
            int month = Integer.parseInt(mEditTextDateMois.getText().toString());
            int day = Integer.parseInt(mEditTextDateJour.getText().toString());
            if (year < 1924 || year > 2020) {
                ok = false;
                mEditTextDateAnnee.setTextColor(Color.RED);
            }

            if (month < 1 || month > 12) {
                ok = false;
                mEditTextDateMois.setTextColor(Color.RED);
            }

            if (day < 1 || day > 31) {
                ok = false;
                mEditTextDateJour.setTextColor(Color.RED);
            }
            if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                ok = false;
                mEditTextDateJour.setTextColor(Color.RED);
            }
            if (month == 2) {
                if (LocalDate.of(year,month,day).isLeapYear()) {
                    if (day > 29) {
                        ok = false;
                        mEditTextDateJour.setTextColor(Color.RED);
                    }
                } else {
                    if (day > 28) {
                        ok = false;
                        mEditTextDateJour.setTextColor(Color.RED);
                    }
                }
            }
            if (ok) {
                String date = year + "." + month + "." + day + "." + mHour + "." + mMinute;
                mPreferences.setBiorythmePref(date, CalculBinomes.getStringBinome(year, month, day, mHour, mMinute));
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            } else {
                mDialog.show();
            }
        }catch (NumberFormatException e){
            mDialog.show();
        }

    }
    @Override
    public void onBackPressed() {

    }
    private void initDialog() {
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_information);
        TextView titre = (TextView) mDialog.findViewById(R.id.text_view_titre);
        TextView text = (TextView) mDialog.findViewById(R.id.text_view_text);
        titre.setText(R.string.erreur);
        text.setText(R.string.date_hors_limite);
    }

}

