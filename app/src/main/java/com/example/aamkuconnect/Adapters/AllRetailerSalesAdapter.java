package com.example.aamkuconnect.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aamkuconnect.Models.AllRetailerModel;
import com.example.aamkuconnect.R;

import java.util.List;

public class AllRetailerSalesAdapter extends RecyclerView.Adapter<AllRetailerSalesAdapter.ViewHolder> {

    List<AllRetailerModel> retailerList;
    Context context;

    public AllRetailerSalesAdapter(List<AllRetailerModel> retailerList, Context context) {
        this.retailerList = retailerList;
        this.context = context;
    }

    @NonNull
    @Override
    public AllRetailerSalesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_retailer_salesperson_row,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllRetailerSalesAdapter.ViewHolder holder, int position) {

        AllRetailerModel model = retailerList.get(position);
    }

    @Override
    public int getItemCount() {
        return retailerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
