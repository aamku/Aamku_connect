package com.example.aamkuconnect.Salesperson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.aamkuconnect.Dashboard;
import com.example.aamkuconnect.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddRetailer extends AppCompatActivity {

    Button saveRetailer;
    TextInputEditText retailer_name,retailer_email,retailer_phone,retailer_gst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_retailer);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setTitle("Add retailer");
        ab.setDisplayHomeAsUpEnabled(true);

        saveRetailer = findViewById(R.id.saveRetailer);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case android.R.id.home:
                Intent i = new Intent(AddRetailer.this, Dashboard.class);
                startActivity(i);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
