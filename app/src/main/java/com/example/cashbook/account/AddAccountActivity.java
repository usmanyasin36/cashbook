package com.example.cashbook.account;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.example.cashbook.R;
import com.example.cashbook.account.model.Account;
import com.example.cashbook.databinding.ActivityAddAccountBinding;

public class AddAccountActivity extends AppCompatActivity {

    ActivityAddAccountBinding activityAddAccountBinding;
    Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityAddAccountBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_account);
        activityAddAccountBinding.setClickhandlers(new ActivityClickHandlers(this));

        account = new Account();
        activityAddAccountBinding.setAccount(account);
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
        Context context;
        public ActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void fabClicked(View view)
        {
            getIntent().putExtra("name",account.getName());
            setResult(RESULT_OK);
            Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show();
        }
    }
}