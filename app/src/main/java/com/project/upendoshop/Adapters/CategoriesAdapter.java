package com.project.upendoshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.upendoshop.Customer.MainActivity;
import com.project.upendoshop.Models.CategoriesModel;
import com.project.upendoshop.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    public CategoriesAdapter(Context context, ArrayList<CategoriesModel> list, MainActivity mainActivity) {
        this.context = context;
        this.list = list;
    }

    Context context;
    ArrayList<CategoriesModel>list;

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(list.get(position).getImage());
        holder.title.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image= itemView.findViewById(R.id.image_recipe);
            title= itemView.findViewById(R.id.text_recipe);
        }
    }
}
