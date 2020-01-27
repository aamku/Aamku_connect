package com.example.aamkuconnect.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aamkuconnect.Models.ShowItemsModel;
import com.example.aamkuconnect.R;

import java.util.List;

public class ShowItemsAdapter extends RecyclerView.Adapter<ShowItemsAdapter.ViewHolder> {

    List<ShowItemsModel> itemsList;
    Context context;

    public ShowItemsAdapter(List<ShowItemsModel> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_items_row,parent,false);

       ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowItemsAdapter.ViewHolder holder, int position) {

        ShowItemsModel model = itemsList.get(position);

        holder.row_itemName.setText(model.getPname());
        holder.row_sku.setText(model.getPsku());
        holder.row_page.setText(model.getPpages());
        holder.row_price.setText(model.getPmrp());
        holder.row_inner.setText(model.getPinner());
        holder.row_outer.setText(model.getPouter());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView row_itemName,row_sku,row_page,row_price,row_inner,row_outer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            row_itemName = itemView.findViewById(R.id.row_itemName);
            row_sku = itemView.findViewById(R.id.row_sku);
            row_page = itemView.findViewById(R.id.row_page);
            row_price = itemView.findViewById(R.id.row_price);
            row_inner = itemView.findViewById(R.id.row_inner);
            row_outer = itemView.findViewById(R.id.row_outer);
        }
    }
}
