package com.fibredariane.horoscope.chinois.biorythmeETRES.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.App;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;

import java.time.*;

/**
 * Created by Carlotina on 25/05/2017.
 */

public class Preferences {
    SharedPreferences mSharedPreferences;
    Context mContext;
    public Preferences(){
        mContext =  App.getContext();
        mSharedPreferences = mContext.getSharedPreferences(mContext.getString(R.string.file_pref), Context.MODE_PRIVATE);

    }

    public String getStringBiorythmePref(){
       return mSharedPreferences.getString(mContext.getString(R.string.pref_biorythme_perso), "");
    }

    /*public Biorythme getBiorythmePref() {
        String stringBiorythme = mSharedPreferences.getString(mContext.getString(R.string.pref_biorythme_perso), "");
        String[] binomes = stringBiorythme.split("\\.");
        Date date_annif = getDatePref();
        return new Biorythme(date_annif.getYear(),
                date_annif.getMonth(),
                date_annif.getDate(),
                date_annif.getHours(),
                date_annif.getMinutes(),
                CalculBinomes.getBinome(mContext, Integer.parseInt(binomes[0]), "A"),
                CalculBinomes.getBinome(mContext, Integer.parseInt(binomes[1]), "M"),
                CalculBinomes.getBinome(mContext, Integer.parseInt(binomes[2]), "J"),
                CalculBinomes.getBinome(mContext, Integer.parseInt(binomes[3]), "H"));
    }
*/
  /*  public Biorythme getBiorythmeCurrentPref() {
        String stringBiorythme = mSharedPreferences.getString(mContext.getString(R.string.pref_biorythme_current), "");
        String[] binomes = stringBiorythme.split("\\.");

        Date date_annif = getDateCurrentPref();

        return new Biorythme(date_annif.getYear(),
                date_annif.getMonth(),
                date_annif.getDate(),
                date_annif.getHours(),
                date_annif.getMinutes(),
                CalculBinomes.getBinome(mContext, Integer.parseInt(binomes[0]), "A"),
                CalculBinomes.getBinome(mContext, Integer.parseInt(binomes[1]), "M"),
                CalculBinomes.getBinome(mContext, Integer.parseInt(binomes[2]), "J"),
                CalculBinomes.getBinome(mContext, Integer.parseInt(binomes[3]), "H"));
    }
*/
    public  String getStringDatePref() {
        return mSharedPreferences.getString(mContext.getString(R.string.pref_date_perso), "");
    }

    public String getStringDateCurrentPref() {
        return mSharedPreferences.getString(mContext.getString(R.string.pref_date_current), "");
    }

    public void setDateSauv() {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(mContext.getString(R.string.pref_date_sauv_1), getStringDateCurrentPrefMin());
        edit.commit();
    }

    public boolean isDateSaved() {
        String dateSauv1 = mSharedPreferences.getString(mContext.getString(R.string.pref_date_sauv_1), "");
        String dateCurrent = getStringDateCurrentPrefMin();
        if (dateCurrent.equals(dateSauv1))
            return true;
        else
            return false;
    }

    public LocalDateTime getDatePref() {
        String date_biorythme = getStringDatePref();
        String[] date_annif = date_biorythme.split("\\.");
        return LocalDateTime.of(Integer.parseInt(date_annif[0]),
                Integer.parseInt(date_annif[1]),
                Integer.parseInt(date_annif[2]),
                Integer.parseInt(date_annif[3]),
                Integer.parseInt(date_annif[4]));
    }

    public LocalDateTime getDateCurrentPref() {
        String date_biorythme = getStringDateCurrentPref();
        String[] date = date_biorythme.split("\\.");
        return LocalDateTime.of(Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2]),
                Integer.parseInt(date[3]),
                Integer.parseInt(date[4]));
    }

    public String getStringDateCurrentPrefMin() {
        String date_biorythme = getStringDateCurrentPref();
        if (date_biorythme.equals("")){
            return date_biorythme;
        }else{
            String[] date = date_biorythme.split("\\.");
            String s = "" + Integer.parseInt(date[0]) + "." +
                    Integer.parseInt(date[1]) + "." +
                    Integer.parseInt(date[2]) + "." +
                    Integer.parseInt(date[3]);
            return s;
        }
    }

    /* public void setBiorythmePref(Biorythme biorythme) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(mContext.getString(R.string.pref_date_perso), biorythme.getDateAnniversaire());
        edit.putString(mContext.getString(R.string.pref_biorythme_perso), biorythme.getStringBiorythme());
        edit.commit();
    }*/

    public void setBiorythmePref(String date,String biorythme) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(mContext.getString(R.string.pref_date_perso),date);
        edit.putString(mContext.getString(R.string.pref_biorythme_perso), biorythme);
        edit.commit();
    }

    public void setBiorythmeCurrentPref( Biorythme biorythme) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(mContext.getString(R.string.pref_date_current), biorythme.getDateAnniversaire());
        edit.putString(mContext.getString(R.string.pref_biorythme_current), biorythme.getStringBiorythme());
        edit.commit();
    }

    public void resetBiorythmePref() {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(mContext.getString(R.string.pref_date_perso), "");
        edit.putString(mContext.getString(R.string.pref_biorythme_perso), "");
        edit.putString(mContext.getString(R.string.pref_date_current), "");
        edit.putString(mContext.getString(R.string.pref_biorythme_current), "");

        edit.commit();
    }

    public int getCreditPref() {
        return mSharedPreferences.getInt(mContext.getString(R.string.pref_nb_credit), 0);
    }

    public void addCreditPref( int nbAddCredit) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        int nb_credit = getCreditPref() + nbAddCredit;
        edit.putInt(mContext.getString(R.string.pref_nb_credit), nb_credit);
        edit.commit();
    }

    public void removeCreditPref() {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        int nb_credit = getCreditPref() - 1;
        edit.putInt(mContext.getString(R.string.pref_nb_credit), nb_credit);
        edit.commit();
    }
    public void isFirstTimeApplication(){
        Boolean firstTime = mSharedPreferences.getBoolean(mContext.getString(R.string.firstTime), true) ;
        if(firstTime){
            SharedPreferences.Editor edit = mSharedPreferences.edit();
            addCreditPref(5);
            edit.putBoolean(mContext.getString(R.string.firstTime), false);
            edit.commit();
        }
    }
}
