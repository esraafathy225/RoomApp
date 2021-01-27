package com.company.roomproject;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DatabaseDAO {

    @Insert
    void insertAll(Contact... contacts);

    @Query("SELECT * FROM contact")
    List<Contact> getAll();

    @Query("SELECT * FROM contact WHERE id = :id ")
    Contact getContact(int id);


    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}
