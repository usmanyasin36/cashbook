package com.example.cashbook.categoory.model;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.cashbook.MainActivity;
import com.example.cashbook.R;
import com.example.cashbook.categoory.model.addandedit.AddAndEditFragment;
import com.example.cashbook.databinding.CategoryFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryViewModel mViewModel;
    private RecyclerView booksRecyclerView;
    private CategoryAdapter categoryAdapter;
    private CategoryFragmentBinding categoryFragmentBinding;
    private ArrayList<Category> categoriesList;
    private long selectedCategoryId;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        categoryFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.category_fragment,container,false);
        categoryFragmentBinding.setClickHandlers(new CategoryFragmentClickHandlers());

        return categoryFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        loadCategoryArrayList();
        // TODO: Use the ViewModel
    }

    private void loadCategoryArrayList() {
        mViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoriesList = (ArrayList<Category>) categories;
                loadRecyclerView();
            }
        });
    }

    private void loadRecyclerView() {

        booksRecyclerView = categoryFragmentBinding.recyclerViewCategory;
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        booksRecyclerView.setHasFixedSize(true);

        categoryAdapter = new CategoryAdapter();
        booksRecyclerView.setAdapter(categoryAdapter);

        categoryAdapter.setCategories(categoriesList);

        categoryAdapter.setListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {
                selectedCategoryId = category.getCategoryId();
                AddAndEditFragment addAndEditFragment = AddAndEditFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("categoryid",category.getCategoryId()+"");
                addAndEditFragment.setArguments(bundle);
                ((MainActivity)getActivity()).goToFragment(addAndEditFragment);
//                Intent intent = new Intent(getActivity() .class);
//                intent.putExtra(AddAndEditActivity.BOOK_ID, selectedBookId);
//                intent.putExtra(AddAndEditActivity.BOOK_NAME, book.getBookName());
//                intent.putExtra(AddAndEditActivity.UNIT_PRICE, book.getUnitPrice());
//                startActivityForResult(intent, EDIT_BOOK_REQUEST_CODE);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Category category = categoriesList.get(viewHolder.getAdapterPosition());
                mViewModel.deleteCategory(category);
            }
        }).attachToRecyclerView(booksRecyclerView);


    }

    public class CategoryFragmentClickHandlers {

        public void onAddEditClicked(View view) {
            ((MainActivity)getActivity()).goToFragment(AddAndEditFragment.newInstance());
        }

    }
}