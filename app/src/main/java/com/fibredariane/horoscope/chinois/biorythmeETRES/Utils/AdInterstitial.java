package com.fibredariane.horoscope.chinois.biorythmeETRES.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * Created by Carlotina on 09/06/2017.
 */

public class AdInterstitial extends AdListener {

    private Activity mActivity;

    private TextView mTextViewCredit;
    private InterstitialAd mInterstitialAd;
    private Dialog mDialogErreur;
    private Dialog mDialogSucces;
    private Preferences mPreferences;

    public AdInterstitial(Activity activity, Preferences preferences){
        mActivity = activity;
        mPreferences = preferences;


        mDialogErreur = new Dialog(mActivity);
        mDialogErreur.setContentView(R.layout.dialog_information);
        TextView titre = (TextView) mDialogErreur.findViewById(R.id.text_view_titre);
        TextView text = (TextView) mDialogErreur.findViewById(R.id.text_view_text);
        titre.setText(R.string.erreur);
        text.setText(R.string.erreur_annonce);

        mDialogSucces = new Dialog(mActivity);
        mDialogSucces.setContentView(R.layout.dialog_information);
        TextView titre2 = (TextView) mDialogSucces.findViewById(R.id.text_view_titre);
        TextView text2 = (TextView) mDialogSucces.findViewById(R.id.text_view_text);
        titre2.setText(R.string.succes);
        text2.setText(R.string.succes_credit);

        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(activity.getString(R.string.id_pub_interstitiel));
        mInterstitialAd.setAdListener(this);


        loadInterstitialAd();
    }

    public void setAd(){

        /*    if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                mDialogErreur.show();
            }*/
       
    }
    @Override
    public void onAdLoaded() {
    }

    @Override
    public void onAdClosed() {
        loadInterstitialAd();
        mPreferences.addCreditPref(1);
        mTextViewCredit.setText("" + mPreferences.getCreditPref());
        mDialogSucces.show();

    }
    private void loadInterstitialAd() {
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(mActivity.getString(R.string.key_device_test))
                .build();
        mInterstitialAd.loadAd(request);
    }
}
