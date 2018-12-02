package com.fibredariane.horoscope.chinois.biorythmeETRES.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Carlotina on 25/05/2017.
 */

public class InfosBinomes {

    public static String getStringAnnee(Biorythme biorythme){
        return "BINOME DE L'ANNEE "+CalculBinomes.getDateAnneeNaissance(biorythme.getYear(),biorythme.getMonth(),biorythme.getDay(),biorythme.getHour(),biorythme.getMinute());
    }
    public static String getStringAnneeTotale(Context context,Biorythme biorythme){
        int year = CalculBinomes.getDateAnneeNaissance(biorythme.getYear(),biorythme.getMonth(),biorythme.getDay(),biorythme.getHour(),biorythme.getMinute());
        Date dateDeb = CalculBinomes.getAnneesLunaires(year);
        Date dateFin = CalculBinomes.getAnneesLunaires(year+1);
        int mDeb = dateDeb.getMonth()-1;
        int mFin = dateFin.getMonth() -1;
        String monthDeb = context.getResources().getString(context.getResources().getIdentifier(
                "mois"+mDeb,
                "string",
                context.getPackageName())).toUpperCase();
        String monthFin = context.getResources().getString(context.getResources().getIdentifier(
                "mois"+mFin,
                "string",
                context.getPackageName())).toUpperCase();
        return "Du "+dateDeb.getDate()+" "+monthDeb+ " "+dateDeb.getYear()
                +" au "+dateFin.getDate()+" "+monthFin+ " "+dateFin.getYear();
    }

    public static int getNbElement(Biorythme biorythme, String element) {
        int nbElement = 0;

        nbElement = getNbElementBinome(biorythme.getBinomeAnnee(), element)
                + getNbElementBinome(biorythme.getBinomeMois(), element)
                + getNbElementBinome(biorythme.getBinomeJour(), element)
                + getNbElementBinome(biorythme.getBinomeHeure(), element);

        return nbElement;
    }

    private static int getNbElementBinome(Binome binome, String element) {
        int nbElement = 0;
        if (binome.getElement().getNom().equals(element))
            nbElement = nbElement + 1;
        if (binome.getOrganeBrancheTerrestre().getElement().getNom().equals(element))
            nbElement = nbElement + 1;
        if (binome.getOrganeTroncCeleste().getElement().getNom().equals(element))
            nbElement = nbElement + 1;
        return nbElement;

    }
    public static int getIdTotInfluenceAccueil(Context context) {
        int nbCC = getNbInfluence(context, "J", "cc");
        int nbTF = getNbInfluence(context, "J", "tf");
        int nbFC = getNbInfluence(context, "J", "fc");
        int nbPC = getNbInfluence(context, "J", "pc");
        int nbD = getNbInfluence(context, "J", "d");
        int nbPositif = nbTF + nbFC;
        int nbNegatif = nbPC + nbD;
        int nbNeutre = nbCC;

        String influence = "cc";

        if (nbTF == 4 || nbTF == 3)
            influence = "tf";
        if (nbFC == 4 || nbFC == 3)
            influence = "fc";
        if (nbPC == 4 || nbPC == 3)
            influence = "pc";
        if (nbD == 4 || nbD == 3)
            influence = "d";
        if (nbPositif == 4 || (nbPositif == 2 && nbNeutre == 2) || (nbPositif == 2 && nbNeutre == 1 && nbNegatif == 1))
            influence = "fc";
        if (nbNegatif == 4 || (nbNegatif == 2 && nbNeutre == 2) || (nbNegatif == 2 && nbNeutre == 1 && nbPositif == 1))
            influence = "pc";

        return context.getResources().getIdentifier(
                "meteo_" + influence + "_accueil",
                "drawable",
                context.getPackageName());
    }

