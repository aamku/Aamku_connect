package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.LinkAddress;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    TextInputEditText product_sku,product_name,product_hsn_gst,product_qty,product_mrp,product_sp,product_brand,
                      product_size,product_rule_type,product_pages,inner_pack,outer_pack;
   // Spinner sizeSpinner,brandSpinner;

    Spinner orderType;
    Button productSave;
  //  List<String> sizeList;
  //  List<String> brandList;
    List<String> typeList;

    int hint = 0;

    LinearLayout parentLayout;
    String[] array;

    private static final String URL = "https://aamku-connect.herokuapp.com/adminItemAdd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items_master);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Add item");

        parentLayout = findViewById(R.id.dynamicLayout);

        //Button
        productSave = findViewById(R.id.productSave);
       // addField = findViewById(R.id.addField);

        //Spinner
      //  sizeSpinner = findViewById(R.id.sizeSpinner);
      //  brandSpinner = findViewById(R.id.brandSpinner);
        orderType = findViewById(R.id.orderType);



        //EditText
        product_sku = findViewById(R.id.product_sku);
        product_name = findViewById(R.id.product_name);
        product_hsn_gst = findViewById(R.id.product_hsn_gst);
        product_qty = findViewById(R.id.product_qty);
        product_mrp = findViewById(R.id.product_mrp);
        product_sp = findViewById(R.id.product_sp);
        product_brand = findViewById(R.id.product_brand);
        product_size = findViewById(R.id.product_size);
        product_rule_type = findViewById(R.id.product_rule_type);
        product_pages = findViewById(R.id.product_pages);
        inner_pack = findViewById(R.id.inner_pack);
        outer_pack = findViewById(R.id.outer_pack);

     /*   addField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createEditTextView();

            }
        });

         array = new String[parentLayout.getChildCount()];

        for (int i=0; i < parentLayout.getChildCount(); i++){
            EditText editText = (EditText)parentLayout.getChildAt(i);
            array[i] = editText.getText().toString();

        }   */


        typeList = new ArrayList<String>();
        typeList.add("Select order type");
        typeList.add("Box");
        typeList.add("Pieces");

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,typeList);

        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderType.setAdapter(sizeAdapter);

        orderType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


   /*     sizeList = new ArrayList<String>();
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
        });  */

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
                 else if(product_pages.getText().toString().equals("")){
                   product_pages.setError("Enter product page");
                   pr.hide();
                 }
                 else if(inner_pack.getText().toString().equals("")){
                   inner_pack.setError("Enter inner pack");
                   pr.hide();
                 }
                 else if(outer_pack.getText().toString().equals("")){
                   outer_pack.setError("Enter outer pack");
                   pr.hide();
                  }
                 else if(product_qty.getText().toString().equals("")){
                     product_qty.setError("Enter product quantity");
                     pr.hide();
                 }
                 else if(orderType.getSelectedItem().toString().equals("Select order type")){
                     Toast.makeText(getApplicationContext(),"Select order type",Toast.LENGTH_SHORT).show();
                     pr.hide();
                 }
                 else if(product_size.getText().toString().equals("")){
                   Toast.makeText(getApplicationContext(),"Enter size",Toast.LENGTH_SHORT).show();
                   pr.hide();
                 }
                 else if(product_brand.getText().toString().equals("")){
                   Toast.makeText(getApplicationContext(),"Enter brand",Toast.LENGTH_SHORT).show();
                   pr.hide();
                 }
                 else if(product_mrp.getText().toString().equals("")){
                     product_mrp.setError("Enter product MRP");
                     pr.hide();
                 }
                 else if(product_rule_type.getText().toString().equals("")){
                   product_mrp.setError("Enter product MRP");
                   pr.hide();
                 }
                 else{

                   SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                   Date date = new Date();

                     OkHttpClient client = new OkHttpClient.Builder()
                             .connectTimeout(20, TimeUnit.SECONDS)
                             .readTimeout(20,TimeUnit.SECONDS)
                             .writeTimeout(20,TimeUnit.SECONDS)
                             .build();

                     RequestBody formBody = new FormBody.Builder()
                             .add("product_sku",product_sku.getText().toString())
                             .add("product_name",product_name.getText().toString())
                             .add("gst_hsn_code",product_hsn_gst.getText().toString())
                             .add("pages",product_pages.getText().toString())
                             .add("inner_pack",inner_pack.getText().toString())
                             .add("outer_pack",outer_pack.getText().toString())
                             .add("product_quantity",product_qty.getText().toString())
                             .add("size",product_size.getText().toString())
                             .add("brand",product_brand.getText().toString())
                             .add("mrp",product_mrp.getText().toString())
                             .add("selling_price",product_sp.getText().toString())
                             .add("order_type",orderType.getSelectedItem().toString())
                             .add("rule_type",product_rule_type.getText().toString())
                             .add("date",formatter.format(date).toString())
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

 /*   protected void createEditTextView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(16,10,16,10);
        EditText edittTxt = new EditText(this);

        int maxLength = 50;
        hint++;
        edittTxt.setHint("Add ruling type");
        edittTxt.setLayoutParams(params);
        // edtTxt.setBackgroundColor(Color.WHITE);
        edittTxt.setInputType(InputType.TYPE_CLASS_TEXT);
        edittTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        edittTxt.setId(hint);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        edittTxt.setFilters(fArray);
        parentLayout.addView(edittTxt);
    }   */
}
