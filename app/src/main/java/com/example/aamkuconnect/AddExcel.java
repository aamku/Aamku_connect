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

import com.example.aamkuconnect.Adapters.AddExcelSalesAdapter;
import com.example.aamkuconnect.Models.AddExcelSalesModel;

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

public class AddExcel extends AppCompatActivity {

    RecyclerView excelSalesRecycler;
    ProgressBar progAd;

    AddExcelSalesAdapter adapter;
    List<AddExcelSalesModel> saleList;

    private static final String URL = "https://aamku-connect.herokuapp.com/getSalesUploadFiles";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excel);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setTitle("Add excel");
        ab.setDisplayHomeAsUpEnabled(true);

        progAd = findViewById(R.id.progAd);
        excelSalesRecycler = findViewById(R.id.excelSalesRecycler);
        excelSalesRecycler.setHasFixedSize(true);
        excelSalesRecycler.setLayoutManager(new LinearLayoutManager(this));

        saleList = new ArrayList<>();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();

        RequestBody formBody = new FormBody.Builder()
                .add("role","SalesPerson")
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

                                progAd.setVisibility(View.INVISIBLE);

                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String str1 = jsonObject.getString("name");
                                    String str2 = jsonObject.getString("mobile");

                                    AddExcelSalesModel model = new AddExcelSalesModel(str1,str2);
                                    saleList.add(model);
                                }

                                adapter = new AddExcelSalesAdapter(getApplicationContext(),saleList);
                                excelSalesRecycler.setAdapter(adapter);

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

                        progAd.setVisibility(View.INVISIBLE);
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
                Intent i = new Intent(AddExcel.this,Dashboard.class);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
