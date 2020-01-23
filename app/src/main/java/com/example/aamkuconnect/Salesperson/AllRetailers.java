package com.example.aamkuconnect.Salesperson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aamkuconnect.Adapters.AllRetailerSalesAdapter;
import com.example.aamkuconnect.R;

public class AllRetailers extends AppCompatActivity {

    AllRetailerSalesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_retailers);
    }
}
