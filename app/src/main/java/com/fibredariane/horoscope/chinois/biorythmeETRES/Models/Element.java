package com.fibredariane.horoscope.chinois.biorythmeETRES.Models;

import android.content.Context;
import android.util.Log;

import com.fibredariane.horoscope.chinois.biorythmeETRES.R;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Carlotina on 12/04/2017.
 */

public class Element implements Serializable {
    private String mNom;
    private String mValeur;
    private int mIntId;
    private int mIntIdSaison;
    private int mIntIdCouleur;
    private int mIntIdMini;
    private int mIntIdVide;
    private String mTextNeutre;
    private String mTextDominant;
    private String mTextVide;
    private String mSaison;
    private String mCouleur;
    private String mDesc;
    private int mIdColor;

    public Element(Context c, String nom) {
        mNom = nom;
        int idElement = c.getResources().getIdentifier(
                getNom().toLowerCase(),
                "string",
                c.getPackageName());
        try {
            JSONObject json = new JSONObject(c.getResources().getString(idElement));
            mValeur = json.getString("valeur");
            mIntId = c.getResources().getIdentifier(
                    getNom().toLowerCase(),
                    "drawable",
                    c.getPackageName());
            mIntIdMini = c.getResources().getIdentifier(
                    mNom.toLowerCase()+"_mini",
                    "drawable",
                    c.getPackageName());
            mIntIdVide = c.getResources().getIdentifier(
                    mNom.toLowerCase()+"_vide",
                    "drawable",
                    c.getPackageName());
            mTextNeutre = json.getString("texte_neutre");
            mTextDominant = json.getString("texte_dominant");
            mTextVide = json.getString("texte_vide");
            mSaison = json.getString("saison");
            mCouleur = json.getString("couleur");
            mDesc = json.getString("desc");

            switch (mNom){
                case "FEU" :
                    mIdColor= R.color.red;
                    mIntIdSaison = R.drawable.saison_ete;
                    mIntIdCouleur = R.drawable.couleur_rouge;
                    break;
                case "EAU" :
                    mIdColor= R.color.black;
                    mIntIdSaison = R.drawable.saison_hiver;
                    mIntIdCouleur = R.drawable.couleur_noir;
                    break;
                case "BOIS" :
                    mIdColor= R.color.green;
                    mIntIdSaison = R.drawable.saison_printemps;
                    mIntIdCouleur = R.drawable.couleur_vert;
                    break;
                case "TERRE" :
                    mIdColor= R.color.yellow;
                    mIntIdSaison = R.drawable.saison_intersaison;
                    mIntIdCouleur = R.drawable.couleur_jaune;
                    break;
                case "METAL" :
                    mIdColor= R.color.blue;
                    mIntIdSaison = R.drawable.saison_automne;
                    mIntIdCouleur = R.drawable.couleur_bleu;
                    break;
            }

    } catch (Exception e) {
        Log.v("TAG", "Element - JSON - erreur");
        e.printStackTrace();
    }


    }
    public String getNom(){
        return mNom;
    }
    public String getValeur(){
        return mValeur;
    }
    public String getTextNeutre() {return mTextNeutre;}
    public String getTextDominant(){return mTextDominant;}
    public String getTextVide(){return mTextVide;}
    public int getIntId(){return mIntId;}
    public int getIntIdMini(){return mIntIdMini;}
    public int getIdColor(){return mIdColor;}
    public String getDesc(){return mDesc;}
    public int getIntIdSaison(){return mIntIdSaison;}
    public int getIntIdCouleur(){return mIntIdCouleur;}
    public int getIntIdVide(){return mIntIdVide;}
}
