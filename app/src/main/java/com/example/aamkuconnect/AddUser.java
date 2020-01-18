package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class AddUser extends AppCompatActivity {

    Spinner role,targetTimeline;
    TextInputEditText name,emp_id,email,mobile,whatsapp,dept,target;
    TextInputLayout targetWrapper,whatsappWrapper;
    AppCompatCheckBox check;
    Button save;
    ProgressDialog prg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("Add user");

        ab.setDisplayHomeAsUpEnabled(true);

        // Spinners
        role = findViewById(R.id.role);
        targetTimeline = findViewById(R.id.month);

       //Button
        save = findViewById(R.id.save);

        // Check box
        check = findViewById(R.id.check);

        // EditTexts
        name = findViewById(R.id.name);
        emp_id = findViewById(R.id.emp_id);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        whatsapp = findViewById(R.id.whatsapp);
        dept = findViewById(R.id.dept);
        target = findViewById(R.id.target);

        //TextINputlayputs
        targetWrapper = findViewById(R.id.targetWrapper);
        whatsappWrapper = findViewById(R.id.whatsappWrapper);

        List<String> list = new ArrayList<String>();
        list.add("Admin");
        list.add("SalesPerson");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(dataAdapter);

        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

               if(item.equals("Admin")){

                   targetWrapper.setVisibility(View.GONE);
                   targetTimeline.setVisibility(View.GONE);
               }
               else{

                   targetTimeline.setVisibility(View.VISIBLE);
                   targetWrapper.setVisibility(View.VISIBLE);
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    whatsappWrapper.setVisibility(View.GONE);
                }
                else{
                    whatsappWrapper.setVisibility(View.VISIBLE);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveUser();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case android.R.id.home:
                Intent i = new Intent(AddUser.this,Dashboard.class);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveUser(){

           prg = new ProgressDialog(AddUser.this);
           prg.setMessage("Adding user...");
           prg.setCancelable(false);
           prg.show();

           String str1 = role.getSelectedItem().toString();
           String str2 = name.getText().toString();
           String str3 = emp_id.getText().toString();
           String str4 = email.getText().toString();
           String str5 = mobile.getText().toString();
           String str6 = whatsapp.getText().toString();
           String str7 = dept.getText().toString();
           String str8 = target.getText().toString();

           //Checking role
           if(str1.equals("Admin")){

               if(str2.equals("")){

                   prg.dismiss();
                   name.setError("Enter name");
               }
               else if(str3.equals("")){

                   prg.dismiss();
                   emp_id.setError("Enter employee id");
               }
               else if(str4.equals("")){
                   prg.dismiss();
                   email.setError("Enter email");
               }
               else if(str5.equals("")){
                   prg.dismiss();
                   mobile.setError("Enter mobile number");
               }
               else if(!(str5.length() == 10)){
                   prg.dismiss();
                   mobile.setError("Invalid mobile number");
               }
               else if(!check.isChecked()){
                   prg.dismiss();
                   Toast.makeText(getApplicationContext(),"Enter whats app number",Toast.LENGTH_SHORT).show();
               }
               else if(dept.equals("")){

                   prg.dismiss();
                   Toast.makeText(getApplicationContext(),"Enter department",Toast.LENGTH_SHORT).show();
               }

               else{

                   OkHttpClient client = new OkHttpClient.Builder()
                           .connectTimeout(20, TimeUnit.SECONDS)
                           .readTimeout(20,TimeUnit.SECONDS)
                           .writeTimeout(20,TimeUnit.SECONDS)
                           .build();

                   RequestBody formBody = new FormBody.Builder()
                           .add("role",str1)
                           .add("name",str2)
                           .add("employee_id",str3)
                           .add("email",str4)
                           .add("mobile",str5)
                           .add("whatsapp",str6)
                           .add("department",str7)
                           .build();

               }

           }

    }
}
