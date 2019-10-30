package com.example.cashbook;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cashbook.account.model.Account;
import com.example.cashbook.account.model.AccountDAO;
import com.example.cashbook.cashbook.model.CashBook;
import com.example.cashbook.cashbook.model.CashBookDAO;
import com.example.cashbook.categoory.model.Category;
import com.example.cashbook.categoory.model.CategoryDAO;
import com.example.cashbook.inventory.Inventory;
import com.example.cashbook.inventory.InventoryDAO;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CashbookRepository {
    private CategoryDAO categoryDAO;
    private AccountDAO accountDAO;
    private CashBookDAO cashbookDAO;
    private InventoryDAO inventoryDAO;
    private LiveData<List<Category>> categories;
    private Executor executor;

    public CashbookRepository(Application application) {
        executor= Executors.newFixedThreadPool(5);
        CashbookDatabase booksDatabase=CashbookDatabase.getInstance(application);
        categoryDAO=booksDatabase.categoryDAO();
        accountDAO=booksDatabase.accountDAO();
        cashbookDAO=booksDatabase.cashbookDAO();
        inventoryDAO=booksDatabase.inventoryDAO();
    }

    //Category Start
    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }

    public void getCategoryById(final long categoryId, final InterfaceCashbookRepository interfaceCashbookRepository) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Category category = categoryDAO.getCategoryById(categoryId);
                interfaceCashbookRepository.onCategoryFetched(category);
            }
        });
    }

    public void insertCategory(final Category category){

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.addCategory(category);
            }
        });
    }

    public void deleteCategory(final Category category){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.deleteCategory(category);
            }
        });
    }

    public void updateCategory(final Category category){

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.updateCategory(category);
            }
        });
    }
    //Category End


    //Account Start
    public LiveData<List<Account>> getAccounts() {
        return accountDAO.getAllAccounts();
    }

    public void getAccountById(final long accountId, final InterfaceCashbookRepository interfaceCashbookRepository) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Account account = accountDAO.getAccountById(accountId);
                interfaceCashbookRepository.onAccountFetched(account);
            }
        });
    }

    public void insertAccount(final Account account){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                accountDAO.addAccount(account);
            }
        });
    }

    public void deleteAccount(final Account account){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                accountDAO.deleteAccount(account);
            }
        });
    }

    public void updateAccount(final Account account){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                accountDAO.updateAccount(account);
            }
        });
    }
    //Account End

    //Cashbook Start
    public LiveData<List<CashBook>> getCashbooks() {
        return cashbookDAO.getAllCashbook();
    }

    public void getCashbookById(final long cashbookId, final InterfaceCashbookRepository interfaceCashbookRepository) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                CashBook cashBook = cashbookDAO.getCashbookById(cashbookId);
                interfaceCashbookRepository.onCashbookFetched(cashBook);
            }
        });
    }

    public void insertCashbook(final CashBook cashBook){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cashbookDAO.addCashbook(cashBook);
            }
        });
    }

    public void deleteCashbook(final CashBook cashBook){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cashbookDAO.deleteCashbook(cashBook);
            }
        });
    }

    public void updateCashbook(final CashBook cashBook){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cashbookDAO.updateCashbook(cashBook);
            }
        });
    }
    //Cashbook End

    //Inventory Start
    public LiveData<List<Inventory>> getInventory() {
        return inventoryDAO.getAllInventories();
    }

    public List<Inventory> getInventoryByCategoryId(final long categoryId) {
        return inventoryDAO.getInventoryByCategoryById(categoryId);
    }

    public void insertInventory(final Inventory inventory){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                inventoryDAO.addInventory(inventory);
            }
        });
    }

    public void deleteInventory(final Inventory inventory){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                inventoryDAO.deleteInventory(inventory);
            }
        });
    }

    public void updateInventory(final Inventory inventory){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                inventoryDAO.updateInventory(inventory);
            }
        });
    }
    //Cashbook End
}
