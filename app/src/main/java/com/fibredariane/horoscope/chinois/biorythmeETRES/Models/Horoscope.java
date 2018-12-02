package com.fibredariane.horoscope.chinois.biorythmeETRES.Models;

import android.content.Context;
import android.util.Log;

import com.fibredariane.horoscope.chinois.biorythmeETRES.R;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Carlotina on 21/06/2017.
 */

public class Horoscope implements Serializable {
    private Context mContext;

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

    public Horoscope(Context context, Biorythme biorythmePerso, Binome binomeCourant,String typeHoro){
        try {
            mContext = context;
          /*  Binome b = new Binome(context, context.getResources().getString(R.string.binome5), typeHoro);
            binomeCourant = b;*/
            JSONObject json = new JSONObject(context.getResources().getString(
                    context.getResources().getIdentifier(
                            "binome"+binomeCourant.getNbBinome()+"_"+typeHoro.toLowerCase(),
                            "string",
                            context.getPackageName())));

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
      return  mContext.getResources().getIdentifier(
                texte,
                "drawable",
              mContext.getPackageName());
    }
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
