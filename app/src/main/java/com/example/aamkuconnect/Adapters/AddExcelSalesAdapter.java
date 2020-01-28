package com.example.aamkuconnect.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aamkuconnect.Models.AddExcelSalesModel;
import com.example.aamkuconnect.PendingRetailers;
import com.example.aamkuconnect.R;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class AddExcelSalesAdapter extends RecyclerView.Adapter<AddExcelSalesAdapter.ViewHolder> {

    Context context;
    List<AddExcelSalesModel> saleList;

    public static final int PERMISSIONS_REQUEST_CODE = 0;
    public static final int FILE_PICKER_REQUEST_CODE = 1;

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

                openFilePicker();



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

    private void openFilePicker() {
        new MaterialFilePicker()
                .withActivity((Activity)context)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withHiddenFiles(true)
                .withTitle("Sample title")
                .start();
    }

}










