package com.example.siluetas.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.siluetas.database.AppDatabase;

import java.util.UUID;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "uuid")
    public String uuid;
    //  Aquí van todos los atributos, arriba está ya el id, agrega lo demás
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "edad")
    private String edad;
    @ColumnInfo(name = "pais")
    private String pais;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;

    //foto entonces no?
   // Si no la almacenas, no, despues de guardar todo veo si puedo almacenarla
    public User(String nombre, String edad, String pais, String email, String password) {
        this.nombre = nombre;
        this.edad = edad;
        this.pais = pais;
        this.email = email;
        this.password = password;
    }

    // constructor
    public User(){

    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //todo lo demas tambien? o solo la parte de insertar?
    public boolean isValid() {
        return nombre != null && edad != null && pais != null && email != null && password != null;
    }

    public void insert(Context context) {
        this.uuid = UUID.randomUUID().toString();
        //  SQLite
        AppDatabase.getInstance(context).userDao().insert(this);
        //  Firebase
        AppDatabase.getFirebaseDbInstance().child("users").child(this.uuid).setValue(this);
        //  Storage
    }

    //que mas? segun yo ya no?

}
