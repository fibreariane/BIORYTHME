package com.fibredariane.horoscope.chinois.biorythmeETRES.Models;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.InfosBinomes;

import java.util.List;

/**
 * Created by Carlotina on 19/05/2017.
 */

public class Synthese {

    private int mNbElementFeu;
    private int mNbElementEau;
    private int mNbElementTerre;
    private int mNbElementMetal;
    private int mNbElementBois;

    private int mNbDominant;
    private int mNbNeutre;
    private int mNbVide;
    public  Synthese(Biorythme biorythme) {
        mNbDominant = 0;
        mNbNeutre = 0;
        mNbVide = 0;

        mNbElementFeu = setElement(biorythme, "FEU");
        mNbElementEau = setElement(biorythme, "EAU");
        mNbElementTerre = setElement(biorythme, "TERRE");
        mNbElementMetal = setElement(biorythme, "METAL");
        mNbElementBois = setElement(biorythme, "BOIS");

    }
    private int setElement(Biorythme biorythme,String stringElement){
        int nbElement = InfosBinomes.getNbElement(biorythme, stringElement);

        if (nbElement <= 1) {
            mNbVide =mNbVide+1;
        }
        if (nbElement == 2 || nbElement == 3) {
            mNbNeutre =mNbNeutre+1;
        }
        if (nbElement > 3) {
            mNbDominant =mNbDominant+1;
        }

        return nbElement;
    }
    public int getNbElement(String stringElement){
        int nbElement = 0;
        switch (stringElement){
            case "FEU": nbElement = mNbElementFeu; break;
            case "EAU": nbElement = mNbElementEau; break;
            case "METAL": nbElement = mNbElementMetal; break;
            case "BOIS": nbElement = mNbElementBois; break;
            case "TERRE": nbElement = mNbElementTerre; break;
        }
        return nbElement;
    }
    public boolean isDominant(String stringElement){
        if (getNbElement(stringElement) > 3)
            return true;
        else
            return false;
    }
    public boolean isNeutre(String stringElement){
        if (getNbElement(stringElement) == 2 || getNbElement(stringElement) == 3)
            return true;
        else
            return false;
    }
    public boolean isVide(String stringElement){
        if (getNbElement(stringElement) <= 1)
            return true;
        else
            return false;
    }
    public int getNbDominant(){
        return mNbDominant;
    }
    public int getNbNeutre(){
        return mNbNeutre;
    }
    public int getNbVide(){
        return mNbVide;
    }

}

