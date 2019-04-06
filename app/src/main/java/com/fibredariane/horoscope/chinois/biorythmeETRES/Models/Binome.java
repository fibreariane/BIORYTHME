package com.fibredariane.horoscope.chinois.biorythmeETRES.Models;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Carlotina on 12/04/2017.
 */

public class Binome implements Serializable {

    private String mNom;
    private String mIdBinome;
    private String mDescription;
    private int mNbBinome;
    private int mIntId;
    private int mIntIdMini;
    private String mType;
    private Organe mOrganeTroncCeleste;
    private Organe mOrganeBrancheTerrestre;
    private String mPolarite;
    private Element mElement;
    private String mKey1;
    private String mKey2;


    public Binome(Cursor cursor, String typeBinome){
        Context c = App.getContext();
        if (cursor.moveToFirst()) {
                mNom = cursor.getString(TableBinome.Constants.NAME_COLUMN);
            mNbBinome = cursor.getInt(TableBinome.Constants.NB_BINOME_COLUMN);
            mIdBinome = cursor.getString(TableBinome.Constants.ID_BINOME_COLUMN);
            mIntId = c.getResources().getIdentifier(
                    mIdBinome,
                    "drawable",
                    c.getPackageName());
            mIntIdMini = c.getResources().getIdentifier(
                    mIdBinome+"_mini",
                    "drawable",
                    c.getPackageName());
            mDescription = cursor.getString(TableBinome.Constants.DESCRIPTION_COLUMN);
            mType = typeBinome;
            mPolarite = cursor.getString(TableBinome.Constants.POLARITE_COLUMN);
            mElement = new Element(cursor.getString(TableBinome.Constants.ELEMENT_COLUMN));
            mOrganeTroncCeleste = new Organe(
                    cursor.getString(TableBinome.Constants.TRONC_ORGANE_COLUMN),
                    cursor.getString(TableBinome.Constants.TRONC_POLARITE_COLUMN),
                    cursor.getString(TableBinome.Constants.TRONC_ELEMENT_COLUMN));
            mOrganeBrancheTerrestre = new Organe(
                    cursor.getString(TableBinome.Constants.BRANCHE_ORGANE_COLUMN),
                    cursor.getString(TableBinome.Constants.BRANCHE_POLARITE_COLUMN),
                    cursor.getString(TableBinome.Constants.BRANCHE_ELEMENT_COLUMN));

             if (mPolarite.equals("YANG")){
                switch (mElement.getNom()){
                    case "BOIS" :
                        mKey1 = c.getResources().getString(R.string.key_creation);
                        mKey2 = c.getResources().getString(R.string.key_action);
                        break;
                    case "FEU" :
                        mKey1 = c.getResources().getString(R.string.key_communication);
                        mKey2 = c.getResources().getString(R.string.key_partage);
                        break;
                    case "METAL" :
                        mKey1 = c.getResources().getString(R.string.key_sensation);
                        mKey2 = c.getResources().getString(R.string.key_instinct);
                        break;
                    case "EAU" :
                        mKey1 = c.getResources().getString(R.string.key_Lucidite);
                        mKey2 = c.getResources().getString(R.string.key_ecoute);
                        break;
                    case "TERRE" :
                        mKey1 = c.getResources().getString(R.string.key_realisation);
                        mKey2 = c.getResources().getString(R.string.key_concret);
                        break;
                }
            }else{
                switch (mElement.getNom()){
                    case "BOIS" :
                        mKey1 = c.getResources().getString(R.string.key_sensation);
                        mKey2 = c.getResources().getString(R.string.key_instinct);
                        break;
                    case "FEU" :
                        mKey1 = c.getResources().getString(R.string.key_Lucidite);
                        mKey2 = c.getResources().getString(R.string.key_ecoute);
                        break;
                    case "METAL" :
                        mKey1 = c.getResources().getString(R.string.key_communication);
                        mKey2 = c.getResources().getString(R.string.key_partage);
                        break;
                    case "EAU" :
                        mKey1 = c.getResources().getString(R.string.key_realisation);
                        mKey2 = c.getResources().getString(R.string.key_concret);
                        break;
                    case "TERRE" :
                        mKey1 = c.getResources().getString(R.string.key_creation);
                        mKey2 = c.getResources().getString(R.string.key_action);
                        break;
                }
            }
        }
    }

