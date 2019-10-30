package com.example.cashbook.categoory.model;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

public class CategoryDiffCallback extends DiffUtil.Callback {
    ArrayList<Category> oldCategoryList;
    ArrayList<Category> newCategoryList;

    public CategoryDiffCallback(ArrayList<Category> oldCategoryList, ArrayList<Category> newCategoryList) {
        this.oldCategoryList = oldCategoryList;
        this.newCategoryList = newCategoryList;
    }

    @Override
    public int getOldListSize() {
        return oldCategoryList == null ? 0 : oldCategoryList.size();
    }

    @Override
    public int getNewListSize() {
        return newCategoryList == null ? 0 :newCategoryList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldBookPosition, int newBookPosition) {
        return oldCategoryList.get(oldBookPosition).getCategoryId()==newCategoryList.get(newBookPosition).getCategoryId();
    }

    @Override
    public boolean areContentsTheSame(int oldBookPosition, int newBookPosition) {
        return oldCategoryList.get(oldBookPosition).equals(newCategoryList.get(newBookPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldBookPosition, int newBookPosition) {
        return super.getChangePayload(oldBookPosition, newBookPosition);
    }
}
