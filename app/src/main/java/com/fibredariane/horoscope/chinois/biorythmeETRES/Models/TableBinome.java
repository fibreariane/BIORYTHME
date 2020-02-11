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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Utils.ParseJSON;


/**
 * Created by Carlotina on 04/12/2018.
 */

public class TableBinome extends SQLiteOpenHelper {

    private static final String DATABASE_CREATE = "create table "
            + Constants.MY_TABLE + "(" + Constants.KEY_COL_ID
            + " integer primary key autoincrement, "
            + Constants.KEY_COL_ID_BINOME + " TEXT, "
            + Constants.KEY_COL_NB_BINOME + " INTEGER, "
            + Constants.KEY_COL_NAME + " TEXT, "
            + Constants.KEY_COL_DESCRIPTION + " TEXT, "
            + Constants.KEY_COL_ELEMENT + " TEXT, "
            + Constants.KEY_COL_POLARITE + " TEXT, "
            + Constants.KEY_COL_TRONC_ORGANE + " TEXT, "
            + Constants.KEY_COL_TRONC_POLARITE + " TEXT, "
            + Constants.KEY_COL_TRONC_ELEMENT + " TEXT, "
            + Constants.KEY_COL_BRANCHE_ORGANE + " TEXT, "
            + Constants.KEY_COL_BRANCHE_POLARITE + " TEXT, "
            + Constants.KEY_COL_BRANCHE_ELEMENT + " TEXT) ";

    /**
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public TableBinome(Context context, String name, SQLiteDatabase.CursorFactory factory,
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
        Context c = App.getContext();
        InputStream inputStream = c.getResources().openRawResource(R.raw.binomes);
        List<Binome> listBinome=null;
        try {
            listBinome = ParseJSON.readJsonStreamBinome(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < listBinome.size(); i++) {
            Binome binome = listBinome.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.KEY_COL_ID_BINOME, binome.getIdBinome());
            contentValues.put(Constants.KEY_COL_NB_BINOME, binome.getNbBinome());
            contentValues.put(Constants.KEY_COL_NAME, binome.getNom());
            contentValues.put(Constants.KEY_COL_DESCRIPTION, binome.getDescription());
            contentValues.put(Constants.KEY_COL_ELEMENT, binome.getElement().getNom());
            contentValues.put(Constants.KEY_COL_POLARITE, binome.getPolarite());
            contentValues.put(Constants.KEY_COL_TRONC_ORGANE, binome.getOrganeTroncCeleste().getNom());
            contentValues.put(Constants.KEY_COL_TRONC_ELEMENT, binome.getOrganeTroncCeleste().getElement().getNom());
            contentValues.put(Constants.KEY_COL_TRONC_POLARITE, binome.getOrganeTroncCeleste().getPolarite());
            contentValues.put(Constants.KEY_COL_BRANCHE_ORGANE, binome.getOrganeBrancheTerrestre().getNom());
            contentValues.put(Constants.KEY_COL_BRANCHE_ELEMENT, binome.getOrganeBrancheTerrestre().getElement().getNom());
            contentValues.put(Constants.KEY_COL_BRANCHE_POLARITE, binome.getOrganeBrancheTerrestre().getPolarite());
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

    public Cursor getBinome(SQLiteDatabase db, String idBinome) {
        String selection = Constants.KEY_COL_NB_BINOME + "=?";
        String[] selectionArg = new String[]{idBinome};
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
        public static final String DATABASE_NAME = "Binome.db";

        // The database version
        public static final int DATABASE_VERSION = 3;

        // The table Name
        public static final String MY_TABLE = "Binome";

        // Noms de colonnes
        public static final String KEY_COL_ID = "_id";
        public static final String KEY_COL_ID_BINOME = "idBinome";
        public static final String KEY_COL_NB_BINOME = "nbBinome";
        public static final String KEY_COL_NAME = "name";
        public static final String KEY_COL_DESCRIPTION = "description";
        public static final String KEY_COL_ELEMENT = "element";
        public static final String KEY_COL_POLARITE = "polarite";
        public static final String KEY_COL_TRONC_ORGANE = "troncOrgane";
        public static final String KEY_COL_TRONC_POLARITE = "troncPolarite";
        public static final String KEY_COL_TRONC_ELEMENT = "troncElement";
        public static final String KEY_COL_BRANCHE_ORGANE = "brancheOrgane";
        public static final String KEY_COL_BRANCHE_POLARITE = "branchePolarite";
        public static final String KEY_COL_BRANCHE_ELEMENT = "brancheElement";

        // Index des colonnes
        public static final int ID_COLUMN = 0;
        public static final int ID_BINOME_COLUMN = 1;
        public static final int NB_BINOME_COLUMN = 2;
        public static final int NAME_COLUMN = 3;
        public static final int DESCRIPTION_COLUMN = 4;
        public static final int ELEMENT_COLUMN = 5;
        public static final int POLARITE_COLUMN = 6;
        public static final int TRONC_ORGANE_COLUMN = 7;
        public static final int TRONC_POLARITE_COLUMN = 8;
        public static final int TRONC_ELEMENT_COLUMN = 9;
        public static final int BRANCHE_ORGANE_COLUMN = 10;
        public static final int BRANCHE_POLARITE_COLUMN = 11;
        public static final int BRANCHE_ELEMENT_COLUMN = 12;
    }
}
