package com.fibredariane.horoscope.chinois.biorythmeETRES.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * Created by Carlotina on 09/06/2017.
 */

public class AdVideo implements RewardedVideoAdListener {

    private Activity mActivity;

    private TextView mTextViewCredit;
    private RewardedVideoAd mAd;
    private Dialog mDialogErreur;
    private Dialog mDialogSucces;
    private Preferences mPreferences;

    public AdVideo(Activity activity){
        mActivity = activity;
        mPreferences = new Preferences();
        mTextViewCredit = (TextView) activity.findViewById(R.id.text_view_credit);

        mTextViewCredit.setText("" + mPreferences.getCreditPref());

        mDialogErreur = new Dialog(mActivity);
        mDialogErreur.setContentView(R.layout.dialog_information);
        TextView titre = (TextView) mDialogErreur.findViewById(R.id.text_view_titre);
        TextView text = (TextView) mDialogErreur.findViewById(R.id.text_view_text);
        titre.setText(R.string.erreur);
        text.setText(R.string.erreur_video);

        mDialogSucces = new Dialog(mActivity);
        mDialogSucces.setContentView(R.layout.dialog_information);
        TextView titre2 = (TextView) mDialogSucces.findViewById(R.id.text_view_titre);
        TextView text2 = (TextView) mDialogSucces.findViewById(R.id.text_view_text);
        titre2.setText(R.string.succes);
        text2.setText(R.string.succes_credit);

        MobileAds.initialize(activity, "ca-app-pub-1517732795926726~1506000093");
        mAd = MobileAds.getRewardedVideoAdInstance(activity);
        mAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
    }

    @Override
    public void onRewardedVideoAdOpened() {
    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        mPreferences.addCreditPref( 3);
        mTextViewCredit.setText("" + mPreferences.getCreditPref());
        mDialogSucces.show();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
    }
    public void setAd(){
        /*   if (mAd.isLoaded()) {
            mAd.show();
        }else{
            mDialogErreur.show();
        }*/
    }
    private void loadRewardedVideoAd() {
        AdRequest request = new AdRequest.Builder()
                .addTestDevice("9F8A6AA43AFBB8C5A38C022EAE85D6AF")
                .build();

        mAd.loadAd("ca-app-pub-1517732795926726/2982733292", request);
    }
}
