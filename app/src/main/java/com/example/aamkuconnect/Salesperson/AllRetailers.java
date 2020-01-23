package com.example.aamkuconnect.Salesperson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aamkuconnect.Adapters.AllRetailerSalesAdapter;
import com.example.aamkuconnect.Adapters.GetUsersAdapter;
import com.example.aamkuconnect.AllUsers;
import com.example.aamkuconnect.Dashboard;
import com.example.aamkuconnect.Models.AllRetailerModel;
import com.example.aamkuconnect.Models.Users;
import com.example.aamkuconnect.R;

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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AllRetailers extends AppCompatActivity {

    AllRetailerSalesAdapter adapter;
    List<AllRetailerModel> retailerList = new ArrayList<>();
    RecyclerView retailerRecycler;
    ProgressBar myProg;
    String salePersonId;

    SharedPreferences sp;

    private static final String URL = "https://aamku-connect.herokuapp.com/getSalesRetailer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_retailers);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setDisplayHomeAsUpEnabled(true);

        myProg = findViewById(R.id.myProg);
        retailerRecycler = findViewById(R.id.retailerRecycler);

        retailerRecycler.setHasFixedSize(true);
        retailerRecycler.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("simplifiedcodingsharedpref", Context.MODE_PRIVATE);
        salePersonId = sp.getString("keyphone","");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();


        RequestBody formBody = new FormBody.Builder()
                .add("id",salePersonId)
                .build();

        Request request = new Request.Builder().post(formBody).url(URL).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            JSONArray jsonArray = new JSONArray(response.body().string());

                            if(jsonArray.length() > 0){

                                myProg.setVisibility(View.INVISIBLE);
                                for(int i = 0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String str1 = object.getString("name");
                                    String str2 = object.getString("time");
                                    String str3 = object.getString("status");



                                    AllRetailerModel model = new AllRetailerModel(str1,str2,str3);
                                    retailerList.add(model);
                                }

                                adapter = new AllRetailerSalesAdapter(retailerList,AllRetailers.this);
                                retailerRecycler.setAdapter(adapter);
                            }
                            else{
                                myProg.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(),"No retailers available",Toast.LENGTH_SHORT).show();
                            }

                            } catch (JSONException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        myProg.setVisibility(View.INVISIBLE);
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
                Intent i = new Intent(AllRetailers.this, Dashboard.class);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
