package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SalesDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_dashboard);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("Salesperson dashboard");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.salesdash, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.action_sales_logout:

                SharedPrefManager.getInstance(SalesDashboard.this).logout();
                Intent i = new Intent(SalesDashboard.this,ChooseLogin.class);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
