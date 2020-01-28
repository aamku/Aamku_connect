package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aamkuconnect.Salesperson.OrderManagement;
import com.example.aamkuconnect.Salesperson.RetailManagement;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FirstFragment extends Fragment {

    SharedPreferences sharedPreferences;
    String s;

    //ADmin section
    LinearLayout adminCard;
    CardView dealersCard,todayPurchase,monthlyPurchase,outstandingCard;
    TextView count;

    //Retailer section
    LinearLayout saleCard;
    CardView retailCard,orderCard;

    private static final String URL = "https://aamku-connect.herokuapp.com/dealerCount";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_fragment,container,false);

        //ADmin
        dealersCard = view.findViewById(R.id.dealersCard);
        todayPurchase = view.findViewById(R.id.todayPurchase);
        monthlyPurchase = view.findViewById(R.id.monthlyPurchase);
        outstandingCard = view.findViewById(R.id.outstandingCard);
        adminCard = view.findViewById(R.id.adminCard);
        count = view.findViewById(R.id.count);

        //salesperson
        saleCard = view.findViewById(R.id.saleCard);
        retailCard = view.findViewById(R.id.retailCard);
        orderCard=  view.findViewById(R.id.orderCard);

        sharedPreferences = getActivity().getSharedPreferences("simplifiedcodingsharedpref", Context.MODE_PRIVATE);
        s = sharedPreferences.getString("keytype","");

        if(s.equals("Salesperson")){

           adminCard.setVisibility(View.INVISIBLE);
           saleCard.setVisibility(View.VISIBLE);
        }
        else if(s.equals("Retailer")){

            saleCard.setVisibility(View.VISIBLE);
            adminCard.setVisibility(View.INVISIBLE);
        }
        else{

            saleCard.setVisibility(View.INVISIBLE);
            adminCard.setVisibility(View.VISIBLE);
           // showDealerCount();
        }

        orderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), OrderManagement.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        retailCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity(), RetailManagement.class);
                startActivity(in);
                getActivity().finish();
            }
        });

        dealersCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),AllDealers.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        return view;
    }

  /*  private void showDealerCount(){

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();


        Request request = new Request.Builder().url(URL).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            JSONArray jsonArray = new JSONArray(response.body().string());

                            if(jsonArray.length() > 0){

                                Toast.makeText(getActivity(),jsonArray.length(),Toast.LENGTH_SHORT).show();

                                for(int i = 0;i<jsonArray.length();i++){

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String str1 = jsonObject.getString("total");
                                    count.setText(str1);
                                    Log.d("count",str1);
                                }
                            }


                        }catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }   */
}
