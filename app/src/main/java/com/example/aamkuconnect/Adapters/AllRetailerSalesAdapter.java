package com.example.aamkuconnect.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

        holder.retName.setText(model.getMy_retailer_name());
        holder.retDate.setText(model.getRetailer_date());
     //   holder.retStatus.setText(model.getRetailer_status());
        if(model.getRetailer_status().equals("pending")){
            Glide.with(context).load(R.drawable.ic_info_outline_amber_500_24dp).into(holder.statusIcon);
        }
        else if(model.getRetailer_status().equals("approved")){
            Glide.with(context).load(R.drawable.ic_done_green_500_24dp).into(holder.statusIcon);
        }
        else{
            Glide.with(context).load(R.drawable.ic_highlight_off_red_400_24dp).into(holder.statusIcon);
        }
    }

    @Override
    public int getItemCount() {
        return retailerList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView retName,retDate,retStatus;
        ImageView statusIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            retName = itemView.findViewById(R.id.retName);
            retDate = itemView.findViewById(R.id.retDate);
            statusIcon = itemView.findViewById(R.id.statusIcon);
          //  retStatus = itemView.findViewById(R.id.retStatus);
        }
    }
}
