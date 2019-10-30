package com.example.cashbook.cashbook;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.cashbook.account.model.Account;
import com.example.cashbook.cashbook.model.CashBook;

import java.util.ArrayList;

public class CashbookDiffCallback extends DiffUtil.Callback {
    ArrayList<CashBook> oldCashbookList;
    ArrayList<CashBook> newCashbookList;

    public CashbookDiffCallback(ArrayList<CashBook> oldCashbookList, ArrayList<CashBook> newCashbookList) {
        this.oldCashbookList = oldCashbookList;
        this.newCashbookList = newCashbookList;
    }

    @Override
    public int getOldListSize() {
        return oldCashbookList == null ? 0 : oldCashbookList.size();
    }

    @Override
    public int getNewListSize() {
        return newCashbookList == null ? 0 :newCashbookList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldBookPosition, int newBookPosition) {
        return oldCashbookList.get(oldBookPosition).getAccountId()==newCashbookList.get(newBookPosition).getAccountId();
    }

    @Override
    public boolean areContentsTheSame(int oldBookPosition, int newBookPosition) {
        return oldCashbookList.get(oldBookPosition).equals(newCashbookList.get(newBookPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldBookPosition, int newBookPosition) {
        return super.getChangePayload(oldBookPosition, newBookPosition);
    }
}