package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;

public class DialogInformationFragment extends DialogFragment implements View.OnClickListener {
        private Button mButtonOK;
        private TextView mTextViewTittle;
        private String mTitle;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_dialog_information, container, false);
            mButtonOK = rootView.findViewById(R.id.button_ok);
            mTextViewTittle = rootView.findViewById(R.id.text_view_title);
            mTextViewTittle.setText(mTitle);

            mButtonOK.setOnClickListener(this);
            return rootView;
        }
        public void initDialogInformation(String title){
            mTitle = title;
        }

        @Override
        public void onClick(View v) {
            dismiss();
        }
    }
