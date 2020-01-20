package com.example.aamkuconnect.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aamkuconnect.Models.Users;
import com.example.aamkuconnect.R;

import java.util.List;

public class GetUsersAdapter extends RecyclerView.Adapter<GetUsersAdapter.ViewHolder> {

    List<Users> userList;
    Context context;

    public GetUsersAdapter(List<Users> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public GetUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_user_row,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GetUsersAdapter.ViewHolder holder, int position) {

        final Users model = userList.get(position);

        holder.user_name.setText(model.getName());
        holder.user_role.setText(model.getRole());
        holder.user_phone.setText(model.getPhone());
        holder.user_email.setText(model.getEmail());
        holder.user_empid.setText(model.getEmpid());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView user_name,user_role,user_phone,user_email,user_empid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_name = itemView.findViewById(R.id.user_name);
            user_role = itemView.findViewById(R.id.user_role);
            user_phone = itemView.findViewById(R.id.user_phone);
            user_email = itemView.findViewById(R.id.user_email);
            user_empid = itemView.findViewById(R.id.user_empid);
        }
    }
}
