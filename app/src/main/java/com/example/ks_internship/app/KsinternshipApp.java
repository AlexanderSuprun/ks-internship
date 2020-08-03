package com.example.ks_internship.app;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ks_internship.utils.database.AppDatabase;

public class KsinternshipApp extends Application {

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(this, AppDatabase.class, "gitItems")
                .allowMainThreadQueries()
                .build();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
