package com.example.cashbook.account;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashbook.R;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.categoory.model.Category;
import com.example.cashbook.categoory.model.CategoryAdapter;
import com.example.cashbook.categoory.model.CategoryDiffCallback;
import com.example.cashbook.databinding.AccountListItemBinding;
import com.example.cashbook.databinding.CategoryListItemBinding;

import java.util.ArrayList;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountViewHolder>{
    private AccountsAdapter.OnItemClickListener listener;
    private ArrayList<Account> accounts=new ArrayList<>();

    @NonNull
    @Override
    public AccountsAdapter.AccountViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        AccountListItemBinding accountListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.account_list_item,viewGroup,false);
        return new AccountsAdapter.AccountViewHolder(accountListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsAdapter.AccountViewHolder accountViewHolder, int i) {
        Account account=accounts.get(i);
        accountViewHolder.accountListItemBinding.setAccount(account);
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public void setAccounts(ArrayList<Account> newAccounts) {
        // this.books = books;
        // notifyDataSetChanged();
        final DiffUtil.DiffResult result=DiffUtil.calculateDiff(new AccountDiffCallback(accounts,newAccounts),false);
        accounts=newAccounts;
        result.dispatchUpdatesTo(AccountsAdapter.this);
    }

    class AccountViewHolder extends RecyclerView.ViewHolder{
        private AccountListItemBinding accountListItemBinding;

        public AccountViewHolder(@NonNull AccountListItemBinding accountListItemBinding) {
            super(accountListItemBinding.getRoot());
            this.accountListItemBinding=accountListItemBinding;
            accountListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition=getAdapterPosition();

                    if(listener!=null && clickedPosition!=RecyclerView.NO_POSITION) {
                        listener.onItemClick(accounts.get(clickedPosition));
                    }
                }
            });

        }

    }

    public interface OnItemClickListener{
        void onItemClick(Account account);
    }

    public void setListener(AccountsAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
