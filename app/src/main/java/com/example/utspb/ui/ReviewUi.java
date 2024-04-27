package com.example.utspb.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.utspb.R;
import com.example.utspb.data.response.Responses;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ReviewUi extends RecyclerView.Adapter<ReviewUi.ViewHolder> {
    private List<Responses> users;
    public ReviewUi(List<Responses> users){
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView tvuser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.ivFoto);
            tvuser = itemView.findViewById(R.id.tvNama);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Responses user = users.get(position);
        holder.tvuser.setText(user.getLogin());
        Picasso.get().load(user.getAvatarUrl()).into(holder.foto);

        holder.itemView.setOnClickListener(click -> {
            Intent intent = new Intent(click.getContext(), Detail.class);
            intent.putExtra("name", user.getName());
            intent.putExtra("username", user.getLogin());
            intent.putExtra("image", user.getAvatarUrl());
            intent.putExtra("bio", user.getBio());
            intent.putExtra("twitter", user.getTwitterUsername());
            click.getContext().startActivity(intent);
        });
    }
}
