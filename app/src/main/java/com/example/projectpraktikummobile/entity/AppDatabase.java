package com.example.projectpraktikummobile.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataHistory.class}, version = 1)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract DataHistoryDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase inidb(Context context){
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(context,AppDatabase.class,"dbHistory").allowMainThreadQueries().build();

        }
        return appDatabase;
    }
}
