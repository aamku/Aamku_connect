package com.example.aamkuconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CheckOtp extends AppCompatActivity {

    OtpTextView otp;
    ProgressDialog otpProgress;
    String message;
    String type;
    Intent intent;

    private static final String URL = "https://aamku-connect.herokuapp.com/checkOtp";
    private static final String KEY_PHONE = "keyphone";
    private static final String KEY_TYPE = "keytype";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_otp);

        otp = findViewById(R.id.otp);

        intent = getIntent();
      //  type = intent.getExtras().getString("type");
      //  Toast.makeText(getApplicationContext(),type,Toast.LENGTH_SHORT).show();


        otp.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(String otp) {

                verify(otp);
            }
        });
    }

    private void verify(String otp){

        otpProgress = new ProgressDialog(CheckOtp.this);
        otpProgress.setMessage("Verifying phone number...");
        otpProgress.setCancelable(false);
        otpProgress.show();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();

        RequestBody formBody = new FormBody.Builder()
                .add("otp",otp)
                .build();

        Request request = new Request.Builder().post(formBody).url(URL).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            otpProgress.dismiss();
                            String resp = response.body().string();


                          //  Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_SHORT).show();

                            if(resp.equals("Exists")){

                                if(intent != null){

                                    message = intent.getExtras().getString("phone");
                                    type = intent.getExtras().getString("type");

                                    SharedPreferences sharedPreferences = getSharedPreferences("simplifiedcodingsharedpref", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(KEY_PHONE, message);
                                    editor.putString(KEY_TYPE,type);
                                    editor.apply();

                                    Intent i = new Intent(CheckOtp.this,Dashboard.class);
                                    startActivity(i);
                                    finish();


                                }

                            }
                            else{

                                Toast.makeText(getApplicationContext(),"Otp is wrong",Toast.LENGTH_SHORT).show();
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

                        otpProgress.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }
}
