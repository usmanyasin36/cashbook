package com.example.cashbook.inventory;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cashbook.categoory.model.Category;

import java.util.List;

@Dao
public interface InventoryDAO {

    @Insert
    public long addInventory(Inventory inventory);

    @Update
    public void updateInventory(Inventory inventory);

    @Delete
    public void deleteInventory(Inventory inventory);

    @Query("select * from table_inventory")
    public LiveData<List<Inventory>> getAllInventories();

    @Query("select * from table_inventory where categoryId == :categoryId ORDER BY createdDate ASC")
    public List<Inventory> getInventoryByCategoryById(long categoryId);

}
