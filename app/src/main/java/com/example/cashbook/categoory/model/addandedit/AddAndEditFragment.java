package com.example.cashbook.categoory.model.addandedit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashbook.InterfaceCashbookRepository;
import com.example.cashbook.R;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.cashbook.model.CashBook;
import com.example.cashbook.categoory.model.Category;
import com.example.cashbook.categoory.model.CategoryAdapter;
import com.example.cashbook.categoory.model.CategoryFragment;
import com.example.cashbook.categoory.model.CategoryViewModel;
import com.example.cashbook.databinding.AddAndEditFragmentBinding;
import com.example.cashbook.databinding.CategoryFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static android.app.Activity.RESULT_OK;

public class AddAndEditFragment extends Fragment{

    private AddAndEditViewModel mViewModel;
    private RecyclerView booksRecyclerView;
    private CategoryAdapter categoryAdapter;
    private AddAndEditFragmentBinding addAndEditFragmentBinding;
    private ArrayList<Category> categoriesList;
    private long selectedCategoryId;
    private AddAndEditFragmentClickHandlers addAndEditFragmentClickHandlers;
    private Category category;

    public static AddAndEditFragment newInstance() {
        return new AddAndEditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        addAndEditFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.add_and_edit_fragment,container,false);
        addAndEditFragmentBinding.setClickHandlers(new AddAndEditFragmentClickHandlers(getContext()));

        mViewModel = ViewModelProviders.of(this).get(AddAndEditViewModel.class);

        category = new Category();
        addAndEditFragmentBinding.setCategory(category);
        Bundle bundle = getArguments();
        if(bundle!=null){
            long categoryId = Long.parseLong(bundle.getString("categoryid"));
            mViewModel.getCategoryById(categoryId, new InterfaceCashbookRepository() {
                @Override
                public void onCategoryFetched(Category tempCategory) {
                    category = tempCategory;
                    addAndEditFragmentBinding.setCategory(category);
                }

                @Override
                public void onAccountFetched(Account category) {

                }

                @Override
                public void onCashbookFetched(CashBook cashBook) {

                }
            });
        }

        return addAndEditFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // TODO: Use the ViewModel
    }

    public class AddAndEditFragmentClickHandlers{
        Context context;

        public AddAndEditFragmentClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view){
            if(category.getName()==null){
                Toast.makeText(context,"Name field cannot be empty",Toast.LENGTH_LONG).show();
            }else{
                if(category.getCategoryId()==0){
                    mViewModel.addNewCategory(category);
                }else{
                    mViewModel.updateCategory(category);
                }

                try {
                    getFragmentManager().popBackStack();
                }catch (Exception e){
                    int h=1;
                }
            }
        }
    }
}
