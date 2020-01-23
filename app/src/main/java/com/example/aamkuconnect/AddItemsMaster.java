package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class AddItemsMaster extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items_master);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case android.R.id.home:

                Intent i = new Intent(AddItemsMaster.this,Dashboard.class);
                startActivity(i);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
