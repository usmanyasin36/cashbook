package com.example.cashbook.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashbook.MainActivity;
import com.example.cashbook.R;
import com.example.cashbook.account.addandeditaccount.AddAndEditAccountFragment;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.categoory.model.Category;
import com.example.cashbook.categoory.model.CategoryAdapter;
import com.example.cashbook.categoory.model.CategoryViewModel;
import com.example.cashbook.categoory.model.addandedit.AddAndEditFragment;
import com.example.cashbook.databinding.AccountFragmentBinding;
import com.example.cashbook.databinding.CategoryFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;
    private RecyclerView booksRecyclerView;
    private AccountsAdapter accountsAdapter;
    private AccountFragmentBinding accountFragmentBinding;
    private ArrayList<Account> accountsList;
    private long selectedAccountId;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        accountFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.account_fragment,container,false);
        accountFragmentBinding.setClickHandlers(new AccountFragmentClickHandlers());

        return accountFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        loadAccountsArrayList();
        // TODO: Use the ViewModel
    }

    private void loadAccountsArrayList() {
        mViewModel.getAllAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                accountsList = (ArrayList<Account>) accounts;
                loadRecyclerView();
            }
        });
    }

    private void loadRecyclerView() {

        booksRecyclerView = accountFragmentBinding.recyclerViewCategory;
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        booksRecyclerView.setHasFixedSize(true);

        accountsAdapter = new AccountsAdapter();
        booksRecyclerView.setAdapter(accountsAdapter);

        accountsAdapter.setAccounts(accountsList);

        accountsAdapter.setListener(new AccountsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Account account) {
                selectedAccountId = account.getAccountId();
                AddAndEditAccountFragment addAndEditFragment = AddAndEditAccountFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("accountid",account.getAccountId()+"");
                addAndEditFragment.setArguments(bundle);
                ((MainActivity)getActivity()).goToFragment(addAndEditFragment);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Account account = accountsList.get(viewHolder.getAdapterPosition());
                mViewModel.deleteAccount(account);
            }
        }).attachToRecyclerView(booksRecyclerView);


    }

    public class AccountFragmentClickHandlers {

        public void onAddEditClicked(View view) {
            ((MainActivity)getActivity()).goToFragment(AddAndEditAccountFragment.newInstance());
        }

    }
}