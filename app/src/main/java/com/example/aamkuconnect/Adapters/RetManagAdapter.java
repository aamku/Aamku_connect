package com.example.aamkuconnect.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aamkuconnect.Models.RetManageModel;
import com.example.aamkuconnect.R;

import java.util.List;

public class RetManagAdapter extends RecyclerView.Adapter<RetManagAdapter.ViewHolder> {

    Context context;
    List<RetManageModel> managList;

    public RetManagAdapter(Context context, List<RetManageModel> managList) {
        this.context = context;
        this.managList = managList;
    }

    @NonNull
    @Override
    public RetManagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ret_manag_row,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RetManagAdapter.ViewHolder holder, int position) {

        RetManageModel model = managList.get(position);

        holder.mangNam.setText(model.getName());
        holder.mangPhone.setText(model.getPhone());
        holder.mangAddress.setText(model.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return managList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mangNam,mangAddress,mangPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mangNam = itemView.findViewById(R.id.mangNam);
            mangAddress = itemView.findViewById(R.id.mangAddress);
            mangPhone = itemView.findViewById(R.id.mangPhone);
        }
    }
}
