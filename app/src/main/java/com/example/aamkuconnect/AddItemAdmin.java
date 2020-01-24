package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class AddItemAdmin extends AppCompatActivity {

    Spinner spinnerProduct,spinnerSku,typeQty;
    TextInputEditText mrp,pages,qty;
    EditText oneSideLineQty,squareLineQty,fourLineQty,singleLineQty;
    Button saveAdminProduct;
    RadioGroup radioGroup;
    AppCompatCheckBox singleLine,fourLine,squareLine,oneSide;
    LinearLayout linesLayout;

    List<String> productList;
    List<String> typeList;

    private static final String URL ="https://aamku-connect.herokuapp.com/adminItemAddByAdmin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_admin);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setTitle("Add item");
        ab.setDisplayHomeAsUpEnabled(true);

        //Radio
        radioGroup = findViewById(R.id.radioGroup);

        //Spinner
        spinnerProduct = findViewById(R.id.spinnerProduct);
        spinnerSku = findViewById(R.id.spinnerSku);
        typeQty = findViewById(R.id.typeQty);
      //  pageSpinner = findViewById(R.id.pageSpinner);


        //Button
        saveAdminProduct = findViewById(R.id.saveAdminProduct);

        //AppCompatCheckBox
        mrp = findViewById(R.id.mrp);
        pages = findViewById(R.id.pages);
        singleLine  = findViewById(R.id.singleLine);
        fourLine = findViewById(R.id.fourline);
        squareLine = findViewById(R.id.fourline);
        oneSide = findViewById(R.id.oneSide);

        //EditText
        qty = findViewById(R.id.qty);
        oneSideLineQty = findViewById(R.id.oneSideLineQty);
        singleLineQty = findViewById(R.id.singleLineQty);
        fourLineQty = findViewById(R.id.fourLineQty);
        squareLineQty = findViewById(R.id.squareLineQty);

        linesLayout = findViewById(R.id.linesLayout);

        typeList = new ArrayList<String>();
        typeList.add("Box");
        typeList.add("Pieces");

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(AddItemAdmin.this,android.R.layout.simple_spinner_item,typeList);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeQty.setAdapter(typeAdapter);

        typeQty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        productList = new ArrayList<String>();
        productList.add("Crown");
        productList.add("Long Book A4");
        productList.add("Long Book");
        productList.add("Crown Junior");
        productList.add("Physics");
        productList.add("Chemistry");
        productList.add("Biology");
        productList.add("Universal");
        productList.add("Sketch Book");

        ArrayAdapter<String> productAdapter = new ArrayAdapter<String>(AddItemAdmin.this,android.R.layout.simple_spinner_item,productList);
        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProduct.setAdapter(productAdapter);

        spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String sp1 = spinnerProduct.getSelectedItem().toString();
                if(sp1.contentEquals("Crown")){

                    List<String> crownList = new ArrayList<String>();
                    crownList.add("8-112");
                    crownList.add("8-113");
                    crownList.add("8-115");
                    crownList.add("8-117");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, crownList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    spinnerSku.setAdapter(dataAdapter);

              /*      List<String> crownPageList = new ArrayList<String>();
                    crownPageList.add("Select pages");
                    crownPageList.add("72");
                    crownPageList.add("120");
                    crownPageList.add("144");
                    crownPageList.add("172");
                    ArrayAdapter<String> pagecrownAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, crownPageList);
                    pagecrownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    pagecrownAdapter.notifyDataSetChanged();
                    pageSpinner.setAdapter(pagecrownAdapter);  */

                    spinnerSku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String sp2 = spinnerSku.getSelectedItem().toString();
                             if(sp2.contentEquals("8-112")){
                                  mrp.setText("20.00");
                                  pages.setText("72");
                                  mrp.setEnabled(false);
                                  pages.setEnabled(false);
                            }
                            else if(sp2.contentEquals("8-113")){
                                mrp.setText("30.00");
                                pages.setText("120");
                                mrp.setEnabled(false);
                                pages.setEnabled(false);
                            }
                            else if(sp2.contentEquals("8-115")){
                                mrp.setText("35.00");
                                pages.setText("144");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else{
                                mrp.setText("40.00");
                                pages.setText("172");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if(sp1.contentEquals("Long Book A4")){

                    List<String> a4List = new ArrayList<String>();
                    a4List.add("8-412");
                    a4List.add("8-414");
                    a4List.add("8-415");
                    a4List.add("8-417");
                    a4List.add("8-419");
                    ArrayAdapter<String> a4Adapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, a4List);
                    a4Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    a4Adapter.notifyDataSetChanged();
                    spinnerSku.setAdapter(a4Adapter);

                /*    List<String> a4PageList = new ArrayList<String>();
                    a4PageList.add("Select pages");
                    a4PageList.add("124");
                    a4PageList.add("180");
                    a4PageList.add("200");
                    a4PageList.add("300");
                    a4PageList.add("400");
                    ArrayAdapter<String> a4pageAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, a4PageList);
                    a4pageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    a4pageAdapter.notifyDataSetChanged();
                    spinnerSku.setAdapter(a4pageAdapter);  */

                    spinnerSku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String sp2 = spinnerSku.getSelectedItem().toString();
                             if(sp2.contentEquals("8-412")){
                                mrp.setText("45.00");
                                pages.setText("124");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else if(sp2.contentEquals("8-414")){
                                mrp.setText("60.00");
                                pages.setText("180");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else if(sp2.contentEquals("8-415")){
                                mrp.setText("70.00");
                                 pages.setText("200");
                                 pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else if(sp2.contentEquals("8-417")){
                                mrp.setText("100.00");
                                 pages.setText("300");
                                 pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else{
                                mrp.setText("130.00");
                                 pages.setText("400");
                                 pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if(sp1.contentEquals("Long Book")){

                    List<String> longList = new ArrayList<String>();
                    longList.add("8-313");
                    longList.add("8-315");
                    longList.add("8-317");
                    longList.add("8-319");
                    longList.add("8-320");
                    ArrayAdapter<String> longAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, longList);
                    longAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    longAdapter.notifyDataSetChanged();
                    spinnerSku.setAdapter(longAdapter);

                  /*  List<String> longPageList = new ArrayList<String>();
                    longPageList.add("Select pages");
                    longPageList.add("116");
                    longPageList.add("164");
                    longPageList.add("200");
                    longPageList.add("272");
                    longPageList.add("400");
                    ArrayAdapter<String> longpageAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, longPageList);
                    longpageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    longpageAdapter.notifyDataSetChanged();
                    pageSpinner.setAdapter(longpageAdapter);  */

                    spinnerSku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String sp2 = spinnerSku.getSelectedItem().toString();
                            if(sp2.contentEquals("8-313")){
                                mrp.setText("30.00");
                                pages.setText("116");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else if(sp2.contentEquals("8-315")){
                                mrp.setText("40.00");
                                pages.setText("164");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else if(sp2.contentEquals("8-317")){
                                mrp.setText("50.00");
                                pages.setText("200");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else if(sp2.contentEquals("8-319")){
                                mrp.setText("70.00");
                                pages.setText("272");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else{
                                mrp.setText("100.00");
                                pages.setText("400");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if(sp1.contentEquals("Crown Junior")){

                    List<String> juniorList = new ArrayList<String>();
                    juniorList.add("8-211");
                    juniorList.add("8-212");
                    juniorList.add("8-213");
                    juniorList.add("8-216");
                    ArrayAdapter<String> juniorAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, juniorList);
                    juniorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    juniorAdapter.notifyDataSetChanged();
                    spinnerSku.setAdapter(juniorAdapter);

               /*     List<String> juniorPageList = new ArrayList<String>();
                    juniorPageList.add("Select pages");
                    juniorPageList.add("52");
                    juniorPageList.add("84");
                    juniorPageList.add("124");
                    juniorPageList.add("160");
                    ArrayAdapter<String> juniorpageAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, juniorPageList);
                    juniorpageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    juniorpageAdapter.notifyDataSetChanged();
                    pageSpinner.setAdapter(juniorpageAdapter);  */

                    spinnerSku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String sp2 = spinnerSku.getSelectedItem().toString();
                            if(sp2.contentEquals("8-211")){
                                mrp.setText("12.00");
                                pages.setText("52");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else if(sp2.contentEquals("8-212")){
                                mrp.setText("20.00");
                                pages.setText("84");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else if(sp2.contentEquals("8-213")){
                                mrp.setText("28.00");
                                pages.setText("124");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else{
                                mrp.setText("35.00");
                                pages.setText("160");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if(sp1.contentEquals("Physics")){

                    List<String> phyList = new ArrayList<String>();
                    phyList.add("8-926");
                    phyList.add("8-931");
                    ArrayAdapter<String> phyAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, phyList);
                    phyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    phyAdapter.notifyDataSetChanged();
                    spinnerSku.setAdapter(phyAdapter);

                    spinnerSku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String sp2 = spinnerSku.getSelectedItem().toString();
                             if(sp2.contentEquals("8-926")){
                                mrp.setText("50.00");
                                pages.setText("104");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else{
                                mrp.setText("65.00");
                                pages.setText("144");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if(sp1.contentEquals("Chemistry")){

                    List<String> chemList = new ArrayList<String>();
                    chemList.add("8-927");
                    chemList.add("8-932");
                    ArrayAdapter<String> chemAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, chemList);
                    chemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    chemAdapter.notifyDataSetChanged();
                    spinnerSku.setAdapter(chemAdapter);

                    spinnerSku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String sp2 = spinnerSku.getSelectedItem().toString();
                            if(sp2.contentEquals("8-927")){
                                mrp.setText("50.00");
                                pages.setText("104");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else{
                                mrp.setText("65.00");
                                pages.setText("144");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if(sp1.contentEquals("Biology")){

                    List<String> bioList = new ArrayList<String>();
                    bioList.add("8-928");
                    bioList.add("8-933");
                    ArrayAdapter<String> bioAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, bioList);
                    bioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    bioAdapter.notifyDataSetChanged();
                    spinnerSku.setAdapter(bioAdapter);

                    spinnerSku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String sp2 = spinnerSku.getSelectedItem().toString();
                            if(sp2.contentEquals("8-928")){
                                mrp.setText("50.00");
                                pages.setText("104");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else{
                                mrp.setText("65.00");
                                pages.setText("144");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if(sp1.contentEquals("Universal")){

                    List<String> uniList = new ArrayList<String>();
                    uniList.add("8-929");
                    uniList.add("8-934");
                    ArrayAdapter<String> uniAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, uniList);
                    uniAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    uniAdapter.notifyDataSetChanged();
                    spinnerSku.setAdapter(uniAdapter);

                    spinnerSku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String sp2 = spinnerSku.getSelectedItem().toString();
                            if(sp2.contentEquals("8-929")){
                                mrp.setText("50.00");
                                pages.setText("104");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                            else{
                                mrp.setText("65.00");
                                pages.setText("144");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else{

                    List<String> sketchList = new ArrayList<String>();
                    sketchList.add("8-961");
                    ArrayAdapter<String> sketchAdapter = new ArrayAdapter<String>(AddItemAdmin.this,
                            android.R.layout.simple_spinner_item, sketchList);
                    sketchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sketchAdapter.notifyDataSetChanged();
                    spinnerSku.setAdapter(sketchAdapter);

                    spinnerSku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String sp2 = spinnerSku.getSelectedItem().toString();
                            if(sp2.contentEquals("8-961")){
                                mrp.setText("30.00");
                                pages.setText("36");
                                pages.setEnabled(false);
                                mrp.setEnabled(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId){

                    case R.id.radio_plane:
                        linesLayout.setVisibility(View.GONE);
                        break;

                    case R.id.radio_ruled:
                        linesLayout.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

           saveAdminProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();

                final ProgressDialog pr1 = new ProgressDialog(AddItemAdmin.this);
                pr1.setCancelable(false);
                pr1.setMessage("Adding item...");
                pr1.show();

                if(qty.getText().toString().equals("")){
                    qty.setError("Enter quantity");
                    pr1.dismiss();
                }
                else{

                    OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20,TimeUnit.SECONDS)
                            .writeTimeout(20,TimeUnit.SECONDS)
                            .build();

                    RequestBody formBody = new FormBody.Builder()
                            .add("product_name",spinnerProduct.getSelectedItem().toString())
                            .add("product_sku",spinnerSku.getSelectedItem().toString())
                            .add("quantity",qty.getText().toString())
                            .add("order_type",typeQty.getSelectedItem().toString())
                            .add("pages",pages.getText().toString())
                            .add("mrp",mrp.getText().toString())
                            .add("single_line_quantity",singleLineQty.getText().toString())
                            .add("four_line_quantity",fourLineQty.getText().toString())
                            .add("square_line_quantity",squareLineQty.getText().toString())
                            .add("oneside_line_quantity",oneSideLineQty.getText().toString())
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

                                            pr1.dismiss();
                                            Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_SHORT).show();
                                        }
                                    }catch (IOException e) {
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

                                    pr1.dismiss();
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

        switch (item.getItemId()){

            case android.R.id.home:

                Intent i = new Intent(AddItemAdmin.this,Dashboard.class);
                startActivity(i);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
