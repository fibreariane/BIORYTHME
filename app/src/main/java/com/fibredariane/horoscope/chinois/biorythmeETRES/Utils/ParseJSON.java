package com.fibredariane.horoscope.chinois.biorythmeETRES.Utils;

import android.util.JsonReader;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Horoscope;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Organe;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParseJSON {
    // Parse binomes.json
    static public List<Binome> readJsonStreamBinome(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readBinomeArray(reader);
        } finally {
            reader.close();
        }
    }

    static public List<Binome> readBinomeArray(JsonReader reader) throws IOException {
        List<Binome> binomes = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            binomes.add(readBinome(reader));
        }
        reader.endArray();
        return binomes;
    }

    static public Binome readBinome(JsonReader reader) throws IOException {
        String idBinome = null;
        int nbBinome = -1;
        String nom = null;
        String description = null;
        String element = null;
        Organe troncCeleste = null;
        Organe brancheTerrestre = null;
        String polarite = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id_binome")) {
                idBinome = reader.nextString();
            } else if (name.equals("nb_binome")) {
                nbBinome = reader.nextInt();
            } else if (name.equals("name")) {
                nom = reader.nextString();
            } else if (name.equals("description")) {
                description = reader.nextString();
            } else if (name.equals("element")) {
                element = reader.nextString();
            } else if (name.equals("polarite")) {
                polarite = reader.nextString();
            } else if (name.equals("tronc_celeste")) {
                troncCeleste = readOrgane(reader);
            } else if (name.equals("branche_terrestre")) {
                brancheTerrestre = readOrgane(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Binome(idBinome, nbBinome, nom, description, element, troncCeleste, brancheTerrestre, polarite);
    }

    static public Organe readOrgane(JsonReader reader) throws IOException {
        String nom = null;
        String polarite = null;
        String element = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("organe")) {
                nom = reader.nextString();
            } else if (name.equals("polarite")) {
                polarite = reader.nextString();
            } else if (name.equals("element")) {
                element = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Organe(nom, polarite, element);
    }

    // Parse horoscopes.json
    static public List<Horoscope> readJsonStreamHoroscope(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readHoroscopeArray(reader);
        } finally {
            reader.close();
        }
    }

    static public List<Horoscope> readHoroscopeArray(JsonReader reader) throws IOException {
        List<Horoscope> horoscopes = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            horoscopes.add(readHoroscope(reader));
        }
        reader.endArray();
        return horoscopes;
    }

    static public Horoscope readHoroscope(JsonReader reader) throws IOException {
        int nbBinome = -1;
        String element = null;
        String polarite = null;
        String influence = null;
        String texteAnnee = null;
        String texteMois = null;
        String texteJour = null;
        String texteHeure = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("nb_binome")) {
                nbBinome = reader.nextInt();
            } else if (name.equals("element")) {
                element = reader.nextString();
            } else if (name.equals("polarite")) {
                polarite = reader.nextString();
            } else if (name.equals("influence")) {
                influence = reader.nextString();
            } else if (name.equals("annee")) {
                texteAnnee = reader.nextString();
            } else if (name.equals("mois")) {
                texteMois = reader.nextString();
            } else if (name.equals("jour")) {
                texteJour = reader.nextString();
            } else if (name.equals("heure")) {
                texteHeure = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (texteAnnee.isEmpty()) {
            texteAnnee = nbBinome + element.substring(0, 1) + polarite.substring(0, 2) + influence + " Texte Social test";
        }
        if (texteMois.isEmpty()) {
            texteMois = nbBinome + element.substring(0, 1) + polarite.substring(0, 2) + influence + " Texte Affectif test";
        }
        if (texteJour.isEmpty()) {
            texteJour = nbBinome + element.substring(0, 1) + polarite.substring(0, 2) + influence + " Texte Chance test";
        }
        if (texteHeure.isEmpty()) {
            texteHeure = nbBinome + element.substring(0, 1) + polarite.substring(0, 2) + influence + " Texte Santé test";
        }
        return new Horoscope(nbBinome, element, polarite, influence, texteAnnee, texteMois, texteJour, texteHeure);
    }
}
