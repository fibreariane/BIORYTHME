package com.fibredariane.horoscope.chinois.biorythmeETRES.Models;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by Carlotina on 19/04/2017.
 */

public class Organe implements Serializable {
    public String mNom;
    public String mPolarite;
    public Element mElement;

    public Organe(String nom, String polarite, String element) {
        mNom = nom;
        mPolarite = polarite;
        mElement = new Element(element);
    }
    public Element getElement(){
        return mElement;
    }
    public String getNom(){ return mNom;}
    public String getPolarite(){ return mPolarite;}
}
