package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fibredariane.horoscope.chinois.biorythmeETRES.R;


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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_reset:
                mDialogueOuiNon.show(mFragmentManager, "Sample Fragment");
                break;
            case R.id.linear_layout_switch :
                Intent intent = new Intent(mContext, SwitchBiorythmeActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_layout_langue :
            case R.id.linear_layout_achat :
            case R.id.linear_layout_glossaire :
            case R.id.linear_layout_evaluation :
                mDialogueInformation.show(mFragmentManager, "Sample Fragment");
               break;
            default:
                break;
        }

    }


}

