package com.example.cashbook.categoory.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cashbook.BR;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "table_category")
public class Category extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private long categoryId;

    private String name;
    private Date createdDate;
    private Date editedDate;
    private long cashbookId;

    @Ignore
    public Category()
    {

    }

    public Category(long categoryId, String name, Date createdDate, Date editedDate, long cashbookId) {
        this.categoryId = categoryId;
        this.name = name;
        this.createdDate = createdDate;
        this.editedDate = editedDate;
        this.cashbookId = cashbookId;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
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
    public long getCashbookId() {
        return cashbookId;
    }

    public void setCashbookId(long cashbookId) {
        this.cashbookId = cashbookId;
        notifyPropertyChanged(BR.cashbookId);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId &&
                cashbookId == category.cashbookId &&
                Objects.equals(name, category.name) &&
                Objects.equals(createdDate, category.createdDate) &&
                Objects.equals(editedDate, category.editedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, name, createdDate, editedDate, cashbookId);
    }

    @Override
    public String toString() {
        return this.name;
    }
}