package com.fibredariane.horoscope.chinois.biorythmeETRES.Models;

import android.content.Context;
import java.io.Serializable;
/**
 * Created by Carlotina on 12/04/2017.
 */

public class Biorythme implements Serializable {
    public Binome mBinomeHeure;
    public Binome mBinomeJour;
    public Binome mBinomeMois;
    public Binome mBinomeAnnee;
    public int mYear;
    public int mMonth;
    public int mDay;
    public int mHour;
    public int mMinute;

    public Biorythme(int year, int month, int day, int hour, int minute, Binome binomeAnnee,Binome binomeMois,Binome binomeJour ,Binome binomeHeure) {
        mYear = year;
        mMonth = month;
        mDay = day;
        mHour = hour;
        mMinute = minute;

        mBinomeAnnee = binomeAnnee;
        mBinomeMois = binomeMois;
        mBinomeJour = binomeJour;
        mBinomeHeure = binomeHeure;
    }

    public Binome getBinomeAnnee(){
        return mBinomeAnnee;
    }
    public Binome getBinomeMois(){
        return mBinomeMois;
    }
    public Binome getBinomeJour(){
        return mBinomeJour;
    }
    public Binome getBinomeHeure(){
        return mBinomeHeure;
    }
    public int getYear(){
        return mYear;
    }
    public int getMonth(){
        return mMonth;
    }
    public int getDay(){
        return mDay;
    }
    public int getHour(){
        return mHour;
    }
    public int getMinute() {return mMinute;}
    public String getDateAnniversaire(){
        return mYear+"."+mMonth+"."+mDay+"."+mHour+"."+mMinute;
    }
    public String getStringBiorythme(){ return mBinomeAnnee.getNbBinome()+"."+
            mBinomeMois.getNbBinome()+"."+
            mBinomeJour.getNbBinome()+"."+
            mBinomeHeure.getNbBinome();
    }
    public String getDateString(){
        Context context = App.getContext();
        String dateJour = mDay +" "
                +context.getResources().getString(context.getResources().getIdentifier(
                "mois" + (mMonth-1),
                "string",
                context.getPackageName())).toUpperCase() +" "
                + mYear;
            return dateJour;
    }
}
