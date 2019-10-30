package com.example.cashbook.account.model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccountDAO {

    @Insert
    public long addAccount(Account account);

    @Update
    public void updateAccount(Account account);

    @Delete
    public void deleteAccount(Account account);

    @Query("select * from table_account")
    public LiveData<List<Account>> getAllAccounts();

    @Query("select * from table_account where accountId == :accountId")
    public Account getAccountById(long accountId);
}