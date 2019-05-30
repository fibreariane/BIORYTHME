package com.fibredariane.horoscope.chinois.biorythmeETRES.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.fibredariane.horoscope.chinois.biorythmeETRES.R;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.ParseJSON;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class TableHoroscope extends SQLiteOpenHelper {

    private static final String DATABASE_CREATE = "create table "
            + Constants.MY_TABLE + "(" + Constants.KEY_COL_ID
            + " integer primary key autoincrement, "
            + Constants.KEY_COL_NB_BINOME + " INTEGER, "
            + Constants.KEY_COL_ELEMENT + " TEXT, "
            + Constants.KEY_COL_POLARITE + " TEXT, "
            + Constants.KEY_COL_INFLUENCE + " TEXT, "
            + Constants.KEY_COL_TEXTE_ANNEE + " TEXT, "
            + Constants.KEY_COL_TEXTE_MOIS + " INTEGER, "
            + Constants.KEY_COL_TEXTE_JOUR + " TEXT, "
            + Constants.KEY_COL_TEXTE_HEURE + " TEXT) ";

    /**
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public TableHoroscope(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    public void deleteDB(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.MY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBOpenHelper", "Mise à jour de la version " + oldVersion
                + " vers la version " + newVersion
                + ", les anciennes données seront détruites ");

        initTable(db);
    }

    public void initTable(SQLiteDatabase db) {

        deleteDB(db);
        onCreate(db);

        initHoroscope(db);
    }

    public void initHoroscope(SQLiteDatabase db) {
        deleteDB(db);
        onCreate(db);
        Context c = App.getContext();

        InputStream inputStream = c.getResources().openRawResource(R.raw.horoscopes_journee);

        List<Horoscope> listHoroscope = null;
        try {
            listHoroscope = ParseJSON.readJsonStreamHoroscope(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < listHoroscope.size(); i++) {
            Horoscope horoscope = listHoroscope.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.KEY_COL_NB_BINOME, horoscope.getNbBinome());
            contentValues.put(Constants.KEY_COL_ELEMENT, horoscope.getElement());
            contentValues.put(Constants.KEY_COL_POLARITE, horoscope.getPolarite());
            contentValues.put(Constants.KEY_COL_INFLUENCE, horoscope.getInfluence());
            contentValues.put(Constants.KEY_COL_TEXTE_ANNEE, horoscope.getTextInfluenceAnnee());
            contentValues.put(Constants.KEY_COL_TEXTE_MOIS, horoscope.getTextInfluenceMois());
            contentValues.put(Constants.KEY_COL_TEXTE_JOUR, horoscope.getTextInfluenceJour());
            contentValues.put(Constants.KEY_COL_TEXTE_HEURE, horoscope.getTextInfluenceHeure());
            //
            insertRecord(db, contentValues);
        }
    }

    public long insertRecord(SQLiteDatabase db, ContentValues contentValues) {
        long rowId = db.insert(Constants.MY_TABLE, null, contentValues);
        return rowId;
    }

    public long updateRecord(SQLiteDatabase db, ContentValues contentValues, long rowId) {
        rowId = db.update(Constants.MY_TABLE,
                contentValues,
                Constants.KEY_COL_ID + "=" + rowId,
                null);

        return rowId;
    }

    public void deleteRecord(SQLiteDatabase db, long rowId) {
        db.delete(Constants.MY_TABLE,
                Constants.KEY_COL_ID + "=" + rowId,
                null);
    }

    public Cursor getHoroscope(SQLiteDatabase db, int nbBinome, String element, String polarite) {

        String selection = Constants.KEY_COL_NB_BINOME + "=?"
                + " and " + Constants.KEY_COL_ELEMENT + "=?"
                + " and " + Constants.KEY_COL_POLARITE + "=?";
        String[] selectionArg = new String[]{String.valueOf(nbBinome), element, polarite};
        String maxResultsListSize = "1";

        Cursor cursor = db.query(Constants.MY_TABLE, null, selection,
                selectionArg, null, null, null, maxResultsListSize);

        return cursor;
    }

    public SQLiteDatabase openDB() throws SQLiteException {
        SQLiteDatabase db;
        try {
            db = getWritableDatabase();
        } catch (SQLiteException ex) {
            db = getReadableDatabase();
        }
        return db;
    }

    public void closeDB(SQLiteDatabase db) {
        db.close();
    }

    public static class Constants implements BaseColumns {
        // The database name
        public static final String DATABASE_NAME = "Horoscope.db";

        // The database version
        public static final int DATABASE_VERSION = 1;

        // The table Name
        public static final String MY_TABLE = "Horoscope";

        // Noms de colonnes
        public static final String KEY_COL_ID = "_id";
        public static final String KEY_COL_NB_BINOME = "nbBinome";
        public static final String KEY_COL_ELEMENT = "element";
        public static final String KEY_COL_POLARITE = "polarite";
        public static final String KEY_COL_INFLUENCE = "influence";
        public static final String KEY_COL_TEXTE_ANNEE = "texteAnnee";
        public static final String KEY_COL_TEXTE_MOIS = "texteMois";
        public static final String KEY_COL_TEXTE_JOUR = "texteJour";
        public static final String KEY_COL_TEXTE_HEURE = "texteHeure";

        // Index des colonnes
        public static final int ID_COLUMN = 0;
        public static final int NB_BINOME_COLUMN = 1;
        public static final int ELEMENT_COLUMN = 2;
        public static final int POLARITE_COLUMN = 3;
        public static final int INFLUENCE_COLUMN = 4;
        public static final int TEXTE_ANNEE_COLUMN = 5;
        public static final int TEXTE_MOIS_COLUMN = 6;
        public static final int TEXTE_JOUR_COLUMN = 7;
        public static final int TEXTE_HEURE_COLUMN = 8;
    }
}
