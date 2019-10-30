package com.example.cashbook.account;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.cashbook.CashbookRepository;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.categoory.model.Category;

import java.util.List;

public class AccountViewModel extends AndroidViewModel {
    private CashbookRepository cashbookRepository;
    private LiveData<List<Account>> allAccounts;

    public AccountViewModel(@NonNull Application application) {
        super(application);

        cashbookRepository = new CashbookRepository(application);
    }

    public LiveData<List<Account>> getAllAccounts() {
        allAccounts = cashbookRepository.getAccounts();
        return allAccounts;
    }

    public void addNewAccount(Account account) {
        cashbookRepository.insertAccount(account);
    }

    public void updateAccount(Account account) {
        cashbookRepository.updateAccount(account);
    }

    public void deleteAccount(Account account) {
        cashbookRepository.deleteAccount(account);
    }
}