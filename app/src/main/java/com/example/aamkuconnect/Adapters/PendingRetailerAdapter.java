package com.example.aamkuconnect.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aamkuconnect.Models.PendingRetail;
import com.example.aamkuconnect.R;

import java.util.List;

public class PendingRetailerAdapter extends RecyclerView.Adapter<PendingRetailerAdapter.ViewHolder> {

    List<PendingRetail> pendingList;
    Context context;

    public PendingRetailerAdapter(List<PendingRetail> pendingList, Context context) {
        this.pendingList = pendingList;
        this.context = context;
    }

    @NonNull
    @Override
    public PendingRetailerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_retailer_row,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PendingRetailerAdapter.ViewHolder holder, int position) {

        final PendingRetail model = pendingList.get(position);

        holder.retailer_row_name.setText(model.getName());
        holder.retailer_row_gst.setText(model.getGst());
        holder.retailer_row_phone.setText(model.getPhone());

        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pendingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button approve;
        TextView retailer_row_name,retailer_row_gst,retailer_row_phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            approve = itemView.findViewById(R.id.approve);
            retailer_row_name = itemView.findViewById(R.id.retailer_row_name);
            retailer_row_gst = itemView.findViewById(R.id.retailer_row_gst);
            retailer_row_phone = itemView.findViewById(R.id.retailer_row_phone);
        }
    }
}
