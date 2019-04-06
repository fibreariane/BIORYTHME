package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.Preferences;

public class DialogOuiNonFragment extends DialogFragment implements View.OnClickListener {
    private Context mContext;
    private Button mButtonOK;
    private Button mButtonCancel;
    private TextView mTextViewTittle;
    private String mType;
    private String mTextButtonOK;
    private String mTextButtonCancel;
    private String mTitle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_oui_non, container, false);
        mContext = this.getActivity();
        mButtonOK = (Button) rootView.findViewById(R.id.button_ok);
        mButtonCancel = (Button) rootView.findViewById(R.id.button_cancel);
        mTextViewTittle = (TextView) rootView.findViewById(R.id.text_view_title);

        mButtonOK.setText(mTextButtonOK);
        mButtonCancel.setText(mTextButtonCancel);
        mTextViewTittle.setText(mTitle);

        mButtonOK.setOnClickListener(this);
        mButtonCancel.setOnClickListener(this);
        return rootView;
    }
    public void initDialogueOuiNon(String type,String title,String textButtonOK,String textButtonCancel){
        mType = type;
        mTitle = title;
        mTextButtonOK = textButtonOK;
        mTextButtonCancel = textButtonCancel;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok:
                switch (mType){
                    case "reinit" :
                        new Preferences().resetBiorythmePref();
                        dismiss();
                    break;
                    default:
                        dismiss();
                        break;
                }
                dismiss();
                break;
            case R.id.button_cancel:
                dismiss();
                break;
            default:
                dismiss();
                break;
        }

    }
}
