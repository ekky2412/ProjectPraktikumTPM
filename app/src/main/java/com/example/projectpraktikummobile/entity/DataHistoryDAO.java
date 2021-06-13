package com.example.projectpraktikummobile.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataHistoryDAO {
    @Insert
    Long insertData(DataHistory dataHistory);

    @Query("Select * from history_db")
    List<DataHistory> getData();

    @Delete
    void deleteData(DataHistory item);
}
