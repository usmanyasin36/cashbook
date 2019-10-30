package com.example.cashbook.cashbook.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cashbook.BR;

import java.util.Date;

@Entity(tableName = "table_cashbook")
public class CashBook extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private long cashbookId;

    private String memo;
    private Date createdDate;
    private Date editedDate;
    private String debitAmount;
    private String creditAmount;
    private long accountId;
    private long categoryId;
    private long inventoryId;


    @Ignore
    public CashBook()
    {

    }

    public CashBook(long cashbookId, String memo, Date createdDate, Date editedDate, String debitAmount, String creditAmount, long accountId, long categoryId, long inventoryId) {
        this.cashbookId = cashbookId;
        this.memo = memo;
        this.createdDate = createdDate;
        this.editedDate = editedDate;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.inventoryId = inventoryId;
    }

    @Bindable
    public long getCashbookId() {
        return cashbookId;
    }

    public void setCashbookId(long cashbookId) {
        this.cashbookId = cashbookId;
        notifyPropertyChanged(BR.cashbookId);
    }

    @Bindable
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
        notifyPropertyChanged(BR.memo);
    }

    @Bindable
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        notifyPropertyChanged(BR.createdDate);
    }

    @Bindable
    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
        notifyPropertyChanged(BR.editedDate);
    }

    @Bindable
    public String getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
        notifyPropertyChanged(BR.debitAmount);
    }

    @Bindable
    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
        notifyPropertyChanged(BR.creditAmount);
    }

    @Bindable
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
        notifyPropertyChanged(BR.accountId);
    }

    @Bindable
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);
    }

    @Bindable
    public long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
        notifyPropertyChanged(BR.inventoryId);
    }
}