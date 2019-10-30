package com.example.cashbook.categoory.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cashbook.CashbookRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private CashbookRepository cashbookRepository;
    private LiveData<List<Category>> allCategories;

    public CategoryViewModel(@NonNull Application application) {
        super(application);

        cashbookRepository=new CashbookRepository(application);
    }

    public LiveData<List<Category>> getAllCategories() {
        allCategories=cashbookRepository.getCategories();
        return allCategories;
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