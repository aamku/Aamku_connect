package com.example.aamkuconnect;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

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

public class PhoneAuth extends AppCompatActivity {

    TextInputEditText phoneAuth;
    Button sendOtp;
    ProgressDialog prgDialog;
    String phone;
    String role;
   // String send;

    private static final String URL = "https://aamku-connect.herokuapp.com/sendOtp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        phoneAuth = findViewById(R.id.phoneAuth);
        sendOtp = findViewById(R.id.sendOtp);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.hide();

      //  Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        if(intent != null){

            role = intent.getExtras().getString("type");
         //   Toast.makeText(getApplicationContext(),role,Toast.LENGTH_SHORT).show();
        }

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prgDialog = new ProgressDialog(PhoneAuth.this);
                prgDialog.setMessage("Getting otp...");
                prgDialog.setCancelable(false);
                prgDialog.show();

                 phone = phoneAuth.getText().toString();

                if(phone.equals("")){

                    prgDialog.dismiss();
                    phoneAuth.setError("Enter phone number");
                }
                else if(!(phone.length() == 12)){

                    prgDialog.dismiss();
                    phoneAuth.setError("Invalid phone number");
                }
                else{

                    sendOtp(phone);
                }

            }
        });

    }

    private void sendOtp(final String phone){

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();

        RequestBody formBody = new FormBody.Builder()
                .add("phone",phone)
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

                                prgDialog.dismiss();
                                Intent i = new Intent(PhoneAuth.this,CheckOtp.class);
                                i.putExtra("phone",phone);
                                i.putExtra("type",role);
                                startActivity(i);
                                finish();
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

                        prgDialog.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}
