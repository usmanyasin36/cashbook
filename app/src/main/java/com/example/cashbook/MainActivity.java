package com.example.cashbook;

import android.content.Context;
import android.os.Bundle;

import com.example.cashbook.account.AccountFragment;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.account.AccountsAdapter;
import com.example.cashbook.cashbook.CashbookFragment;
import com.example.cashbook.categoory.model.CategoryFragment;
import com.example.cashbook.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;
    ActivityMainBinding activityMainBinding;

    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        //setSupportActionBar(toolbar);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.initializeDatabase(getApplicationContext());

        activityMainBinding =  DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.setClickHandlers(new ActivityClickHandlers());

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if(fragmentManager.getBackStackEntryCount()!=0){
//           getSupportFragmentManager().popBackStack();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ActivityClickHandlers{
        public ActivityClickHandlers() {

        }
        public void categoriesClicked(View view)
        {
            goToFragment(CategoryFragment.newInstance());
        }

        public void accountsClicked(View view)
        {
            goToFragment(AccountFragment.newInstance());
        }

        public void cashbookClicked(View view)
        {
            goToFragment(CashbookFragment.newInstance());
        }
    }

    public void goToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.framelayout, fragment).addToBackStack("").commit();
//                ft.add(R.id.frame, fragment).addToBackStack("").commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}