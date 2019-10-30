package com.example.cashbook.categoory.model.addandedit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cashbook.CashbookRepository;
import com.example.cashbook.InterfaceCashbookRepository;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.cashbook.model.CashBook;
import com.example.cashbook.categoory.model.Category;

import java.util.List;

public class AddAndEditViewModel extends AndroidViewModel {
    private CashbookRepository cashbookRepository;

    public AddAndEditViewModel(@NonNull Application application) {
        super(application);

        cashbookRepository=new CashbookRepository(application);
    }

    public void getCategoryById(long categoryId, final InterfaceCashbookRepository interfaceCashbookRepository) {
        cashbookRepository.getCategoryById(categoryId, new InterfaceCashbookRepository() {
            @Override
            public void onCategoryFetched(Category category) {
                interfaceCashbookRepository.onCategoryFetched(category);
            }

            @Override
            public void onAccountFetched(Account category) {

            }

            @Override
            public void onCashbookFetched(CashBook cashBook) {

            }
        });
    }

    public void addNewCategory(Category category){
        cashbookRepository.insertCategory(category);
    }

    public void updateCategory(Category category){
        cashbookRepository.updateCategory(category);
    }

    public void deleteCategory(Category category){
        cashbookRepository.deleteCategory(category);
    }
}