    public static int getIdTotInfluence(Context context) {
        int nbCC = getNbInfluence(context, "J", "cc");
        int nbTF = getNbInfluence(context, "J", "tf");
        int nbFC = getNbInfluence(context, "J", "fc");
        int nbPC = getNbInfluence(context, "J", "pc");
        int nbD = getNbInfluence(context, "J", "d");
        int nbPositif = nbTF + nbFC;
        int nbNegatif = nbPC + nbD;
        int nbNeutre = nbCC;

        String influence = "cc";

        if (nbTF == 4 || nbTF == 3)
            influence = "tf";
        if (nbFC == 4 || nbFC == 3)
            influence = "fc";
        if (nbPC == 4 || nbPC == 3)
            influence = "pc";
        if (nbD == 4 || nbD == 3)
            influence = "d";
        if (nbPositif == 4 || (nbPositif == 2 && nbNeutre == 2) || (nbPositif == 2 && nbNeutre == 1 && nbNegatif == 1))
            influence = "fc";
        if (nbNegatif == 4 || (nbNegatif == 2 && nbNeutre == 2) || (nbNegatif == 2 && nbNeutre == 1 && nbPositif == 1))
            influence = "pc";

        return context.getResources().getIdentifier(
                "meteo_" + influence,
                "drawable",
                context.getPackageName());
    }

    public static int getNbInfluence(Context context, String typeCurrent, String typeInfluence) {
        int nbInfluence = 0;

        String influence = getInfluence(context, typeCurrent, "A");
        if (typeInfluence.equals(influence))
            nbInfluence = nbInfluence + 1;

        influence = getInfluence(context, typeCurrent, "M");
        if (typeInfluence.equals(influence))
            nbInfluence = nbInfluence + 1;

        influence = getInfluence(context, typeCurrent, "J");
        if (typeInfluence.equals(influence))
            nbInfluence = nbInfluence + 1;

        influence = getInfluence(context, typeCurrent, "H");
        if (typeInfluence.equals(influence))
            nbInfluence = nbInfluence + 1;

        return nbInfluence;
    }

    public static int getIdInfluence(Context context, String typeCurrent, String typePersonal) {
        String influence = getInfluence(context, typeCurrent, typePersonal);
        return context.getResources().getIdentifier(
                "meteo_" + influence,
                "drawable",
                context.getPackageName());

    }

    public static int getIdTextInfluence(Context context, String typeCurrent, String typePersonal) {
        String temps = "influence_jour";
        switch (typePersonal) {
            case "A":
                temps = "influence_annee_";
                break;
            case "M":
                temps = "influence_mois_";
                break;
            case "J":
                temps = "influence_jour_";
                break;
            case "H":
                temps = "influence_heure_";
                break;
        }
        String influence = getInfluence(context, typeCurrent, typePersonal);
        return context.getResources().getIdentifier(
                temps + influence,
                "string",
                context.getPackageName());
    }

    public static int getIdImageElement(Context context, String typeCurrent) {
        Biorythme currentBiorythme = CalculBinomes.getCurrentBiorythme(context,new Preferences(context));
        Binome currentBinome = currentBiorythme.getBinomeAnnee();

        switch (typeCurrent) {
            case "A":
                currentBinome = currentBiorythme.getBinomeAnnee();
                break;
            case "M":
                currentBinome = currentBiorythme.getBinomeMois();
                break;
            case "J":
                currentBinome = currentBiorythme.getBinomeJour();
                break;
            case "H":
                currentBinome = currentBiorythme.getBinomeHeure();
                break;
        }

        return context.getResources().getIdentifier(
                currentBinome.getElement().getNom().toLowerCase(),
                "drawable",
                context.getPackageName());
    }

