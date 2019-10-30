package com.example.cashbook.categoory.model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cashbook.account.model.Account;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Insert
    public long addCategory(Category category);

    @Update
    public void updateCategory(Category category);

    @Delete
    public void deleteCategory(Category category);

    @Query("select * from table_category")
    public LiveData<List<Category>> getAllCategories();

    @Query("select * from table_category where categoryId == :categoryId")
    public Category getCategoryById(long categoryId);

}
