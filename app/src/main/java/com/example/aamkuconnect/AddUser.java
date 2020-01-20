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

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddUser extends AppCompatActivity {

    Spinner role,targetTimeline,productSales;
    TextInputEditText name,emp_id,email,mobile,whatsapp,dept,target;
    TextInputLayout targetWrapper,whatsappWrapper;
    AppCompatCheckBox check;
    Button save;
    ProgressDialog prg;

    private static final String URL = "https://aamku-connect.herokuapp.com/saveUser";

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
        productSales = findViewById(R.id.productSales);

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
        list.add("Select role");
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
                   productSales.setVisibility(View.GONE);
               }
               else{

                   targetTimeline.setVisibility(View.VISIBLE);
                   targetWrapper.setVisibility(View.VISIBLE);
                   productSales.setVisibility(View.VISIBLE);
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> timeline = new ArrayList<String>();
        timeline.add("Target timeline");
        timeline.add("1 month");
        timeline.add("2 month");
        timeline.add("3 month");
        timeline.add("4 month");
        timeline.add("5 month");
        timeline.add("6 month");
        timeline.add("7 month");
        timeline.add("8 month");
        timeline.add("9 month");
        timeline.add("10 month");
        timeline.add("11 month");
        timeline.add("12 month");

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,timeline);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        targetTimeline.setAdapter(ad);

        targetTimeline.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> listing = new ArrayList<String>();
        listing.add("Product to sales");
        listing.add("Books");
        listing.add("Copies");
        listing.add("Pen");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listing);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSales.setAdapter(adapter);

        productSales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
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
           String str8 = targetTimeline.getSelectedItem().toString();
           String str9 = target.getText().toString();
           String str10 = productSales.getSelectedItem().toString();

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
           /*    else if(!check.isChecked()){
                   prg.dismiss();
                   Toast.makeText(getApplicationContext(),"Enter whats app number",Toast.LENGTH_SHORT).show();
               }  */
               else if(str7.equals("")){

                   prg.dismiss();
                   dept.setError("Enter department");
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

                   Request request = new Request.Builder().post(formBody).url(URL).build();

                   client.newCall(request).enqueue(new Callback() {

                       @Override
                       public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   prg.dismiss();
                                   String resp = null;
                                   try {
                                       resp = response.body().string();
                                   }
                                   catch (IOException e) {
                                       e.printStackTrace();
                                   }
                                   Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_SHORT).show();
                               }
                           });

                       }

                       @Override
                       public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {

                                   prg.dismiss();
                                   Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT);
                               }
                           });
                       }
                   });

               }

           }

           else if(str1.equals("Select role")){

                   prg.dismiss();
                   Toast.makeText(getApplicationContext(),"Select role",Toast.LENGTH_SHORT).show();
               }
           else{


               String str11 = role.getSelectedItem().toString();
               String str12 = name.getText().toString();
               String str13 = emp_id.getText().toString();
               String str14 = email.getText().toString();
               String str15 = mobile.getText().toString();
               String str16 = whatsapp.getText().toString();
               String str17 = dept.getText().toString();
               String str18 = targetTimeline.getSelectedItem().toString();
               String str19 = target.getText().toString();
               String str20 = productSales.getSelectedItem().toString();

               if(str12.equals("")){

                   prg.dismiss();
                   name.setError("Enter name");
               }
               else if(str13.equals("")){

                   prg.dismiss();
                   emp_id.setError("Enter employee id");
               }
               else if(str14.equals("")){
                   prg.dismiss();
                   email.setError("Enter email");
               }
               else if(str15.equals("")){
                   prg.dismiss();
                   mobile.setError("Enter mobile number");
               }
               else if(!(str15.length() == 10)){
                   prg.dismiss();
                   mobile.setError("Invalid mobile number");
               }
           /*    else if(!check.isChecked()){
                   prg.dismiss();
                   Toast.makeText(getApplicationContext(),"Enter whats app number",Toast.LENGTH_SHORT).show();
               }  */
               else if(str17.equals("")){

                   prg.dismiss();
                   dept.setError("Enter department");
               }
               else if(str18.equals("Target timeline")){

                   prg.dismiss();
                   Toast.makeText(getApplicationContext(),"Select timeline",Toast.LENGTH_SHORT).show();
               }
               else if(str19.equals("")){

                   prg.dismiss();
                   target.setError("Enter target");
               }
               else if(str20.equals("Product to sales")){
                   prg.dismiss();
                   Toast.makeText(getApplicationContext(),"Select product",Toast.LENGTH_SHORT).show();
               }
               else{

                   OkHttpClient myClient = new OkHttpClient.Builder()
                           .connectTimeout(20, TimeUnit.SECONDS)
                           .readTimeout(20,TimeUnit.SECONDS)
                           .writeTimeout(20,TimeUnit.SECONDS)
                           .build();


                   RequestBody formBody = new FormBody.Builder()
                           .add("role",str11)
                           .add("name",str12)
                           .add("employee_id",str13)
                           .add("email",str14)
                           .add("mobile",str15)
                           .add("whatsapp",str16)
                           .add("department",str17)
                           .add("target_timeline",str18)
                           .add("target",str19)
                           .add("product_sales",str20)
                           .build();

                   Request request = new Request.Builder().post(formBody).url(URL).build();

                   myClient.newCall(request).enqueue(new Callback() {

                       @Override
                       public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   prg.dismiss();
                                   String resp = null;
                                   try {
                                       resp = response.body().string();
                                   }
                                   catch (IOException e) {
                                       e.printStackTrace();
                                   }
                                   Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_SHORT).show();
                               }
                           });

                       }

                       @Override
                       public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {

                                   prg.dismiss();
                                   Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT);
                               }
                           });
                       }
                   });


               }

           }

    }
}
