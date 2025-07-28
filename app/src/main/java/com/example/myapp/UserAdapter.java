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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    ArrayList<User> lstUser;
    Context context;
    UserCallback userCallback;

    public void setCallback(UserCallback callback) {
        this.userCallback = callback;
    }
    public UserAdapter(ArrayList<User> lstUser, UserCallback userCallback) {
        this.lstUser = lstUser;
        this.userCallback=userCallback;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View userView= inflater.inflate(R.layout.layoutitem,parent,false);
        UserViewHolder viewHolder = new UserViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User item = lstUser.get(position);
        holder.imAvarta.setImageBitmap(Utils.convertToBitmapAssets(context,item.getAvatar()));
        holder.tvName.setText(item.getName());
        //
        holder.itemView.setOnClickListener(view -> userCallback.onItemClick(item.getAvatar()));
        holder.ivDelete.setOnClickListener(view -> userCallback.onItemDeleteClicked(item, position));
        holder.ivEdit.setOnClickListener(view -> userCallback.onItemEditClicked(item, position));
    }

    @Override
    public int getItemCount() {
        return lstUser.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        ImageView imAvarta;
        TextView tvName;
        ImageView ivEdit;
        ImageView ivDelete;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imAvarta=itemView.findViewById(R.id.ivAvartar);
            tvName=itemView.findViewById(R.id.tvName);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
    public interface UserCallback{
        void onItemClick(String id);
        void onItemDeleteClicked(User us, int position);
        void onItemEditClicked(User us, int position);
    }
}
