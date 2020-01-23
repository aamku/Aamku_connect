package com.example.aamkuconnect.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.example.aamkuconnect.PendingRetailers;
import com.example.aamkuconnect.R;
import com.example.aamkuconnect.Salesperson.AddRetailer;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PendingRetailerAdapter extends RecyclerView.Adapter<PendingRetailerAdapter.ViewHolder> {

    private static final String URL = "https://aamku-connect.herokuapp.com/approveRetailer";

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
    public void onBindViewHolder(@NonNull final PendingRetailerAdapter.ViewHolder holder, final int position) {

        final PendingRetail model = pendingList.get(position);



        holder.retailer_row_name.setText(model.getPendingName());
        holder.retailer_row_gst.setText(model.getPendingGst());
        holder.retailer_row_phone.setText(model.getPendingPhone());

        final String phone = model.getPendingPhone();

        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

      //  Toast.makeText(context,phone,Toast.LENGTH_SHORT).show();

               approveRetailer(phone,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pendingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button approve,cancelBut;
        TextView retailer_row_name,retailer_row_gst,retailer_row_phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            approve = itemView.findViewById(R.id.approve);
            retailer_row_name = itemView.findViewById(R.id.retailer_row_name);
            retailer_row_gst = itemView.findViewById(R.id.retailer_row_gst);
            retailer_row_phone = itemView.findViewById(R.id.retailer_row_phone);
        }
    }

    private void approveRetailer(String phone, final int position){

     //   Toast.makeText(context,phone,Toast.LENGTH_SHORT).show();
        final ProgressDialog prg1 = new ProgressDialog(context);
        prg1.setMessage("Approving retailer...");
        prg1.setCancelable(false);
        prg1.show();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();

        RequestBody formBody = new FormBody.Builder()
                .add("phone",phone)
                .build();

        Request request = new Request.Builder().post(formBody).url(URL).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                ((PendingRetailers)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            String resp = response.body().string();

                            if(resp.equals("Retailer approved")){

                                prg1.dismiss();
                                Toast.makeText(context, resp, Toast.LENGTH_SHORT).show();

                                pendingList.remove(position);
                                notifyItemRemoved(position);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                ((PendingRetailers)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        prg1.dismiss();
                        Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
