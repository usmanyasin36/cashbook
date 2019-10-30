package com.example.cashbook.cashbook.addandeditcashbook;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cashbook.InterfaceCashbookRepository;
import com.example.cashbook.R;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.cashbook.model.CashBook;
import com.example.cashbook.categoory.model.Category;
import com.example.cashbook.databinding.AddAndEditAccountFragmentBinding;
import com.example.cashbook.databinding.AddAndEditCashbookFragmentBinding;
import com.example.cashbook.inventory.Inventory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddAndEditCashbookFragment extends Fragment{

    private AddAndEditCashbookViewModel mViewModel;
    private AddAndEditCashbookFragmentBinding addAndEditFragmentBinding;
    private AddAndEditCashbookFragmentClickHandlers addAndEditFragmentClickHandlers;
    private CashBook cashBook;
    private Inventory selectedInventory;
    private ArrayList<Account> accountList;
    private ArrayList<Category> itemList;
    Account selectedAccount;
    Category selectedItem;

    public static AddAndEditCashbookFragment newInstance() {
        return new AddAndEditCashbookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        addAndEditFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.add_and_edit_cashbook_fragment,container,false);
        addAndEditFragmentBinding.setClickHandlers(new AddAndEditCashbookFragmentClickHandlers(getContext()));

        mViewModel = ViewModelProviders.of(this).get(AddAndEditCashbookViewModel.class);

        cashBook = new CashBook();
        selectedInventory = new Inventory();
        addAndEditFragmentBinding.setCashbook(cashBook);
        addAndEditFragmentBinding.setInventory(selectedInventory);
        Bundle bundle = getArguments();
        if(bundle!=null){
            long cashbookid = Long.parseLong(bundle.getString("cashbookid"));
            mViewModel.getCashbookById(cashbookid, new InterfaceCashbookRepository() {
                @Override
                public void onCategoryFetched(Category tempCategory) {

                }

                @Override
                public void onAccountFetched(Account account) {

                }

                @Override
                public void onCashbookFetched(CashBook tempCashBook) {
                    cashBook = tempCashBook;
                    addAndEditFragmentBinding.setCashbook(cashBook);
                }
            });
        }

        //show accounts
        mViewModel.getAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                accountList = (ArrayList<Account>) accounts;
                showAccountsSpinner();
            }
        });

        //show items
        mViewModel.getItems().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                itemList = (ArrayList<Category>) categories;
                showItemsSpinner();
            }
        });

        if(!mViewModel.isSpinnerEditable(cashBook)){
            addAndEditFragmentBinding.spinner.setEnabled(false);
            addAndEditFragmentBinding.spinner.setClickable(false);

            addAndEditFragmentBinding.spinnerItems.setEnabled(false);
            addAndEditFragmentBinding.spinnerItems.setClickable(false);
        }

        return addAndEditFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    private void showAccountsSpinner() {
        ArrayAdapter<Account> categoryArrayAdapter = new ArrayAdapter<Account>(getContext(), R.layout.spinner_item, accountList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        addAndEditFragmentBinding.setAccountsSpinnerAdapter(categoryArrayAdapter);
    }

    private void showItemsSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<Category>(getContext(), R.layout.spinner_item, itemList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        addAndEditFragmentBinding.setItemsSpinnerAdapter(categoryArrayAdapter);
    }

    public class AddAndEditCashbookFragmentClickHandlers{
        Context context;

        public AddAndEditCashbookFragmentClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view){
            if(cashBook.getMemo()==null || selectedItem == null || selectedAccount ==null){
                Toast.makeText(context,"Memo field cannot be empty",Toast.LENGTH_LONG).show();
            }else{
                cashBook.setEditedDate(new Date());

                long balance=0;
                long debitAmount=0;
                long creditAmount=0;
                boolean cash;
                if(selectedAccount!=null && !selectedAccount.getBalance().equals("")){
                    balance = Long.parseLong(selectedAccount.getBalance());
                    if(selectedAccount.getName().equalsIgnoreCase("cash")){
                        cash = true;
                    }
                }
                //update debit
                if(cashBook.getDebitAmount()!=null && !cashBook.getDebitAmount().equals("")){
                    debitAmount = Long.parseLong(cashBook.getDebitAmount());
                    if(selectedAccount!=null){
                        if(selectedAccount.getStartingBalance()==null || selectedAccount.getStartingBalance().equals("")){
                            selectedAccount.setStartingBalance(cashBook.getDebitAmount());
                        }
                        balance = balance - debitAmount;
                    }
                    //update inventory
                    List<Inventory> inventories = mViewModel.getInventoryByCategoryId(selectedItem.getCategoryId());
                    //removeQuantityFromInventoryRecursive(inventories);
                }
                //update credit
                if(cashBook.getCreditAmount()!=null && !cashBook.getCreditAmount().equals("")){
                    creditAmount = Long.parseLong(cashBook.getCreditAmount());
                    if(selectedAccount!=null){
                        if(selectedAccount.getStartingBalance()==null || selectedAccount.getStartingBalance().equals("")){
                            selectedAccount.setStartingBalance(cashBook.getCreditAmount());
                        }
                        balance = balance + creditAmount;
                    }
                }

                selectedAccount.setBalance(String.valueOf(balance));
                mViewModel.updateAccount(selectedAccount);

                if(cashBook.getCashbookId()==0){
                    cashBook.setCreatedDate(new Date());
                    mViewModel.addNewCashbook(cashBook);
                }else{
                    mViewModel.updateCashbook(cashBook);
                }

                try {
                    getFragmentManager().popBackStack();
                }catch (Exception e){
                    int h=1;
                }
            }
        }


        public void onAccountItemClicked(AdapterView<?> parent, View view, int pos, long id) {
            selectedAccount = (Account) parent.getItemAtPosition(pos);
        }

        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            selectedItem = (Category) parent.getItemAtPosition(pos);
        }

        public void removeQuantityFromInventory(List<Inventory> inventories){
            for(Inventory inventory : inventories){
                if(inventory.getRemainingQuantity()!=0){
                    //removeQuantityRecursively(inventory);
                    long totalQuantity = inventory.getRemainingQuantity()-selectedInventory.getRemainingQuantity();
                    if(totalQuantity<0){
                        Toast.makeText(getContext(),"Error.",Toast.LENGTH_LONG).show();
                    }
                    if(totalQuantity==0){

                    }

                }
            }
        }

//        public long removeQuantityRecursively(Inventory inventory){
//            if(inventory.getRemainingQuantity()-selectedInventory.getRemainingQuantity()>0){
//                inventory.getRemainingQuantity()-selectedInventory.getRemainingQuantity();
//            }
//        }
    }
}
