package com.example.cashbook.categoory.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cashbook.R;
import com.example.cashbook.databinding.CategoryListItemBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private OnItemClickListener listener;
    private ArrayList<Category> categories=new ArrayList<>();

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        CategoryListItemBinding categoryListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.category_list_item,viewGroup,false);
        return new CategoryViewHolder(categoryListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
         Category category=categories.get(i);
         categoryViewHolder.categoryListItemBinding.setCategory(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(ArrayList<Category> newCategories) {
       // this.books = books;
       // notifyDataSetChanged();
       final DiffUtil.DiffResult result=DiffUtil.calculateDiff(new CategoryDiffCallback(categories,newCategories),false);
       categories=newCategories;
       result.dispatchUpdatesTo(CategoryAdapter.this);
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
    private CategoryListItemBinding categoryListItemBinding;

    public CategoryViewHolder(@NonNull CategoryListItemBinding bookListItemBinding) {
        super(bookListItemBinding.getRoot());
        this.categoryListItemBinding=bookListItemBinding;
        categoryListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int clickedPosition=getAdapterPosition();

              if(listener!=null && clickedPosition!=RecyclerView.NO_POSITION) {
                  listener.onItemClick(categories.get(clickedPosition));
              }
            }
        });

    }

}

public interface OnItemClickListener{
    void onItemClick(Category category);
}

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
