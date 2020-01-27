package com.example.aamkuconnect.Retailers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.aamkuconnect.ChooseLogin;
import com.example.aamkuconnect.R;
import com.example.aamkuconnect.Salesperson.AddRetailer;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity {

    Button registerRetailer;
    AppCompatCheckBox regCheck;
    TextInputEditText reg_retailer_name,reg_retailer_email,reg_retailer_phone,reg_retailer_whatsapp,reg_retailer_gst,reg_remarks,reg_services,
            reg_retailer_state,reg_retailer_city,reg_retailer_address,reg_retailer_pin;

    TextInputLayout whatsappRetailWrapper;

    ProgressDialog prog3;

    private static final String URL = "https://aamku-connect.herokuapp.com/saveRetailer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //BUtton
        registerRetailer = findViewById(R.id.registerRetailer);

        //CheckBox
        regCheck = findViewById(R.id.regCheck);

        //TextINputLayout
        whatsappRetailWrapper = findViewById(R.id.whatsappRetailWrapper);

        //EditText
        reg_retailer_name = findViewById(R.id.reg_retailer_name);
        reg_retailer_email = findViewById(R.id.reg_retailer_email);
        reg_retailer_phone = findViewById(R.id.reg_retailer_phone);
        reg_retailer_gst = findViewById(R.id.reg_retailer_gst);
        reg_retailer_whatsapp = findViewById(R.id.reg_retailer_whatsapp);
        reg_retailer_state = findViewById(R.id.reg_retailer_state);
        reg_retailer_city = findViewById(R.id.reg_retailer_city);
        reg_retailer_address = findViewById(R.id.reg_retailer_address);
        reg_retailer_pin = findViewById(R.id.reg_retailer_pin);

        reg_remarks = findViewById(R.id.remarks);
        reg_services = findViewById(R.id.reg_services);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setTitle("Register");
        ab.setDisplayHomeAsUpEnabled(true);

        regCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        registerRetailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prog3 = new ProgressDialog(Register.this);
                prog3.setMessage("Adding retailer...");
                prog3.setCancelable(false);
                prog3.show();

                if(reg_retailer_name.getText().toString().equals("")){

                    prog3.dismiss();
                    reg_retailer_name.setError("Enter name");
                }
                else if(reg_retailer_email.getText().toString().equals("")){

                    prog3.dismiss();
                    reg_retailer_email.setError("Enter email");

                }
                else if(reg_retailer_phone.getText().toString().equals("")){

                    prog3.dismiss();
                    reg_retailer_phone.setError("Enter phone");
                }
                else if(!(reg_retailer_phone.getText().toString().length() == 10)){

                    prog3.dismiss();
                    reg_retailer_phone.setError("Invalid phone number");
                }
            /*    else if(retailer_whatsapp.equals("")){

                    prog1.dismiss();
                    retailer_whatsapp.setError("Enter phone");
                }
                else if(!(retailer_whatsapp.length() == 10)){

                    prog1.dismiss();
                    retailer_whatsapp.setError("Invalid phone number");
                }  */
                else if(reg_retailer_gst.getText().toString().equals("")){

                    prog3.dismiss();
                    reg_retailer_gst.setError("Enter GST number");
                }
                else if(!(reg_retailer_gst.getText().toString().length() == 15)){

                    prog3.dismiss();
                    reg_retailer_gst.setError("Invalid GST number");
                }
                else if(reg_retailer_state.getText().toString().equals("")){
                    reg_retailer_state.setError("Enter state");
                    prog3.dismiss();
                }
                else if(reg_retailer_city.getText().toString().equals("")){
                    reg_retailer_city.setError("Enter city");
                    prog3.dismiss();
                }
                else if(reg_retailer_address.getText().toString().equals("")){
                    reg_retailer_address.setError("Enter address");
                    prog3.dismiss();
                }
                else if(reg_retailer_pin.getText().toString().equals("")){
                    reg_retailer_pin.setError("Enter pin code");
                    prog3.dismiss();
                }
                else if(!(reg_retailer_pin.getText().toString().length() == 6)){
                    reg_retailer_pin.setError("Enter valid pin code");
                    prog3.dismiss();
                }
                else if(reg_services.getText().toString().equals("")){

                    prog3.dismiss();
                    reg_services.setError("Enter service");
                }
                 else{

                    registerRetailer();
                }


            }
        });

    }

    private void registerRetailer(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();


        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();

        RequestBody formBody = new FormBody.Builder()
                .add("added_by","own")
                .add("name",reg_retailer_name.getText().toString())
                .add("email",reg_retailer_email.getText().toString())
                .add("mobile",reg_retailer_email.getText().toString())
                .add("whatsapp",reg_retailer_whatsapp.getText().toString())
                .add("gst",reg_retailer_gst.getText().toString())
                .add("state",reg_retailer_state.getText().toString())
                .add("city",reg_retailer_city.getText().toString())
                .add("address",reg_retailer_address.getText().toString())
                .add("pin",reg_retailer_pin.getText().toString())
                .add("services",reg_services.getText().toString())
                .add("status","pending")
                .add("date",formatter.format(date).toString())
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
                            prog3.dismiss();

                            if(resp.equals("Data added")){

                                reg_retailer_name.setText("");
                                reg_retailer_email.setText("");
                                reg_retailer_phone.setText("");
                                reg_retailer_whatsapp.setText("");
                                reg_retailer_gst.setText("");
                                reg_retailer_state.setText("");
                                reg_retailer_city.setText("");
                                reg_retailer_address.setText("");
                                reg_retailer_pin.setText("");
                                reg_services.setText("");
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

                        prog3.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case android.R.id.home:

                Intent i = new Intent(Register.this, ChooseLogin.class);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
