package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aamkuconnect.Adapters.AdminSeeRetailersAdapter;
import com.example.aamkuconnect.Adapters.AllRetailerSalesAdapter;
import com.example.aamkuconnect.Models.AdminSeeRetailerModel;
import com.example.aamkuconnect.Models.AllRetailerModel;
import com.example.aamkuconnect.Salesperson.AllRetailers;

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

public class AdminSeeRetailers extends AppCompatActivity {

    ProgressBar myProg1;
    RecyclerView adminRecycler;

    AdminSeeRetailersAdapter adapter;

    List<AdminSeeRetailerModel> retList =  new ArrayList<>();

    private static final String URL = "https://aamku-connect.herokuapp.com/adminSeeRetailers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_see_retailers);

        myProg1 = findViewById(R.id.myProg1);
        adminRecycler = findViewById(R.id.adminRecycler);
        adminRecycler.setHasFixedSize(true);
        adminRecycler.setLayoutManager(new LinearLayoutManager(this));

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("All retailers");
        ab.setDisplayHomeAsUpEnabled(true);

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

                            if(jsonArray.length()>0){

                                myProg1.setVisibility(View.INVISIBLE);

                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String str1 = object.getString("name");
                                    String str2 = object.getString("mobile");
                                    String str3 = object.getString("gst");
                                    String str4 = object.getString("state");
                                    String str5 = object.getString("pin");
                                    String str6 = object.getString("address");

                                    String str7 = "Phone: " + str2;
                                    String str8 = "GST: " + str3;
                                    String str9 = "State: " + str4;
                                    String str10 = "PIN: " + str5;
                                    String str11 = "Address: " + str6;

                                    Log.d("name",str1);

                                    AdminSeeRetailerModel model = new AdminSeeRetailerModel(str1,str7,str8,str9,str10,str11);
                                    retList.add(model);
                                }

                                adapter = new AdminSeeRetailersAdapter(AdminSeeRetailers.this,retList);
                                adminRecycler.setAdapter(adapter);
                            }
                            else{

                                Toast.makeText(getApplicationContext(),"No retailer found",Toast.LENGTH_SHORT).show();
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
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

                        myProg1.setVisibility(View.INVISIBLE);
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

                Intent i = new Intent(AdminSeeRetailers.this,Dashboard.class);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
