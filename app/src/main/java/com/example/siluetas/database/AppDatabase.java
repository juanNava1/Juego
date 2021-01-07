package com.example.siluetas.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.siluetas.model.dao.ScoreDao;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.example.siluetas.model.Score;


@Database(entities = {Score.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "TDAH").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public static DatabaseReference getFirebaseDbInstance() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public static StorageReference getFirebaseStorageInstance() {
        return FirebaseStorage.getInstance().getReference();
    }

    public abstract ScoreDao scoreDao();
}
