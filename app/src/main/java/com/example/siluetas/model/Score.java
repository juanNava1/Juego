package com.example.siluetas.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.siluetas.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(tableName = "scores")
public class Score{
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "uuid")
    public String uuid;
    //      User id
    @ColumnInfo(name = "user_id")
    @NonNull
    private int userid;
    //      Puntaje de juego de sombras
    @ColumnInfo(name = "score_shadows")
    @Nullable
    private String score_shadows;
    //      Puntaje de juego de sonidos
    @ColumnInfo(name = "score_sounds")
    @Nullable
    private String score_sounds;
    //      Puntaje de operaciones aritméticas
    @ColumnInfo(name = "score_operations")
    @Nullable
    private String score_operations;


    public Score(String score_shadows, String score_sounds, String score_operations, int user_id) {
        this.score_shadows = score_shadows;
        this.score_sounds = score_sounds;
        this.score_operations = score_operations;
        this.userid = user_id;
    }

    public Score() {
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
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Nullable
    public String getScore_shadows() {
        return score_shadows;
    }

    public void setScore_shadows(@Nullable String score_shadows) {
        this.score_shadows = score_shadows;
    }

    @Nullable
    public String getScore_sounds() {
        return score_sounds;
    }

    public void setScore_sounds(@Nullable String score_sounds) {
        this.score_sounds = score_sounds;
    }

    @Nullable
    public String getScore_operations() {
        return score_operations;
    }

    public void setScore_operations(@Nullable String score_operations) {
        this.score_operations = score_operations;
    }


    public void insert(Context context) {
        this.uuid = UUID.randomUUID().toString();
        //  SQLite
        AppDatabase.getInstance(context).scoreDao().insert(this);
        //  Firebase
        AppDatabase.getFirebaseDbInstance().child("scores").child(this.uuid).setValue(this);

    }


    public void update(Context context) {
        //  SQLite
        AppDatabase.getInstance(context).scoreDao().update(this);
        //  Firebase
        AppDatabase.getFirebaseDbInstance().child("scores").child(this.uuid).setValue(this);

    }


}
