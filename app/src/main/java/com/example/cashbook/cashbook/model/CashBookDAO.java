package com.example.cashbook.cashbook.model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cashbook.account.model.Account;

import java.util.List;

@Dao
public interface CashBookDAO {

    @Insert
    public long addCashbook(CashBook cashBook);

    @Update
    public void updateCashbook(CashBook cashBook);

    @Delete
    public void deleteCashbook(CashBook cashBook);

    @Query("select * from table_cashbook")
    public LiveData<List<CashBook>> getAllCashbook();

    @Query("select * from table_cashbook where cashbookId == :cashbookId")
    public CashBook getCashbookById(long cashbookId);

}
