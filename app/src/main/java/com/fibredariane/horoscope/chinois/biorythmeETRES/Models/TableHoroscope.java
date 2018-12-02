package com.fibredariane.horoscope.chinois.biorythmeETRES.Models;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.CursorJoiner;
        import android.database.DatabaseUtils;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteException;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.sqlite.SQLiteQueryBuilder;
        import android.provider.BaseColumns;
        import android.util.Log;
        import android.widget.Toast;

        import com.fibredariane.horoscope.chinois.biorythmeETRES.R;

        import org.json.JSONObject;


public class TableHoroscope extends SQLiteOpenHelper {

    private static final String DATABASE_CREATE = "create table "
            + Constants.MY_TABLE + "(" + Constants.KEY_COL_ID
            + " integer primary key autoincrement, "
            + Constants.KEY_COL_NB_BINOME + " INTEGER, "
            + Constants.KEY_COL_TYPE_HOROSCOPE + " TEXT, "
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

        deleteDB(db);
        onCreate(db);
    }

    public void initTable(SQLiteDatabase db, Context c) {
        long numRows = DatabaseUtils.queryNumEntries(db, Constants.MY_TABLE);

        deleteDB(db);
        onCreate(db);

        initHoroscope(db, c, "A");
        initHoroscope(db, c, "M");
        initHoroscope(db, c, "J");
    }

    public void initHoroscope(SQLiteDatabase db, Context c, String typeHoroscope) {
       for (int i = 1; i <= 60; i++) {
            String string_binome = c.getResources().getString(
                    c.getResources().getIdentifier(
                            "binome" + i +"_"+typeHoroscope.toLowerCase(),
                            "string",
                            c.getPackageName()));
            if (string_binome != "") {
                try {
                    JSONObject json = new JSONObject(string_binome);

                    JSONObject jsonFils = new JSONObject(json.getString("bois_yin"));
                    insertHoroscope(db, i, typeHoroscope, "BOIS","YIN",
                            jsonFils.getString("influence"),jsonFils.getString("annee"),
                            jsonFils.getString("mois"),jsonFils.getString("jour"),
                            jsonFils.getString("heure"));

                    jsonFils = new JSONObject(json.getString("bois_yang"));
                    insertHoroscope(db, i, typeHoroscope, "BOIS","YANG",
                            jsonFils.getString("influence"),jsonFils.getString("annee"),
                            jsonFils.getString("mois"),jsonFils.getString("jour"),
                            jsonFils.getString("heure"));

                    jsonFils = new JSONObject(json.getString("feu_yin"));
                    insertHoroscope(db, i, typeHoroscope, "FEU","YIN",
                            jsonFils.getString("influence"),jsonFils.getString("annee"),
                            jsonFils.getString("mois"),jsonFils.getString("jour"),
                            jsonFils.getString("heure"));

                    jsonFils = new JSONObject(json.getString("feu_yang"));
                    insertHoroscope(db, i, typeHoroscope, "FEU","YANG",
                            jsonFils.getString("influence"),jsonFils.getString("annee"),
                            jsonFils.getString("mois"),jsonFils.getString("jour"),
                            jsonFils.getString("heure"));

                    jsonFils = new JSONObject(json.getString("eau_yin"));
                    insertHoroscope(db, i, typeHoroscope, "EAU","YIN",
                            jsonFils.getString("influence"),jsonFils.getString("annee"),
                            jsonFils.getString("mois"),jsonFils.getString("jour"),
                            jsonFils.getString("heure"));

                    jsonFils = new JSONObject(json.getString("eau_yang"));
                    insertHoroscope(db, i, typeHoroscope, "EAU","YANG",
                            jsonFils.getString("influence"),jsonFils.getString("annee"),
                            jsonFils.getString("mois"),jsonFils.getString("jour"),
                            jsonFils.getString("heure"));

                    jsonFils = new JSONObject(json.getString("terre_yin"));
                    insertHoroscope(db, i, typeHoroscope, "TERRE","YIN",
                            jsonFils.getString("influence"),jsonFils.getString("annee"),
                            jsonFils.getString("mois"),jsonFils.getString("jour"),
                            jsonFils.getString("heure"));

                    jsonFils = new JSONObject(json.getString("terre_yang"));
                    insertHoroscope(db, i, typeHoroscope, "TERRE","YANG",
                            jsonFils.getString("influence"),jsonFils.getString("annee"),
                            jsonFils.getString("mois"),jsonFils.getString("jour"),
                            jsonFils.getString("heure"));

                    jsonFils = new JSONObject(json.getString("metal_yin"));
                    insertHoroscope(db, i, typeHoroscope, "METAL","YIN",
                            jsonFils.getString("influence"),jsonFils.getString("annee"),
                            jsonFils.getString("mois"),jsonFils.getString("jour"),
                            jsonFils.getString("heure"));

                    jsonFils = new JSONObject(json.getString("metal_yang"));
                    insertHoroscope(db, i, typeHoroscope, "METAL","YANG",
                            jsonFils.getString("influence"),jsonFils.getString("annee"),
                            jsonFils.getString("mois"),jsonFils.getString("jour"),
                            jsonFils.getString("heure"));

                } catch (Exception e) {
                    Log.v("TAG", "Horoscope - JSON - erreur"+i
                    +"  "+
                            string_binome);
                    e.printStackTrace();
                }
            }
        }
    }

    public void insertHoroscope(SQLiteDatabase db, int nbBinome, String typeHoroscope, String element,
                                String polarite, String influence,String texteAnnee, String texteMois,
                                String texteJour, String texteHeure) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.KEY_COL_NB_BINOME, nbBinome);
        contentValues.put(Constants.KEY_COL_TYPE_HOROSCOPE, typeHoroscope);
        contentValues.put(Constants.KEY_COL_ELEMENT, element);
        contentValues.put(Constants.KEY_COL_POLARITE, polarite);
        contentValues.put(Constants.KEY_COL_INFLUENCE, influence);
        contentValues.put(Constants.KEY_COL_TEXTE_ANNEE, texteAnnee);
        contentValues.put(Constants.KEY_COL_TEXTE_MOIS, texteMois);
        contentValues.put(Constants.KEY_COL_TEXTE_JOUR, texteJour);
        contentValues.put(Constants.KEY_COL_TEXTE_HEURE, texteHeure);
        //
        insertRecord(db, contentValues);
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

    public Cursor getHoroscope(SQLiteDatabase db, int nbBinome, String typeHoroscope,String element, String polarite) {

        String selection = Constants.KEY_COL_NB_BINOME + "=?"
                +" and " + Constants.KEY_COL_TYPE_HOROSCOPE + "=? "
                +" and " +Constants.KEY_COL_ELEMENT + "=?"
                +" and " +Constants.KEY_COL_POLARITE + "=?" ;
        String[] selectionArg = new String[]{String.valueOf(nbBinome),typeHoroscope,element,polarite};
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
        public static final String KEY_COL_TYPE_HOROSCOPE = "typeBinome";
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
        public static final int TYPE_HOROSCOPE_COLUMN = 2;
        public static final int ELEMENT_COLUMN = 3;
        public static final int POLARITE_COLUMN = 4;
        public static final int INFLUENCE_COLUMN = 5;
        public static final int TEXTE_ANNEE_COLUMN = 6;
        public static final int TEXTE_MOIS_COLUMN = 7;
        public static final int TEXTE_JOUR_COLUMN = 8;
        public static final int TEXTE_HEURE_COLUMN = 9;
    }
}
