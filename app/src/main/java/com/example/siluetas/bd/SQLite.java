package com.example.siluetas.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;

import java.util.ArrayList;

public class SQLite  {
    private sql sql;
    private SQLiteDatabase db;

    public SQLite (Context context){
        sql = new sql(context);
    }

    public void abrir(){
        Log.i("SQLite", "Se abre conexion con DB" + sql.getDatabaseName());
        db = sql.getWritableDatabase();
    }

    public void cerrar(){
        Log.i("SQLite", "Se cierra conexion con DB" + sql.getDatabaseName());
        sql.close();
    }

    public boolean addRegistroUsuario( String email, String password, String image
    ){
        ContentValues cv = new ContentValues();

        cv.put("EMAIL", email);
        cv.put("PASSWORD", password);
        cv.put("IMAGEN", image);

        return (db.insert("USUARIOS", null, cv)!=-1) ? true : false;
    }

    public Cursor getRegistro(){
        return db.rawQuery("SELECT * FROM USUARIOS", null);
    }

    public ArrayList<String> getUSUARIOS(Cursor cursor){
        ArrayList<String> ListData = new ArrayList<>();
        String item = "";

        if (cursor.moveToFirst()){
            do {
                item += "Email: ["+ cursor.getString(3) + "]\r\n";
                item += "Password: ["+ cursor.getString(4 ) + "]\r\n";
                ListData.add(item);
                item="";

            }while (cursor.moveToNext());
        }
        return ListData;
    }

    public ArrayList<String> getImagenes(Cursor cursor){
        ArrayList<String> ListData = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                ListData.add(cursor.getString(6));
            }while (cursor.moveToNext());
        }
        return ListData;
    }

}
