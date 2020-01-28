package com.example.aamkuconnect.Salesperson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.aamkuconnect.Adapters.OrderManagTabAdapter;
import com.example.aamkuconnect.Dashboard;
import com.example.aamkuconnect.NewOrderFragment;
import com.example.aamkuconnect.OrderHistoryFragment;
import com.example.aamkuconnect.R;
import com.google.android.material.tabs.TabLayout;

public class OrderManagement extends AppCompatActivity {

    private TabLayout my_tab;
    private ViewPager pager;
    private OrderManagTabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setTitle("Order management");
        ab.setElevation(0);
        ab.setDisplayHomeAsUpEnabled(true);

        my_tab = findViewById(R.id.my_tab);
        pager = findViewById(R.id.pager);

        adapter = new OrderManagTabAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewOrderFragment(), "New Order");
        adapter.addFragment(new OrderHistoryFragment(), "ORDER HISTORY");

        my_tab.setupWithViewPager(pager);

        pager.setAdapter(adapter);

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