    public  Binome(Context c, String jsonString, String typeBinome) {

        try {
            JSONObject json = new JSONObject(jsonString);
            mNom = json.getString("name");
            mNbBinome =json.getInt("nb_binome");
            mIdBinome = json.getString("id_binome");
            mIntId = c.getResources().getIdentifier(
                    mIdBinome,
                    "drawable",
                    c.getPackageName());
            mIntIdMini = c.getResources().getIdentifier(
                    mIdBinome+"_mini",
                    "drawable",
                    c.getPackageName());

            mDescription = json.getString("description");
            mType = typeBinome;
            mPolarite = json.getString("polarite");
            mElement = new Element(json.getString("element"));

            JSONObject jsonTroncCeleste = new JSONObject(json.getString("tronc_celeste"));
            mOrganeTroncCeleste = new Organe(jsonTroncCeleste.getString("organe"),jsonTroncCeleste.getString("polarite"),jsonTroncCeleste.getString("element"));
            JSONObject jsonBrancheTerrestre = new JSONObject(json.getString("branche_terrestre"));
            mOrganeBrancheTerrestre = new Organe(jsonBrancheTerrestre.getString("organe"),jsonBrancheTerrestre.getString("polarite"),jsonBrancheTerrestre.getString("element"));

            if (mPolarite.equals("YANG")){
                switch (mElement.getNom()){
                    case "BOIS" :
                        mKey1 = c.getResources().getString(R.string.key_creation);
                        mKey2 = c.getResources().getString(R.string.key_action);
                        break;
                    case "FEU" :
                        mKey1 = c.getResources().getString(R.string.key_communication);
                        mKey2 = c.getResources().getString(R.string.key_partage);
                        break;
                    case "METAL" :
                        mKey1 = c.getResources().getString(R.string.key_sensation);
                        mKey2 = c.getResources().getString(R.string.key_instinct);
                        break;
                    case "EAU" :
                        mKey1 = c.getResources().getString(R.string.key_Lucidite);
                        mKey2 = c.getResources().getString(R.string.key_ecoute);
                        break;
                    case "TERRE" :
                        mKey1 = c.getResources().getString(R.string.key_realisation);
                        mKey2 = c.getResources().getString(R.string.key_concret);
                        break;
                }
            }else{
                switch (mElement.getNom()){
                    case "BOIS" :
                        mKey1 = c.getResources().getString(R.string.key_sensation);
                        mKey2 = c.getResources().getString(R.string.key_instinct);
                        break;
                    case "FEU" :
                        mKey1 = c.getResources().getString(R.string.key_Lucidite);
                        mKey2 = c.getResources().getString(R.string.key_ecoute);
                        break;
                    case "METAL" :
                        mKey1 = c.getResources().getString(R.string.key_communication);
                        mKey2 = c.getResources().getString(R.string.key_partage);
                        break;
                    case "EAU" :
                        mKey1 = c.getResources().getString(R.string.key_realisation);
                        mKey2 = c.getResources().getString(R.string.key_concret);
                        break;
                    case "TERRE" :
                        mKey1 = c.getResources().getString(R.string.key_creation);
                        mKey2 = c.getResources().getString(R.string.key_action);
                        break;
                }
            }
        } catch (Exception e) {
            Log.v("TAG", "Binome - JSON - erreur");
            e.printStackTrace();
        }
    }
    public String getNom(){
        return mNom;
    }
    public String getIdBinome() {return mIdBinome;}
    public int getIntId(){
        return mIntId;
    }
    public int getIntIdMini(){
        return mIntIdMini;
    }
    public String getDescription(){ return mDescription;}
    public Element getElement(){return mElement;}
    public Organe getOrganeTroncCeleste(){return mOrganeTroncCeleste;}
    public Organe getOrganeBrancheTerrestre() {return mOrganeBrancheTerrestre;}
    public String getPolarite() {return mPolarite;}
    public String getKey1(){return mKey1;}
    public String getKey2(){return mKey2;}
    public int getNbBinome(){return mNbBinome;}


}
