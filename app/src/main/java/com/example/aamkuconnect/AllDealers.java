package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aamkuconnect.Adapters.AllDealersAdapter;
import com.example.aamkuconnect.Models.DealerModel;

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

public class AllDealers extends AppCompatActivity {

    RecyclerView recyclerDealer;
    ProgressBar dealerProg;
    List<DealerModel> dealerList = new ArrayList<>();
    AllDealersAdapter adapter;

    private static final String URL = "https://aamku-connect.herokuapp.com/allDealers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_dealers);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setTitle("All dealers");
        ab.setDisplayHomeAsUpEnabled(true);

          dealerProg = findViewById(R.id.dealerProg);
          recyclerDealer = findViewById(R.id.recyclerDealer);
          recyclerDealer.setHasFixedSize(true);
          recyclerDealer.setLayoutManager(new LinearLayoutManager(this));

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

                                dealerProg.setVisibility(View.INVISIBLE);
                                for(int i = 0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String str1 = object.getString("name");
                                    String str2 = "Phone: " + object.getString("phone");
                                    String str3 = "Location: " + object.getString("location");
                                    String str4 = "PIN: " + object.getString("pin");
                                    String str5 = "Address: " + object.getString("address");

                                    DealerModel model  = new DealerModel(str1,str2,str3,str4,str5);
                                    dealerList.add(model);
                                }

                                adapter= new AllDealersAdapter(getApplicationContext(),dealerList);
                                recyclerDealer.setAdapter(adapter);
                            }
                            else{
                                dealerProg.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(),"No dealer available",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
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
                        dealerProg.setVisibility(View.INVISIBLE);
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

                Intent i = new Intent(AllDealers.this,Dashboard.class);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
