package com.fibredariane.horoscope.chinois.biorythmeETRES.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.App;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Horoscope;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.TableBinome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.TableHoroscope;

/**
 * Created by Carlotina on 05/12/2018.
 */

public class ManageRecordDB {

    private SQLiteDatabase dbBinome;
    private SQLiteDatabase dbHoroscope;
    TableBinome tableBinome;
    TableHoroscope tableHoroscope;

    public void initTables(){
        Context c = App.getContext();
        tableBinome = new TableBinome(c,TableBinome.Constants.DATABASE_NAME, null,
                TableBinome.Constants.DATABASE_VERSION);
        dbBinome = tableBinome.openDB();
        if ( DatabaseUtils.queryNumEntries(dbBinome, TableBinome.Constants.MY_TABLE) == 0){
            tableBinome.initTable(dbBinome);
        }

        tableHoroscope = new TableHoroscope(c,TableHoroscope.Constants.DATABASE_NAME, null,
                TableHoroscope.Constants.DATABASE_VERSION);
        dbHoroscope = tableHoroscope.openDB();
        if ( DatabaseUtils.queryNumEntries(dbHoroscope, TableHoroscope.Constants.MY_TABLE) == 0){
            tableHoroscope.initTable(dbHoroscope);
        }
    }

    public void openDBs(){
        dbBinome = tableBinome.openDB();
        dbHoroscope = tableHoroscope.openDB();
    }

    public void closeDBs(){
        tableBinome.closeDB(dbBinome);
        tableHoroscope.closeDB(dbHoroscope);
    }

    public Binome getBinome(String idBinome,String typeBinome){
        Cursor cursor = tableBinome.getBinome(dbBinome, idBinome);
        Binome binome = new Binome(cursor,typeBinome);

        return binome;
    }

    public Horoscope getHoroscope(String typeHoroscope, Binome binomeHoroscope, Biorythme biorythmeUser){
        Cursor cursorAnnee = tableHoroscope.getHoroscope(dbHoroscope,
                binomeHoroscope.getNbBinome(),
                typeHoroscope,
                biorythmeUser.getBinomeAnnee().getElement().getNom(),
                biorythmeUser.getBinomeAnnee().getPolarite());

        Cursor cursorMois = tableHoroscope.getHoroscope(dbHoroscope,
                binomeHoroscope.getNbBinome(),
                typeHoroscope,
                biorythmeUser.getBinomeMois().getElement().getNom(),
                biorythmeUser.getBinomeMois().getPolarite());

        Cursor cursorJour = tableHoroscope.getHoroscope(dbHoroscope,
                binomeHoroscope.getNbBinome(),
                typeHoroscope,
                biorythmeUser.getBinomeJour().getElement().getNom(),
                biorythmeUser.getBinomeJour().getPolarite());

        Cursor cursorHeure = tableHoroscope.getHoroscope(dbHoroscope,
                binomeHoroscope.getNbBinome(),
                typeHoroscope,
                biorythmeUser.getBinomeHeure().getElement().getNom(),
                biorythmeUser.getBinomeHeure().getPolarite());

        Horoscope horoscope = new Horoscope(cursorAnnee,cursorMois,cursorJour,cursorHeure,binomeHoroscope);

        return horoscope;
    }

}
