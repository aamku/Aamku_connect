package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aamkuconnect.Adapters.GetUsersAdapter;
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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AllUsers extends AppCompatActivity {

    Spinner filter;
    String item;
    ProgressBar progressBar;
    GetUsersAdapter adapter;
    List<Users> userList;
    RecyclerView recycler;

    private static final String URL = "https://aamku-connect.herokuapp.com/allUsers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("All users");
        ab.setDisplayHomeAsUpEnabled(true);

        filter = findViewById(R.id.filter);
        progressBar = findViewById(R.id.progress);
        recycler = findViewById(R.id.recycler);

        List<String> roles = new ArrayList<String>();
        roles.add("Select user type");
        roles.add("Admin");
        roles.add("SalesPerson");

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(AllUsers.this));

        userList = new ArrayList<>();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,roles);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(dataAdapter);

        getUsers();

        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                item = parent.getItemAtPosition(position).toString();

         /*       if(item.equals("Select user type")){

                    progressBar.setVisibility(View.GONE);
                }
                else{

                    getUsers(item);

                }  */

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case android.R.id.home:
                Intent i = new Intent(AllUsers.this,Dashboard.class);
                startActivity(i);
                finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private void getUsers(){

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();


    /*    RequestBody formBody = new FormBody.Builder()
                .add("role",item)
                .build();  */

        Request request = new Request.Builder().url(URL).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            JSONArray jsonArray = new JSONArray(response.body().string());

                            if(jsonArray.length() > 1){

                                progressBar.setVisibility(View.INVISIBLE);
                                recycler.setVisibility(View.VISIBLE);

                                for(int i = 0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String str1 = object.getString("name");
                                    String str2 = object.getString("role");
                                    String str3 = object.getString("mobile");
                                    String str4 = object.getString("email");
                                    String str5 = object.getString("emp_id");

                                   // Log.d("name",str1);

                                   // String name = str1;
                                    String role = "Role: " + str2;
                                    String mobile = "Mobile: " + str3;
                                    String email = "Email: " + str4;
                                    String emp_id = "Employee ID: " + str5;

                                    Users model = new Users(str1,role,mobile,email,emp_id);
                                    userList.add(model);
                                }

                                adapter = new GetUsersAdapter(userList,AllUsers.this);
                                recycler.setAdapter(adapter);
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

                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }
}
