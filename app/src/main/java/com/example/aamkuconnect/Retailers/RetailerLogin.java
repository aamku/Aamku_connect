package com.example.aamkuconnect.Retailers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aamkuconnect.CheckOtp;
import com.example.aamkuconnect.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RetailerLogin extends AppCompatActivity {

    Button reg_retailer,login_otp;
    EditText login_retailer_pin;
    ProgressDialog prog4;
    String role;
    String phone;

    private static final String URL = "https://aamku-connect.herokuapp.com/retailerLoginOtp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);

        reg_retailer = findViewById(R.id.reg_retailer);
        login_otp = findViewById(R.id.login_otp);
        login_retailer_pin = findViewById(R.id.login_retailer_pin);

        Intent intent = getIntent();
        if(intent != null){

            role = intent.getExtras().getString("type");
            //   Toast.makeText(getApplicationContext(),role,Toast.LENGTH_SHORT).show();
        }

        phone = login_retailer_pin.getText().toString();

        reg_retailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RetailerLogin.this, Register.class);
                startActivity(i);
                finish();
            }
        });

        login_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prog4 = new ProgressDialog(RetailerLogin.this);
                prog4.setMessage("Sending otp... ");
                prog4.setCancelable(false);
                prog4.show();

                if(login_retailer_pin.getText().toString().equals("")){

                    prog4.dismiss();
                    login_retailer_pin.setError("Enter phone number");
                }
                else if(!(login_retailer_pin.length() == 10)){

                    prog4.dismiss();
                    login_retailer_pin.setError("Invalid phone number");

                }
                else{

                    OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20,TimeUnit.SECONDS)
                            .writeTimeout(20,TimeUnit.SECONDS)
                            .build();

                    RequestBody formBody = new FormBody.Builder()
                            .add("phone",login_retailer_pin.getText().toString())
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

                                         if(resp.equals("Otp send successfully")){

                                             prog4.dismiss();
                                             Intent i = new Intent(RetailerLogin.this, CheckOtp.class);
                                             i.putExtra("phone",phone);
                                             i.putExtra("type",role);
                                             startActivity(i);
                                             finish();
                                         }
                                         else{

                                             prog4.dismiss();
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

                                    prog4.dismiss();
                                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    });
                }

            }
        });
    }
}
