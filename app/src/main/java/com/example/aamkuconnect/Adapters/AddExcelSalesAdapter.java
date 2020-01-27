package com.example.aamkuconnect.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aamkuconnect.Models.AddExcelSalesModel;
import com.example.aamkuconnect.R;

import java.util.List;

public class AddExcelSalesAdapter extends RecyclerView.Adapter<AddExcelSalesAdapter.ViewHolder> {

    Context context;
    List<AddExcelSalesModel> saleList;

    public AddExcelSalesAdapter(Context context, List<AddExcelSalesModel> saleList) {
        this.context = context;
        this.saleList = saleList;
    }

    @NonNull
    @Override
    public AddExcelSalesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_excel_add_row,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddExcelSalesAdapter.ViewHolder holder, int position) {

        AddExcelSalesModel model = saleList.get(position);

        holder.sale_name.setText(model.getSaleName());
        holder.sale_phone.setText(model.getSalePhone());

        holder.uploadExcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return saleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView sale_name,sale_phone;
        ImageView uploadExcel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sale_name = itemView.findViewById(R.id.sale_name);
            sale_phone = itemView.findViewById(R.id.sale_phone);
            uploadExcel = itemView.findViewById(R.id.uploadExcel);
        }
    }
}
