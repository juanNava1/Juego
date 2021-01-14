package com.example.siluetas.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.siluetas.database.AppDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(tableName = "scores")
public class Score {
    @PrimaryKey(autoGenerate = true)
    public int id;
    //    @ColumnInfo(name = "uuid")
//    public String uuid;
    //      User id
    @ColumnInfo(name = "user_id")
    private String userid;
    //      Puntaje de juego de sombras
    @ColumnInfo(name = "score_shadows")
    @Nullable
    private String score_shadows;
    //      Puntaje de juego de sonidos
    @ColumnInfo(name = "score_sounds")
    @Nullable
    private String score_sounds;
    //      Puntaje de operaciones aritméticas
    @ColumnInfo(name = "score_res")
    @Nullable
    private String score_res;

    @ColumnInfo(name = "score_add")
    @Nullable
    private String score_add;

    public Score(String score_shadows, String score_sounds, String score_add, String userid, String score_res) {
        this.score_shadows = score_shadows;
        this.score_sounds = score_sounds;
        this.score_add = score_add;
        this.userid = userid;
        this.score_res = score_res;
    }

    public Score() {
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", score_shadows='" + score_shadows + '\'' +
                ", score_sounds='" + score_sounds + '\'' +
                ", score_res='" + score_res + '\'' +
                ", score_add='" + score_add + '\'' +
                '}';
    }

    //    public int getUid() {
//        return uid;
//    }
//
//    public void setUid(int uid) {
//        this.uid = uid;
//    }
//
//    public String getUuid() {
//        return uuid;
//    }
//
//    public void setUuid(String uuid) {
//        this.uuid = uuid;
//    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getScore_res() {
        return score_res;
    }

    public void setScore_res(@Nullable String score_res) {
        this.score_res = score_res;
    }

    @Nullable
    public String getScore_add() {
        return score_add;
    }

    public void setScore_add(@Nullable String score_add) {
        this.score_add = score_add;
    }

    public void insert(Context context) {
        //  SQLite
        AppDatabase.getInstance(context).scoreDao().insert(this);
        //  Firebase
        AppDatabase.getFirebaseDbInstance().child("scores").child(this.userid).setValue(this);

    }


    public void update(Context context) {
        //  SQLite
        AppDatabase.getInstance(context).scoreDao().update(this);
        //  Firebase
        AppDatabase.getFirebaseDbInstance().child("scores").child(this.userid).setValue(this);

    }


    public static Score findById(Context context, String userid) {
        return AppDatabase.getInstance(context).scoreDao().findById(userid);
    }

    public static Score findUser(Context context, String userid) {
        return AppDatabase.getInstance(context).scoreDao().findById(userid);
    }

//    public static Score findScore(Context context, String userid) {
//        return AppDatabase.getInstance(context).scoreDao().findById(userid);
//    }

    // FIND SCORE ON FIREBASE

    public static DatabaseReference findScore(String userid) {
        return AppDatabase.getFirebaseDbInstance().child("scores").child(userid);
    }

}
