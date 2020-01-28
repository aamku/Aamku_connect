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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aamkuconnect.Adapters.RetManagAdapter;
import com.example.aamkuconnect.Dashboard;
import com.example.aamkuconnect.Models.RetManageModel;
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

public class RetailManagement extends AppCompatActivity {

    RecyclerView recyclerRet;
    ProgressBar progRetail;
    RetManagAdapter adapter;
    List<RetManageModel> managList;
    String salePersonId;

    SharedPreferences sp;

    Button addRet;

    private static final String URL = "https://aamku-connect.herokuapp.com/retManageShow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_management);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setTitle("Order management");
        ab.setDisplayHomeAsUpEnabled(true);

        sp = getSharedPreferences("simplifiedcodingsharedpref", Context.MODE_PRIVATE);
        salePersonId = sp.getString("keyphone","");

        recyclerRet = findViewById(R.id.recyclerRet);
        progRetail = findViewById(R.id.progRetail);
        addRet = findViewById(R.id.addRet);

        recyclerRet.setHasFixedSize(true);
        recyclerRet.setLayoutManager(new LinearLayoutManager(this));

        managList = new ArrayList<>();

        addRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RetailManagement.this,AddRetailer.class);
                startActivity(i);
            }
        });

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

                                progRetail.setVisibility(View.INVISIBLE);

                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String str1 = jsonObject.getString("name");
                                    String str2 = jsonObject.getString("mobile");
                                    String str3 = jsonObject.getString("address");

                                    String str4 = "Name: " + str1;
                                    String str5 = "Phone: " + str2;
                                    String str6 = "Address: " + str3;

                                    RetManageModel model = new RetManageModel(str4,str5,str6);
                                    managList.add(model);
                                }

                                adapter = new RetManagAdapter(getApplicationContext(),managList);
                                recyclerRet.setAdapter(adapter);
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

                        progRetail.setVisibility(View.INVISIBLE);
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
                Intent i = new Intent(RetailManagement.this, Dashboard.class);
                startActivity(i);
                finish();

        }

        return super.onOptionsItemSelected(item);
    }
}
