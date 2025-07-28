package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserGridAdapter2 extends RecyclerView.Adapter<UserGridAdapter2.UserGridViewHolder>{
    ArrayList<User> lstUser;
    Context context;
    UserCallback userCallback;

    public UserGridAdapter2(ArrayList<User> lstUser, UserCallback userCallback) {
        this.lstUser = lstUser;
        this.userCallback=userCallback;
    }

    @NonNull
    @Override
    public UserGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View userView= inflater.inflate(R.layout.layoutitem2,parent,false);
        UserGridViewHolder viewHolder = new UserGridViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserGridViewHolder holder, int position) {
        User item = lstUser.get(position);
        holder.imAvarta.setImageBitmap(Utils.convertToBitmapAssets(context,item.getAvatar()));
        holder.tvName.setText(item.getName());
        //
        holder.itemView.setOnClickListener(view -> userCallback.onItemClick(item.getAvatar()));

    }

    @Override
    public int getItemCount() {
        return lstUser.size();
    }

    class UserGridViewHolder extends RecyclerView.ViewHolder{
        ImageView imAvarta;
        TextView tvName;
        public UserGridViewHolder(@NonNull View itemView) {
            super(itemView);
            imAvarta=itemView.findViewById(R.id.ivAvartar);
            tvName=itemView.findViewById(R.id.tvName);
        }
    }
    public interface UserCallback{
        void onItemClick(String name);
    }
}
