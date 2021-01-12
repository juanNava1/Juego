package com.example.siluetas.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.siluetas.model.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

}
