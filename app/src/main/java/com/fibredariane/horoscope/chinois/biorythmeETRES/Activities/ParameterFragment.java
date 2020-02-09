package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.BuildConfig;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Test.TestBinomeActivity;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Test.TestMain;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;

import java.util.Date;
import java.util.TimeZone;

public class ParameterFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private Context mContext;
    private View rootView;
    private FragmentManager mFragmentManager;
    private DialogOuiNonFragment mDialogueOuiNon;
    private DialogInformationFragment mDialogueInformation;

    private LinearLayout mLinearLayoutReset;
    private LinearLayout mLinearLayoutSwitch;
    private LinearLayout mLinearLayoutLangue;
    private LinearLayout mLinearLayoutAchat;
    private LinearLayout mLinearLayoutGlossaire;
    private LinearLayout mLinearLayoutEvaluation;
    private Button mButtonTest;

    public ParameterFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ParameterFragment newInstance(int sectionNumber) {
        ParameterFragment fragment = new ParameterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_parameter, container, false);
        mContext = this.getActivity();
        if (mContext != null) {
            mLinearLayoutReset = (LinearLayout) rootView.findViewById(R.id.linear_layout_reset);
            mLinearLayoutReset.setOnClickListener(this);
            mLinearLayoutSwitch = (LinearLayout) rootView.findViewById(R.id.linear_layout_switch);
            mLinearLayoutSwitch.setOnClickListener(this);
            mLinearLayoutLangue = (LinearLayout) rootView.findViewById(R.id.linear_layout_langue);
            mLinearLayoutLangue.setOnClickListener(this);
            mLinearLayoutAchat = (LinearLayout) rootView.findViewById(R.id.linear_layout_achat);
            mLinearLayoutAchat.setOnClickListener(this);
            mLinearLayoutGlossaire = (LinearLayout) rootView.findViewById(R.id.linear_layout_glossaire);
            mLinearLayoutGlossaire.setOnClickListener(this);
            mLinearLayoutEvaluation = (LinearLayout) rootView.findViewById(R.id.linear_layout_evaluation);
            mLinearLayoutEvaluation.setOnClickListener(this);
            mButtonTest = (Button) rootView.findViewById(R.id.btn_test);
            if (BuildConfig.DEBUG) {
                mButtonTest.setVisibility(View.VISIBLE);
                mButtonTest.setOnClickListener(this);
            }
            TextView textViewVersion = rootView.findViewById(R.id.version_name);
            textViewVersion.setText(BuildConfig.VERSION_NAME);

            TextView textViewIdBio = rootView.findViewById(R.id.biorythmme_id);
            textViewIdBio.setText(getIdBiorythme());

            mFragmentManager = getFragmentManager();
            mDialogueOuiNon = new DialogOuiNonFragment();
            mDialogueOuiNon.initDialogueOuiNon("reinit",
                    getString(R.string.reinit_donnee),
                    getString(R.string.reinitialiser),
                    getString(R.string.retour));
            mDialogueInformation = new DialogInformationFragment();
            mDialogueInformation.initDialogInformation(getString(R.string.pas_dispo));
        }
        return rootView;
    }

    private String getIdBiorythme() {
        String id = getString(R.string.pas_renseigne);

        String date_biorythme = new Preferences().getStringDatePref();
        if (date_biorythme != "") {
            String[] date = date_biorythme.split("\\.");
            id = Integer.parseInt(date[2])
                    + getLetterNumber(Integer.parseInt(date[1]))
                    + String.valueOf(Integer.parseInt(date[0])).substring(2, 4)
                    + getLetterNumber(Integer.parseInt(date[3]))
                    + getLetterMinute(Integer.parseInt(date[4]))
                    + TimeZone.getDefault().getDisplayName();
        }
        return id;
    }

    private String getLetterMinute(int minute) {
        if (minute < 30) {
            return "Y";
        } else {
            return "Z";
        }
    }

    private String getLetterNumber(int number) {
        String letter = "";
        switch (number) {
            case 0:
            case 24:
                letter = "A";
                break;
            case 1:
                letter = "B";
                break;
            case 2:
                letter = "C";
                break;
            case 3:
                letter = "D";
                break;
            case 4:
                letter = "E";
                break;
            case 5:
                letter = "F";
                break;
            case 6:
                letter = "G";
                break;
            case 7:
                letter = "H";
                break;
            case 8:
                letter = "I";
                break;
            case 9:
                letter = "J";
                break;
            case 10:
                letter = "K";
                break;
            case 11:
                letter = "L";
                break;
            case 12:
                letter = "M";
                break;
            case 13:
                letter = "N";
                break;
            case 14:
                letter = "O";
                break;
            case 15:
                letter = "P";
                break;
            case 16:
                letter = "Q";
                break;
            case 17:
                letter = "R";
                break;
            case 18:
                letter = "S";
                break;
            case 19:
                letter = "T";
                break;
            case 20:
                letter = "U";
                break;
            case 21:
                letter = "V";
                break;
            case 22:
                letter = "W";
                break;
            case 23:
                letter = "X";
        }
        return letter;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.linear_layout_reset:
                mDialogueOuiNon.show(mFragmentManager, "Sample Fragment");
                break;
            case R.id.linear_layout_switch:
                intent = new Intent(mContext, SwitchBiorythmeActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_layout_langue:
            case R.id.linear_layout_achat:
            case R.id.linear_layout_glossaire:
            case R.id.linear_layout_evaluation:
                mDialogueInformation.show(mFragmentManager, "Sample Fragment");
                break;
            case R.id.btn_test:
                intent = new Intent(mContext, TestMain.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }


}

