package com.example.aamkuconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class ChooseLogin extends AppCompatActivity {

    CheckBox checkAdmin,checkSales,checkRetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login);

        checkAdmin = findViewById(R.id.checkAdmin);
        checkSales = findViewById(R.id.checkSales);
        checkRetail = findViewById(R.id.checkRetail);

        checkAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked){

                    Intent i = new Intent(ChooseLogin.this,MainActivity.class);
                   // i.putExtra("type","Salesperson");
                    startActivity(i);
                    finish();
                }

            }
        });

        checkSales.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked){

                    Intent i = new Intent(ChooseLogin.this,PhoneAuth.class);
                    i.putExtra("type","Salesperson");
                    startActivity(i);
                    finish();
                }

            }
        });

        checkRetail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked){

                    Intent i = new Intent(ChooseLogin.this,PhoneAuth.class);
                    i.putExtra("type","Retailer");
                    startActivity(i);
                    finish();
                }

            }
        });



    }
}
