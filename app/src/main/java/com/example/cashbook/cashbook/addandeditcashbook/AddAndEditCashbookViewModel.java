package com.example.cashbook.cashbook.addandeditcashbook;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cashbook.CashbookRepository;
import com.example.cashbook.InterfaceCashbookRepository;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.cashbook.model.CashBook;
import com.example.cashbook.categoory.model.Category;
import com.example.cashbook.inventory.Inventory;

import java.util.List;

public class AddAndEditCashbookViewModel extends AndroidViewModel {
    private CashbookRepository cashbookRepository;

    public AddAndEditCashbookViewModel(@NonNull Application application) {
        super(application);

        cashbookRepository=new CashbookRepository(application);
    }

    public void getCashbookById(long cashbookId, final InterfaceCashbookRepository interfaceCashbookRepository) {
        cashbookRepository.getCashbookById(cashbookId, new InterfaceCashbookRepository() {

            @Override
            public void onCategoryFetched(Category category) {

            }

            @Override
            public void onAccountFetched(Account account) {

            }

            @Override
            public void onCashbookFetched(CashBook cashBook) {
                interfaceCashbookRepository.onCashbookFetched(cashBook);
            }
        });
    }

    public void addNewCashbook(CashBook cashBook){
        cashbookRepository.insertCashbook(cashBook);
    }

    public void updateCashbook(CashBook cashBook){
        cashbookRepository.updateCashbook(cashBook);
    }

    public void deleteCashbook(CashBook cashBook){
        cashbookRepository.deleteCashbook(cashBook);
    }

    public LiveData<List<Account>> getAccounts(){
        return cashbookRepository.getAccounts();
    }

    public LiveData<List<Category>> getItems(){
        return cashbookRepository.getCategories();
    }

    public void updateAccount(Account account){
        cashbookRepository.updateAccount(account);
    }

    public List<Inventory> getInventoryByCategoryId(long categoryId){
        return cashbookRepository.getInventoryByCategoryId(categoryId);
    }

    public boolean isSpinnerEditable(CashBook cashBook)
    {
        if(cashBook.getCashbookId()==0){
            return true;
        }else{
            return false;
        }
    }
}