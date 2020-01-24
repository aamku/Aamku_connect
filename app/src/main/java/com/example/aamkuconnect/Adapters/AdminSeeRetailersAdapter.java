package com.example.aamkuconnect.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aamkuconnect.AdminSeeRetailers;
import com.example.aamkuconnect.Models.AdminSeeRetailerModel;
import com.example.aamkuconnect.R;

import java.util.List;

public class AdminSeeRetailersAdapter extends RecyclerView.Adapter<AdminSeeRetailersAdapter.ViewHolder> {

    Context context;
    List<AdminSeeRetailerModel> retList;

    public AdminSeeRetailersAdapter(Context context, List<AdminSeeRetailerModel> retList) {
        this.context = context;
        this.retList = retList;
    }

    @NonNull
    @Override
    public AdminSeeRetailersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_see_retailer_row,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdminSeeRetailersAdapter.ViewHolder holder, int position) {

        AdminSeeRetailerModel model = retList.get(position);

        holder.holderName.setText(model.getAdminseeName());
        holder.holderPhone.setText(model.getAdminseePhone());
        holder.holderGst.setText(model.getAdminseeGst());
        holder.holderState.setText(model.getAdminseeState());
        holder.holderPin.setText(model.getAdminseePin());
        holder.holderAddress.setText(model.getAdminseeAddress());
    }

    @Override
    public int getItemCount() {
        return retList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView holderName,holderPhone,holderGst,holderState,holderPin,holderAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            holderName = itemView.findViewById(R.id.adminSeeRetailerName);
            holderPhone = itemView.findViewById(R.id.adminSeeRetailerPhone);
            holderGst = itemView.findViewById(R.id.adminSeeRetailerGst);
            holderState = itemView.findViewById(R.id.adminSeeRetailerState);
            holderPin = itemView.findViewById(R.id.adminSeeRetailerPin);
            holderAddress = itemView.findViewById(R.id.adminSeeRetailerAddress);
        }
    }
}
