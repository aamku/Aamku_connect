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

    CardView  bankcardId,purchaseCard,orderManage,retailManage;
    SharedPreferences sharedPreferences;
    LinearLayout retailerSection,cardLayout;

    String s;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_fragment,container,false);

        bankcardId = view.findViewById(R.id.bankcardId);
        purchaseCard = view.findViewById(R.id.purchaseCard);
        cardLayout = view.findViewById(R.id.cardLayout);
        orderManage = view.findViewById(R.id.orderManage);
        retailManage = view.findViewById(R.id.retailManage);

        retailerSection = view.findViewById(R.id.retailerSection);

        sharedPreferences = getActivity().getSharedPreferences("simplifiedcodingsharedpref", Context.MODE_PRIVATE);
        s = sharedPreferences.getString("keytype","");

        if(s.equals("Salesperson")){

            bankcardId.setVisibility(View.INVISIBLE);
            purchaseCard.setVisibility(View.INVISIBLE);
            cardLayout.setVisibility(View.INVISIBLE);
        }

        orderManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), OrderManagement.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        retailManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity(), RetailManagement.class);
                startActivity(in);
                getActivity().finish();
            }
        });

        return view;
    }
}
