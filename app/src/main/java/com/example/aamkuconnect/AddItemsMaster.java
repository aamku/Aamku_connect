package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

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

public class AddItemsMaster extends AppCompatActivity {

    TextInputEditText product_sku,product_name,product_hsn_gst,product_qty,product_mrp,product_sp;
    Spinner sizeSpinner,brandSpinner;
    Button productSave;
    List<String> sizeList;
    List<String> brandList;

    private static final String URL = "https://aamku-connect.herokuapp.com/adminItemAdd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items_master);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Add item");

        //Button
        productSave = findViewById(R.id.productSave);

        //Spinner
        sizeSpinner = findViewById(R.id.sizeSpinner);
        brandSpinner = findViewById(R.id.brandSpinner);

        //EditText
        product_sku = findViewById(R.id.product_sku);
        product_name = findViewById(R.id.product_name);
        product_hsn_gst = findViewById(R.id.product_hsn_gst);
        product_qty = findViewById(R.id.product_qty);
        product_mrp = findViewById(R.id.product_mrp);
        product_sp = findViewById(R.id.product_sp);

        sizeList = new ArrayList<String>();
        sizeList.add("A3");
        sizeList.add("A4");
        sizeList.add("A5");

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sizeList);

        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        brandList = new ArrayList<>();
        brandList.add("Aamku");
        brandList.add("AamkuOne");
        brandList.add("Buzzet");

        ArrayAdapter<String> brandAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,brandList);

        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(brandAdapter);

        brandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        productSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog pr = new ProgressDialog(AddItemsMaster.this);
                pr.setMessage("Adding item...");
                pr.setCancelable(false);
                pr.show();

                 if(product_sku.getText().toString().equals("")){
                     product_sku.setError("Enter product sku");
                     pr.hide();
                 }
                 else if(product_name.getText().toString().equals("")){
                     product_name.setError("Enter product name");
                     pr.hide();
                 }
                 else if(product_hsn_gst.getText().toString().equals("")){
                     product_hsn_gst.setError("Enter gst code");
                     pr.hide();
                 }
                 else if(product_qty.getText().toString().equals("")){
                     product_qty.setError("Enter product quantity");
                     pr.hide();
                 }
                 else if(product_mrp.getText().toString().equals("")){
                     product_mrp.setError("Enter product MRP");
                     pr.hide();
                 }
                 else if(product_sp.getText().toString().equals("")){
                     product_sp.setError("Enter selling price");
                     pr.hide();
                 }
                 else{

                     OkHttpClient client = new OkHttpClient.Builder()
                             .connectTimeout(20, TimeUnit.SECONDS)
                             .readTimeout(20,TimeUnit.SECONDS)
                             .writeTimeout(20,TimeUnit.SECONDS)
                             .build();

                     RequestBody formBody = new FormBody.Builder()
                             .add("product_sku",product_sku.getText().toString())
                             .add("product_name",product_name.getText().toString())
                             .add("gst_hsn_code",product_hsn_gst.getText().toString())
                             .add("product_quantity",product_qty.getText().toString())
                             .add("size",sizeSpinner.getSelectedItem().toString())
                             .add("brand",brandSpinner.getSelectedItem().toString())
                             .add("mrp",product_mrp.getText().toString())
                             .add("selling_price",product_sp.getText().toString())
                             .build();

                     Request request = new Request.Builder().post(formBody).url(URL).build();

                     client.newCall(request).enqueue(new Callback() {

                         @Override
                         public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                             runOnUiThread(new Runnable() {
                                 @Override
                                 public void run() {

                                     try {

                                         String resp = response.body().string();

                                         if(resp.equals("Item added")){

                                             pr.hide();
                                             Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_SHORT).show();
                                         }

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

                                     pr.hide();
                                     Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                 }
                             });
                         }
                     });

                 }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case android.R.id.home:

                Intent i = new Intent(AddItemsMaster.this,Dashboard.class);
                startActivity(i);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
