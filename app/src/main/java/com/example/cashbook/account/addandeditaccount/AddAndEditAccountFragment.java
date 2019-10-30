package com.example.cashbook.account.addandeditaccount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.cashbook.InterfaceCashbookRepository;
import com.example.cashbook.R;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.cashbook.model.CashBook;
import com.example.cashbook.categoory.model.Category;
import com.example.cashbook.databinding.AddAndEditAccountFragmentBinding;

public class AddAndEditAccountFragment extends Fragment{

    private AddAndEditAccountViewModel mViewModel;
    private AddAndEditAccountFragmentBinding addAndEditFragmentBinding;
    private AddAndEditAccountFragmentClickHandlers addAndEditFragmentClickHandlers;
    private Account account;

    public static AddAndEditAccountFragment newInstance() {
        return new AddAndEditAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        addAndEditFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.add_and_edit_account_fragment,container,false);
        addAndEditFragmentBinding.setClickHandlers(new AddAndEditAccountFragmentClickHandlers(getContext()));

        mViewModel = ViewModelProviders.of(this).get(AddAndEditAccountViewModel.class);

        account = new Account();
        addAndEditFragmentBinding.setAccount(account);
        Bundle bundle = getArguments();
        if(bundle!=null){
            long accountid = Long.parseLong(bundle.getString("accountid"));
            mViewModel.getAccountById(accountid, new InterfaceCashbookRepository() {
                @Override
                public void onCategoryFetched(Category tempCategory) {

                }

                @Override
                public void onAccountFetched(Account tempAccount) {
                    account = tempAccount;
                    addAndEditFragmentBinding.setAccount(account);
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

    public class AddAndEditAccountFragmentClickHandlers{
        Context context;

        public AddAndEditAccountFragmentClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view){
            if(account.getName()==null){
                Toast.makeText(context,"Name field cannot be empty",Toast.LENGTH_LONG).show();
            }else{
                if(account.getAccountId()==0){
                    mViewModel.addNewAccount(account);
                }else{
                    mViewModel.updateAccount(account);
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
