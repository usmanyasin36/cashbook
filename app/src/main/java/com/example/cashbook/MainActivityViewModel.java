package com.example.cashbook;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.cashbook.account.model.Account;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private CashbookDatabase cashbookDatabase;
    public void initializeDatabase(Context context)
    {
        if(cashbookDatabase==null) {
            cashbookDatabase = Room.databaseBuilder(context, CashbookDatabase.class, "cashbookDB").allowMainThreadQueries().build();
        }
    }
}
