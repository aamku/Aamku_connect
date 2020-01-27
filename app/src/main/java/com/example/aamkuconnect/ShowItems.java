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

import com.example.aamkuconnect.Adapters.ShowItemsAdapter;
import com.example.aamkuconnect.Models.ShowItemsModel;

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

public class ShowItems extends AppCompatActivity {

    ProgressBar itemsProgress;
    RecyclerView showItemsRecycler;
    List<ShowItemsModel> itemsList;
    ShowItemsAdapter adapter;

    private static final String URL ="https://aamku-connect.herokuapp.com/showItems";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        itemsProgress = findViewById(R.id.itemsProgress);
        showItemsRecycler = findViewById(R.id.showItemsRecycler);
        showItemsRecycler.setHasFixedSize(true);
        showItemsRecycler.setLayoutManager(new LinearLayoutManager(this));

        itemsList = new ArrayList<>();

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("All items");
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

                                itemsProgress.setVisibility(View.INVISIBLE);

                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String str1 = object.getString("name");
                                    String str2 = object.getString("sku");
                                    String str3 = object.getString("pages");
                                    String str4 = object.getString("mrp");
                                    String str5 = object.getString("inner");
                                    String str6 = object.getString("outer");

                                    String str7 = "Sku: " + str2;
                                    String str8 = "Pages: " + str3;
                                    String str9 = "MRP: " + str4;
                                    String str10 = "Inner pack: " + str5;
                                    String str11 = "Outer pack: " + str6;

                                    ShowItemsModel model = new ShowItemsModel(str1,str7,str8,str9,str10,str11);
                                    itemsList.add(model);
                                }

                                adapter = new ShowItemsAdapter(itemsList,ShowItems.this);
                                showItemsRecycler.setAdapter(adapter);

                            }
                            else{

                                itemsProgress.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(),"No item found", Toast.LENGTH_SHORT).show();
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

                        itemsProgress.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case android.R.id.home:

                Intent i = new Intent(ShowItems.this,Dashboard.class);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
