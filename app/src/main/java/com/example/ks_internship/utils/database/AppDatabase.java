package com.example.ks_internship.utils.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ks_internship.model.GitRepoItem;

@Database(entities = {GitRepoItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RepoItemDao repoItemDao();

}
