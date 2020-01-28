package com.example.aamkuconnect.Salesperson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.aamkuconnect.Dashboard;
import com.example.aamkuconnect.R;

public class OrderManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setTitle("Order management");
        ab.setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case android.R.id.home:
                Intent i = new Intent(OrderManagement.this, Dashboard.class);
                startActivity(i);
                finish();

        }

        return super.onOptionsItemSelected(item);
    }
}
