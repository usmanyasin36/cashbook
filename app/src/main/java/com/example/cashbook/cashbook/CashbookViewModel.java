package com.example.cashbook.cashbook;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cashbook.CashbookRepository;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.cashbook.model.CashBook;

import java.util.List;

public class CashbookViewModel extends AndroidViewModel {
    private CashbookRepository cashbookRepository;
    private LiveData<List<CashBook>> allCashbooks;

    public CashbookViewModel(@NonNull Application application) {
        super(application);

        cashbookRepository = new CashbookRepository(application);
    }

    public LiveData<List<CashBook>> getAllCashbooks() {
        allCashbooks = cashbookRepository.getCashbooks();
        return allCashbooks;
    }

    public void addNewCashbook(CashBook cashBook) {
        cashbookRepository.insertCashbook(cashBook);
    }

    public void updateCashbook(CashBook cashBook) {
        cashbookRepository.updateCashbook(cashBook);
    }

    public void deleteCashbook(CashBook cashBook) {
        cashbookRepository.deleteCashbook(cashBook);
    }
}