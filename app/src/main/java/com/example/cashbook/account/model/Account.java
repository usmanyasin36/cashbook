package com.example.cashbook.account.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cashbook.BR;
import com.example.cashbook.categoory.model.Category;

import java.util.Objects;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "table_account")
public class Account extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private long accountId;

    private String name;
    private String email;
    private String type;
    private String startingBalance;
    private String balance;

    @Ignore
    public Account()
    {

    }

    public Account(long accountId, String name, String email, String type, String startingBalance, String balance) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.type = type;
        this.startingBalance = startingBalance;
        this.balance = balance;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Bindable
    public String getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(String startingBalance) {
        this.startingBalance = startingBalance;

    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId &&
                Objects.equals(name, account.name) &&
                Objects.equals(email, account.email) &&
                Objects.equals(type, account.type) &&
                Objects.equals(startingBalance, account.startingBalance) &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, name, email, type, startingBalance, balance);
    }

    @Override
    public String toString() {
        return this.name;
    }
}