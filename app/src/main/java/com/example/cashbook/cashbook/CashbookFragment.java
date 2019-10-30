package com.example.cashbook.cashbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashbook.MainActivity;
import com.example.cashbook.R;
import com.example.cashbook.account.AccountFragment;
import com.example.cashbook.account.addandeditaccount.AddAndEditAccountFragment;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.cashbook.addandeditcashbook.AddAndEditCashbookFragment;
import com.example.cashbook.cashbook.model.CashBook;
import com.example.cashbook.databinding.AccountFragmentBinding;
import com.example.cashbook.databinding.CashbookFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class CashbookFragment extends Fragment {

    private CashbookViewModel mViewModel;
    private RecyclerView booksRecyclerView;
    private CashbookAdapter cashbookAdapter;
    private CashbookFragmentBinding cashbookFragmentBinding;
    private ArrayList<CashBook> cashbooksList;
    private long selectedCashbookId;

    public static CashbookFragment newInstance() {
        return new CashbookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        cashbookFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.cashbook_fragment,container,false);
        cashbookFragmentBinding.setClickHandlers(new CashbookFragment.CashbookFragmentClickHandlers());

        return cashbookFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CashbookViewModel.class);
        loadAccountsArrayList();
        // TODO: Use the ViewModel
    }

    private void loadAccountsArrayList() {
        mViewModel.getAllCashbooks().observe(this, new Observer<List<CashBook>>() {
            @Override
            public void onChanged(List<CashBook> cashBooks) {
                cashbooksList = (ArrayList<CashBook>) cashBooks;
                loadRecyclerView();
            }
        });
    }

    private void loadRecyclerView() {

        booksRecyclerView = cashbookFragmentBinding.recyclerViewCategory;
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        booksRecyclerView.setHasFixedSize(true);

        cashbookAdapter = new CashbookAdapter();
        booksRecyclerView.setAdapter(cashbookAdapter);

        cashbookAdapter.setCashBooks(cashbooksList);

        cashbookAdapter.setListener(new CashbookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CashBook cashBook) {
                selectedCashbookId = cashBook.getAccountId();
                AddAndEditCashbookFragment addAndEditFragment = AddAndEditCashbookFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("cashbookid",cashBook.getAccountId()+"");
                addAndEditFragment.setArguments(bundle);
                ((MainActivity)getActivity()).goToFragment(addAndEditFragment);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                CashBook cashBook = cashbooksList.get(viewHolder.getAdapterPosition());
                mViewModel.deleteCashbook(cashBook);
            }
        }).attachToRecyclerView(booksRecyclerView);


    }

    public class CashbookFragmentClickHandlers {

        public void onAddEditClicked(View view) {
            ((MainActivity)getActivity()).goToFragment(AddAndEditCashbookFragment.newInstance());
        }

    }
}