package com.example.cashbook.account.addandeditaccount;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.cashbook.CashbookRepository;
import com.example.cashbook.InterfaceCashbookRepository;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.cashbook.model.CashBook;
import com.example.cashbook.categoory.model.Category;

public class AddAndEditAccountViewModel extends AndroidViewModel {
    private CashbookRepository cashbookRepository;

    public AddAndEditAccountViewModel(@NonNull Application application) {
        super(application);

        cashbookRepository=new CashbookRepository(application);
    }

    public void getAccountById(long accountId, final InterfaceCashbookRepository interfaceCashbookRepository) {
        cashbookRepository.getAccountById(accountId, new InterfaceCashbookRepository() {

            @Override
            public void onCategoryFetched(Category category) {

            }

            @Override
            public void onAccountFetched(Account account) {
                interfaceCashbookRepository.onAccountFetched(account);
            }

            @Override
            public void onCashbookFetched(CashBook cashBook) {

            }
        });
    }

    public void addNewAccount(Account account){
        cashbookRepository.insertAccount(account);
    }

    public void updateAccount(Account account){
        cashbookRepository.updateAccount(account);
    }

    public void deleteAccount(Account account){
        cashbookRepository.deleteAccount(account);
    }
}