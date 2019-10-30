package com.example.cashbook.account;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.cashbook.account.model.Account;
import com.example.cashbook.categoory.model.Category;

import java.util.ArrayList;

public class AccountDiffCallback extends DiffUtil.Callback {
    ArrayList<Account> oldAccountList;
    ArrayList<Account> newAccountList;

    public AccountDiffCallback(ArrayList<Account> oldAccountList, ArrayList<Account> newAccountList) {
        this.oldAccountList = oldAccountList;
        this.newAccountList = newAccountList;
    }

    @Override
    public int getOldListSize() {
        return oldAccountList == null ? 0 : oldAccountList.size();
    }

    @Override
    public int getNewListSize() {
        return newAccountList == null ? 0 :newAccountList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldBookPosition, int newBookPosition) {
        return oldAccountList.get(oldBookPosition).getAccountId()==newAccountList.get(newBookPosition).getAccountId();
    }

    @Override
    public boolean areContentsTheSame(int oldBookPosition, int newBookPosition) {
        return oldAccountList.get(oldBookPosition).equals(newAccountList.get(newBookPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldBookPosition, int newBookPosition) {
        return super.getChangePayload(oldBookPosition, newBookPosition);
    }
}
