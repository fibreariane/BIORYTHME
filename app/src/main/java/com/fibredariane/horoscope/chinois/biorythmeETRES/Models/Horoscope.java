package com.fibredariane.horoscope.chinois.biorythmeETRES.Models;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.fibredariane.horoscope.chinois.biorythmeETRES.R;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Carlotina on 21/06/2017.
 */

public class Horoscope implements Serializable {

    private String mTextInfluenceAnnee;
    private String mTextInfluenceMois;
    private String mTextInfluenceJour;
    private String mTextInfluenceHeure;

    private int mIdImageInfluenceAnnee;
    private int mIdImageInfluenceMois;
    private int mIdImageInfluenceJour;
    private int mIdImageInfluenceHeure;

    private int mIdImageElement;

    private String mPolarite;
    private String mElement;

    private String mInfluence;
    private int  mNbBinome;

    public Horoscope(int nbBinome, String element, String polarite, String influence, String texteAnnee, String texteMois, String texteJour, String texteHeure){
        mNbBinome=nbBinome;
        mElement = element;
        mPolarite = polarite;
        mInfluence = influence;
        mTextInfluenceAnnee = texteAnnee;
        mTextInfluenceMois = texteMois;
        mTextInfluenceJour = texteJour;
        mTextInfluenceHeure = texteHeure;
    }

    public Horoscope(Cursor cursorAnnee, Cursor cursorMois,Cursor cursorJour,Cursor cursorHeure,Binome binomeHoroscope){
        mElement = binomeHoroscope.getElement().getNom();
        mPolarite = binomeHoroscope.getPolarite();

        mIdImageElement = getId(mElement.toLowerCase());

        if (cursorAnnee.moveToFirst()) {
            mTextInfluenceAnnee =  cursorAnnee.getString(TableHoroscope.Constants.TEXTE_ANNEE_COLUMN);
            mIdImageInfluenceAnnee = getId("meteo_"+cursorAnnee.getString(TableHoroscope.Constants.INFLUENCE_COLUMN).toLowerCase());
        }
        if (cursorMois.moveToFirst()) {
            mTextInfluenceMois = cursorMois.getString(TableHoroscope.Constants.TEXTE_MOIS_COLUMN);
            mIdImageInfluenceMois = getId("meteo_"+cursorMois.getString(TableHoroscope.Constants.INFLUENCE_COLUMN).toLowerCase());
        }
        if (cursorJour.moveToFirst()) {
            mTextInfluenceJour= cursorJour.getString(TableHoroscope.Constants.TEXTE_JOUR_COLUMN);
            mIdImageInfluenceJour = getId("meteo_"+cursorJour.getString(TableHoroscope.Constants.INFLUENCE_COLUMN).toLowerCase());
        }
        if (cursorHeure.moveToFirst()) {
            mTextInfluenceHeure= cursorHeure.getString(TableHoroscope.Constants.TEXTE_HEURE_COLUMN);
            mIdImageInfluenceHeure = getId("meteo_"+cursorHeure.getString(TableHoroscope.Constants.INFLUENCE_COLUMN).toLowerCase());
        }
    }

    public Horoscope( Biorythme biorythmePerso, Binome binomeCourant,String typeHoro){
        try {
            Context c = App.getContext();
          /*  Binome b = new Binome(context, context.getResources().getString(R.string.binome5), typeHoro);
            binomeCourant = b;*/
            JSONObject json = new JSONObject(c.getResources().getString(
                    c.getResources().getIdentifier(
                            "binome"+binomeCourant.getNbBinome()+"_"+typeHoro.toLowerCase(),
                            "string",
                            c.getPackageName())));

            JSONObject jsonAnnee = new JSONObject(json.getString(getStringJson(biorythmePerso.getBinomeAnnee())));
            JSONObject jsonMois = new JSONObject(json.getString(getStringJson(biorythmePerso.getBinomeMois())));
            JSONObject jsonJour = new JSONObject(json.getString(getStringJson(biorythmePerso.getBinomeJour())));
            JSONObject jsonHeure = new JSONObject(json.getString(getStringJson(biorythmePerso.getBinomeHeure())));

            mTextInfluenceAnnee = jsonAnnee.getString("annee");
            mTextInfluenceMois = jsonMois.getString("mois");
            mTextInfluenceJour= jsonJour.getString("jour");
            mTextInfluenceHeure= jsonHeure.getString("heure");


            mIdImageInfluenceAnnee = getId("meteo_"+jsonAnnee.getString("influence"));
            mIdImageInfluenceMois = getId("meteo_"+jsonMois.getString("influence"));
            mIdImageInfluenceJour = getId("meteo_"+jsonJour.getString("influence"));
            mIdImageInfluenceHeure = getId("meteo_"+jsonHeure.getString("influence"));

            mElement = binomeCourant.getElement().getNom();
            mPolarite = binomeCourant.getPolarite();

            mIdImageElement = getId(mElement.toLowerCase());

        } catch (Exception e) {
        Log.v("TAG", "Horoscope - JSON - erreur");
        e.printStackTrace();
    }
    }
    private String getStringJson(Binome binome){
        return binome.getElement().getNom().toLowerCase()+"_"+binome.getPolarite().toLowerCase();
    }
    private int getId(String texte){
      return  App.getContext().getResources().getIdentifier(
                texte,
                "drawable",
              App.getContext().getPackageName());
    }

    public int getNbBinome(){return mNbBinome;}
    public String getInfluence(){return mInfluence;}
    public String getTextInfluenceAnnee(){return mTextInfluenceAnnee;}
    public String getTextInfluenceMois(){return mTextInfluenceMois;}
    public String getTextInfluenceJour(){return mTextInfluenceJour;}
    public String getTextInfluenceHeure(){return mTextInfluenceHeure;}
    public String getElement(){return mElement;}
    public String getPolarite(){return mPolarite;}
    public int getIdImageElement(){return mIdImageElement;}
    public int getIdImageInfluenceAnnee(){return mIdImageInfluenceAnnee;}
    public int getIdImageInfluenceMois(){return mIdImageInfluenceMois;}
    public int getIdImageInfluenceJour(){return mIdImageInfluenceJour;}
    public int getIdImageInfluenceHeure(){return mIdImageInfluenceHeure;}
}
