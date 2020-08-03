package com.example.ks_internship.utils.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ks_internship.model.GitRepoItem;

import java.util.List;

@Dao
public interface RepoItemDao {

    @Query("SELECT * FROM gitRepoItems")
    LiveData<List<GitRepoItem>> getAllRecords();

    @Query("SELECT * FROM gitRepoItems WHERE id = :id")
    LiveData<GitRepoItem> getById(int id);

    @Insert
    void insert(GitRepoItem item);

    @Insert
    void insert(List<GitRepoItem> items);

    @Update
    void update(GitRepoItem item);

    @Delete
    void delete(GitRepoItem item);

    @Query("DELETE FROM gitRepoItems")
    void deleteAllRecords();
}
