package com.project.upendoshop.Adapters;

import static com.project.upendoshop.Adapters.DBmain.TABLENAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.upendoshop.Models.CartModels;
import com.project.upendoshop.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<CartModels>list;
    SQLiteDatabase sqLiteDatabase;
    int cart_items;

    public CartAdapter(Context context, ArrayList<CartModels> list, SQLiteDatabase sqLiteDatabase, int cart_items) {
        this.context = context;
        this.list = list;
        this.sqLiteDatabase = sqLiteDatabase;
        this.cart_items = cart_items;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final CartModels cartModels = list.get(position);
        holder.item.setText(list.get(position).getTitle());
        holder.amount.setText(list.get(position).getAmount());
        holder.quantity.setText(list.get(position).getQuantity());
        holder.pcs.setText(list.get(position).getPcs());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBmain dBmain= new DBmain(context);
                sqLiteDatabase= dBmain.getReadableDatabase();
                long recdelete = sqLiteDatabase.delete(TABLENAME, "id="+cartModels.getId(),null);
                if (recdelete!= -1) {
                    Toast.makeText(context, "item successfully removed", Toast.LENGTH_SHORT).show();
                    list.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item,amount,quantity,pcs,remove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item= itemView.findViewById(R.id.prod_title);
            amount=itemView.findViewById(R.id.prod_amount1);
            quantity=itemView.findViewById(R.id.prod_quantity);
            pcs= itemView.findViewById(R.id.prod_pcs);
            remove= itemView.findViewById(R.id.remove_btn);
        }
    }
}
