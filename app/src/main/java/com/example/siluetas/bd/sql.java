package com.example.siluetas.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class sql extends SQLiteOpenHelper {

    private static final String database = "usuarios";
    private static final int VERSION = 102;

    private final String tUsuarios = "CREATE TABLE USUARIOS ("+
            "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            "NOMBRE TEXT, "+
            "EDAD TEXT NOT NULL, "+
            "PAIS TEXT NOT NULL,"+
            "EMAIL TEXT NOT NULL, "+
            "PASSWORD TEXT NOT NULL, "+
            "IMAGEN TEXT NOT NULL );";

    public sql(Context context){
        super(context, database, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tUsuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS USUARIOS");
            db.execSQL(tUsuarios);

        }

    }
}

