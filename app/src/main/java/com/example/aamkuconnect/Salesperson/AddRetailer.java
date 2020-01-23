package com.example.aamkuconnect.Salesperson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.aamkuconnect.Dashboard;
import com.example.aamkuconnect.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddRetailer extends AppCompatActivity {

    Button saveRetailer;
    TextInputEditText retailer_name,retailer_email,retailer_phone,retailer_whatsapp,retailer_gst,remarks,services;
    TextInputLayout whatsappRetailWrapper;
    AppCompatCheckBox retailCheck;

    SharedPreferences sp;
    String phone_id;

    ProgressDialog prog1;

    private static final String URL = "https://aamku-connect.herokuapp.com/saveRetailer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_retailer);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setTitle("Add retailer");
        ab.setDisplayHomeAsUpEnabled(true);

        //Button
        saveRetailer = findViewById(R.id.saveRetailer);

        //Checkbox
        retailCheck = findViewById(R.id.retailCheck);

        //TextInput
        whatsappRetailWrapper = findViewById(R.id.whatsappRetailWrapper);

        //Retailer
        retailer_name = findViewById(R.id.retailer_name);
        retailer_email = findViewById(R.id.retailer_email);
        retailer_phone = findViewById(R.id.retailer_phone);
        retailer_gst = findViewById(R.id.retailer_gst);
        retailer_whatsapp = findViewById(R.id.retailer_whatsapp);
        remarks = findViewById(R.id.remarks);
        services = findViewById(R.id.services);

        retailCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    whatsappRetailWrapper.setVisibility(View.GONE);
                }
                else{
                    whatsappRetailWrapper.setVisibility(View.VISIBLE);
                }
            }
        });

        saveRetailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prog1 = new ProgressDialog(AddRetailer.this);
                prog1.setMessage("Adding retailer...");
                prog1.setCancelable(false);
                prog1.show();

                if(retailer_name.getText().toString().equals("")){

                    prog1.dismiss();
                    retailer_name.setError("Enter name");
                }
                else if(retailer_email.getText().toString().equals("")){

                    prog1.dismiss();
                    retailer_email.setError("Enter email");

                }
                else if(retailer_phone.getText().toString().equals("")){

                    prog1.dismiss();
                    retailer_phone.setError("Enter phone");
                }
                else if(!(retailer_phone.getText().toString().length() == 10)){

                    prog1.dismiss();
                    retailer_phone.setError("Invalid phone number");
                }
            /*    else if(retailer_whatsapp.equals("")){

                    prog1.dismiss();
                    retailer_whatsapp.setError("Enter phone");
                }
                else if(!(retailer_whatsapp.length() == 10)){

                    prog1.dismiss();
                    retailer_whatsapp.setError("Invalid phone number");
                }  */
                else if(retailer_gst.getText().toString().equals("")){

                    prog1.dismiss();
                    retailer_gst.setError("Enter GST number");
                }
                else if(!(retailer_gst.getText().toString().length() == 15)){

                    prog1.dismiss();
                    retailer_gst.setError("Invalid GST number");
                }
                else if(services.getText().toString().equals("")){

                    prog1.dismiss();
                    services.setError("Enter service");
                }
                else{

                    saveRetailer();
                }

            }
        });

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

    private void saveRetailer(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();

        String time = formatter.format(date);

        sp = getSharedPreferences("simplifiedcodingsharedpref", Context.MODE_PRIVATE);
         phone_id = sp.getString("keyphone","");

         OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();

        RequestBody formBody = new FormBody.Builder()
                .add("added_by",phone_id)
                .add("name",retailer_name.getText().toString())
                .add("email",retailer_email.getText().toString())
                .add("mobile",retailer_phone.getText().toString())
                .add("whatsapp",retailer_whatsapp.getText().toString())
                .add("gst",retailer_gst.getText().toString())
                .add("services",services.getText().toString())
                .add("status","pending")
                .add("time",formatter.format(date).toString())
                .build();

        Request request = new Request.Builder().post(formBody).url(URL).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            String resp = response.body().string();
                            prog1.dismiss();

                            if(resp.equals("Data added")){

                                retailer_name.setText("");
                                retailer_email.setText("");
                                retailer_phone.setText("");
                                retailer_whatsapp.setText("");
                                retailer_gst.setText("");
                                services.setText("");
                                Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_SHORT).show();
                            }

                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        prog1.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });


    }
}
