package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aamkuconnect.Adapters.GetUsersAdapter;
import com.example.aamkuconnect.Adapters.PendingRetailerAdapter;
import com.example.aamkuconnect.Models.PendingRetail;
import com.example.aamkuconnect.Models.Users;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PendingRetailers extends AppCompatActivity {

    RecyclerView pendingRetailers;
    ProgressBar pendingProgress;
    PendingRetailerAdapter adapter;
    List<PendingRetail> pendingList;

    private static final String URL = "https://aamku-connect.herokuapp.com/getPendingRetailers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_retailers);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("Pending Retailers");
        ab.setDisplayHomeAsUpEnabled(true);

        pendingProgress = findViewById(R.id.pendingProgress);
        pendingRetailers = findViewById(R.id.pendingRetailers);

        pendingRetailers.setHasFixedSize(true);
        pendingRetailers.setLayoutManager(new LinearLayoutManager(this));

        pendingList = new ArrayList<>();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();


        Request request = new Request.Builder().url(URL).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            JSONArray jsonArray = new JSONArray(response.body().string());

                            if(jsonArray.length() > 0){

                                pendingProgress.setVisibility(View.INVISIBLE);


                                for(int i = 0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String str1 = object.getString("name");
                                    String str2 = object.getString("gst");
                                    String str3 = object.getString("mobile");


                                 /*   String str4 = "Name: " + str1;
                                    String str5 = "GST: " + str2;
                                    String str6 = "Mobile: " + str3;  */


                                    PendingRetail model = new PendingRetail(str1,str2,str3);
                                    pendingList.add(model);
                                }

                                adapter = new PendingRetailerAdapter(pendingList,PendingRetailers.this);
                                pendingRetailers.setAdapter(adapter);

                            }
                            else{

                                pendingProgress.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(),"No retailer requests",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (IndexOutOfBoundsException e){
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

                        pendingProgress.setVisibility(View.INVISIBLE);
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

                Intent i = new Intent(PendingRetailers.this,Dashboard.class);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(PendingRetailers.this);
        builder.setMessage("Do you want to exit.");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
