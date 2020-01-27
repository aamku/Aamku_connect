package com.example.aamkuconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aamkuconnect.Retailers.Register;

public class RetailerLogin extends AppCompatActivity {

    Button reg_retailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);

        reg_retailer = findViewById(R.id.reg_retailer);

        reg_retailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RetailerLogin.this, Register.class);
                startActivity(i);
                finish();
            }
        });
    }
}
