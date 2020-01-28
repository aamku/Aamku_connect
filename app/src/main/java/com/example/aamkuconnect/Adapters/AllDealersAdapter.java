package com.example.aamkuconnect.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aamkuconnect.Models.DealerModel;
import com.example.aamkuconnect.R;

import java.util.List;

public class AllDealersAdapter extends RecyclerView.Adapter<AllDealersAdapter.ViewHolder>{

    Context context;
    List<DealerModel> dealerList;

    public AllDealersAdapter(Context context, List<DealerModel> dealerList) {
        this.context = context;
        this.dealerList = dealerList;
    }

    @NonNull
    @Override
    public AllDealersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dealer_row,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllDealersAdapter.ViewHolder holder, int position) {

        DealerModel model = dealerList.get(position);

        holder.dNam.setText(model.getdName());
        holder.dPhon.setText(model.getdPhone());
        holder.dLoc.setText(model.getdLocation());
        holder.dPinCode.setText(model.getdPin());
        holder.dAdd.setText(model.getdAdr());

    }

    @Override
    public int getItemCount() {
        return dealerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dNam,dPhon,dLoc,dPinCode,dAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dNam = itemView.findViewById(R.id.dNam);
            dPhon = itemView.findViewById(R.id.dPhon);
            dLoc = itemView.findViewById(R.id.dLoc);
            dPinCode = itemView.findViewById(R.id.dPinCode);
            dAdd = itemView.findViewById(R.id.dAdd);

        }
    }

}
