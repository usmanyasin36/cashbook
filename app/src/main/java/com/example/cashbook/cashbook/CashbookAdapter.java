package com.example.cashbook.cashbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashbook.R;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.cashbook.model.CashBook;
import com.example.cashbook.databinding.AccountListItemBinding;
import com.example.cashbook.databinding.CashbookListItemBinding;

import java.util.ArrayList;

public class CashbookAdapter extends RecyclerView.Adapter<CashbookAdapter.CashbookViewHolder>{
    private CashbookAdapter.OnItemClickListener listener;
    private ArrayList<CashBook> cashBooks=new ArrayList<>();

    @NonNull
    @Override
    public CashbookAdapter.CashbookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        CashbookListItemBinding cashbookListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.cashbook_list_item,viewGroup,false);
        return new CashbookAdapter.CashbookViewHolder(cashbookListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CashbookAdapter.CashbookViewHolder cashbookViewHolder, int i) {
        CashBook cashBook=cashBooks.get(i);
        cashbookViewHolder.cashbookListItemBinding.setCashbook(cashBook);
    }

    @Override
    public int getItemCount() {
        return cashBooks.size();
    }

    public void setCashBooks(ArrayList<CashBook> newCashbooks) {
        // this.books = books;
        // notifyDataSetChanged();
        final DiffUtil.DiffResult result=DiffUtil.calculateDiff(new CashbookDiffCallback(cashBooks,newCashbooks),false);
        cashBooks = newCashbooks;
        result.dispatchUpdatesTo(CashbookAdapter.this);
    }

    class CashbookViewHolder extends RecyclerView.ViewHolder{
        private CashbookListItemBinding cashbookListItemBinding;

        public CashbookViewHolder(@NonNull CashbookListItemBinding cashbookListItemBinding) {
            super(cashbookListItemBinding.getRoot());
            this.cashbookListItemBinding = cashbookListItemBinding;
            cashbookListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition=getAdapterPosition();

                    if(listener!=null && clickedPosition!=RecyclerView.NO_POSITION) {
                        listener.onItemClick(cashBooks.get(clickedPosition));
                    }
                }
            });

        }

    }

    public interface OnItemClickListener{
        void onItemClick(CashBook cashBook);
    }

    public void setListener(CashbookAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}