    public static String getInfluence(Context context, String typeCurrent, String typePersonal) {
        Preferences preferences = new Preferences(context);
        String date_biorythme = preferences.getStringDatePref();

        Biorythme currentBiorythme = CalculBinomes.getCurrentBiorythme(context,preferences);
        Biorythme personalBiorythme = preferences.getBiorythmePref();

        Binome currentBinome = currentBiorythme.getBinomeAnnee();
        Binome personalBinome = personalBiorythme.getBinomeAnnee();

        String influence = "CC";

        switch (typeCurrent) {
            case "A":
                currentBinome = currentBiorythme.getBinomeAnnee();
                break;
            case "M":
                currentBinome = currentBiorythme.getBinomeMois();
                break;
            case "J":
                currentBinome = currentBiorythme.getBinomeJour();
                break;
        }
        switch (typePersonal) {
            case "A":
                personalBinome = personalBiorythme.getBinomeAnnee();
                break;
            case "M":
                personalBinome = personalBiorythme.getBinomeMois();
                break;
            case "J":
                personalBinome = personalBiorythme.getBinomeJour();
                break;
            case "H":
                personalBinome = personalBiorythme.getBinomeHeure();
                break;
        }

        switch (currentBinome.getElement().getNom()) {
            case "BOIS":
                if (personalBinome.getPolarite().equals("YANG")) {
                    switch (personalBinome.getElement().getNom()) {
                        case "BOIS":
                            influence = "cc";
                            break;
                        case "FEU":
                            influence = "fc";
                            break;
                        case "METAL":
                            influence = "pc";
                            break;
                        case "EAU":
                            influence = "d";
                            break;
                        case "TERRE":
                            influence = "tf";
                            break;
                    }
                } else {
                    switch (personalBinome.getElement().getNom()) {
                        case "BOIS":
                            influence = "pc";
                            break;
                        case "FEU":
                            influence = "d";
                            break;
                        case "METAL":
                            influence = "fc";
                            break;
                        case "EAU":
                            influence = "tf";
                            break;
                        case "TERRE":
                            influence = "cc";
                            break;
                    }
                }
                break;
            case "FEU":
                if (personalBinome.getPolarite().equals("YANG")) {
                    switch (personalBinome.getElement().getNom()) {
                        case "BOIS":
                            influence = "d";
                            break;
                        case "FEU":
                            influence = "cc";
                            break;
                        case "METAL":
                            influence = "tf";
                            break;
                        case "EAU":
                            influence = "pc";
                            break;
                        case "TERRE":
                            influence = "fc";
                            break;
                    }
                } else {
                    switch (personalBinome.getElement().getNom()) {
                        case "BOIS":
                            influence = "tf";
                            break;
                        case "FEU":
                            influence = "pc";
                            break;
                        case "METAL":
                            influence = "cc";
                            break;
                        case "EAU":
                            influence = "fc";
                            break;
                        case "TERRE":
                            influence = "d";
                            break;
                    }
                }
                break;
            case "METAL":
                if (personalBinome.getPolarite().equals("YANG")) {
                    switch (personalBinome.getElement().getNom()) {
                        case "BOIS":
                            influence = "tf";
                            break;
                        case "FEU":
                            influence = "pc";
                            break;
                        case "METAL":
                            influence = "cc";
                            break;
                        case "EAU":
                            influence = "fc";
                            break;
                        case "TERRE":
                            influence = "d";
                            break;
                    }
                } else {
                    switch (personalBinome.getElement().getNom()) {
                        case "BOIS":
                            influence = "cc";
                            break;
                        case "FEU":
                            influence = "fc";
                            break;
                        case "METAL":
                            influence = "pc";
                            break;
                        case "EAU":
                            influence = "d";
                            break;
                        case "TERRE":
                            influence = "tf";
                            break;
                    }
                }
                break;
            case "EAU":
                if (personalBinome.getPolarite().equals("YANG")) {
                    switch (personalBinome.getElement().getNom()) {
                        case "BOIS":
                            influence = "fc";
                            break;
                        case "FEU":
                            influence = "tf";
                            break;
                        case "METAL":
                            influence = "d";
                            break;
                        case "EAU":
                            influence = "cc";
                            break;
                        case "TERRE":
                            influence = "pc";
                            break;
                    }
                } else {
                    switch (personalBinome.getElement().getNom()) {
                        case "BOIS":
                            influence = "d";
                            break;
                        case "FEU":
                            influence = "cc";
                            break;
                        case "METAL":
                            influence = "tf";
                            break;
                        case "EAU":
                            influence = "pc";
                            break;
                        case "TERRE":
                            influence = "fc";
                            break;
                    }
                }
                break;
            case "TERRE":
                if (personalBinome.getPolarite().equals("YANG")) {
                    switch (personalBinome.getElement().getNom()) {
                        case "BOIS":
                            influence = "pc";
                            break;
                        case "FEU":
                            influence = "d";
                            break;
                        case "METAL":
                            influence = "fc";
                            break;
                        case "EAU":
                            influence = "tf";
                            break;
                        case "TERRE":
                            influence = "cc";
                            break;
                    }
                } else {
                    switch (personalBinome.getElement().getNom()) {
                        case "BOIS":
                            influence = "fc";
                            break;
                        case "FEU":
                            influence = "tf";
                            break;
                        case "METAL":
                            influence = "d";
                            break;
                        case "EAU":
                            influence = "cc";
                            break;
                        case "TERRE":
                            influence = "pc";
                            break;
                    }
                }
                break;
        }

        return influence;
    }

}
