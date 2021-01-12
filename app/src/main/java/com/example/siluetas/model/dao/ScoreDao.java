package com.example.siluetas.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.siluetas.model.Score;

import java.util.List;
@Dao
public interface ScoreDao {
    @Query("SELECT COUNT(*) FROM SCORES")
    int count();

    @Query("SELECT * FROM SCORES")
    List<Score> getAll();

    @Query("SELECT * FROM SCORES WHERE user_id = :id")
    Score findById(String id);


    @Insert
    void insert(Score score);


    @Update
    void update(Score score);
}
