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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.aamkuconnect.Salesperson.OrderManagement;
import com.example.aamkuconnect.Salesperson.RetailManagement;

public class FirstFragment extends Fragment {

    SharedPreferences sharedPreferences;

    String s;

    //ADmin section
    LinearLayout adminCard;
    CardView dealersCard,todayPurchase,monthlyPurchase,outstandingCard;

    //Retailer section
    LinearLayout saleCard;
    CardView retailCard,orderCard;

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
}
