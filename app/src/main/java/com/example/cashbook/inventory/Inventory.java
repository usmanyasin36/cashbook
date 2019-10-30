package com.example.cashbook.inventory;

import androidx.databinding.BaseObservable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "table_inventory")
public class Inventory extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private long inventoryId;

    private String name;
    private Date createdDate;
    private Date editedDate;
    private long categoryId;
    private String purchaseAmount;
    private long purchaseQuantity;
    private long remainingQuantity;
    private String purchaseDate;


    @Ignore
    public Inventory()
    {

    }

    public Inventory(long inventoryId, String name, Date createdDate, Date editedDate, long categoryId, String purchaseAmount, long purchaseQuantity, long remainingQuantity, String purchaseDate) {
        this.inventoryId = inventoryId;
        this.name = name;
        this.createdDate = createdDate;
        this.editedDate = editedDate;
        this.categoryId = categoryId;
        this.purchaseAmount = purchaseAmount;
        this.purchaseQuantity = purchaseQuantity;
        this.remainingQuantity = remainingQuantity;
        this.purchaseDate = purchaseDate;
    }

    public long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public long getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(long purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public long getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(long remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }
}