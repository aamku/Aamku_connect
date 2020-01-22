package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    TextInputEditText username,pwd;
    Button login;
    private FirebaseAuth fAuth;
    ProgressDialog prg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.hide();

        fAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });

    }

    private void login(){

        prg = new ProgressDialog(MainActivity.this);
        prg.setMessage("Logging in");
        prg.setCancelable(false);
        prg.show();

        String user = username.getText().toString();
        String pas = pwd.getText().toString();

        StringBuilder mail = new StringBuilder(user);
        mail.append("@gmail.com");
        String email = mail.toString().toLowerCase();

        if(user.equals("")){

            username.setError("Enter username");
            prg.dismiss();
        }
        else if(pas.equals("")){

            pwd.setError("Enter password");
            prg.dismiss();
        }
        else{

            fAuth.signInWithEmailAndPassword(email,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        Intent in = new Intent(MainActivity.this,Dashboard.class);
                        startActivity(in);
                        finish();

                     //   saveAdmin();

                        prg.dismiss();
                    }else{

                        prg.dismiss();
                        Toast.makeText(getApplicationContext(),"Unable to login",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

 /*   private void saveAdmin(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();

        RequestBody formBody = new FormBody.Builder()
                .add("phone_id",phone_id)
                .add("name",retailer_name.getText().toString())
                .build();

        Request request = new Request.Builder().post(formBody).url(URL).build();

    }   */
}
