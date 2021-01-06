package com.example.spage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdopter extends RecyclerView.Adapter<CustomAdopter.MyviewHolder> {

    private Context context;
    private ArrayList id,name,amount ,qty;
    int position;




    CustomAdopter(Activity activity,Context context,ArrayList id , ArrayList name,ArrayList amount, ArrayList qty)
    {


        this.context=context;
        this.id=id;
        this.name=name;
        this.amount=amount;
        this.qty=qty;
    }



    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_row,parent,false);
        Intent intent = new Intent(context, update.class);

        return new MyviewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int position) {
        this.position=position;
        holder.id.setText(String.valueOf(id.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.amount.setText(String.valueOf(amount.get(position)));
        holder.qty.setText(String.valueOf(qty.get(position)));
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , update.class);
                 intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("name",String.valueOf(name.get(position)));
                intent.putExtra("amount",String.valueOf(amount.get(position)));
                intent.putExtra("qty",String.valueOf(qty.get(position)));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return id.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView id,name,amount,qty;
        LinearLayout mainlayout;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            id =itemView.findViewById(R.id.id);
            name =itemView.findViewById(R.id.name);
            amount =itemView.findViewById(R.id.amt);
            qty =itemView.findViewById(R.id.qty);
            mainlayout =itemView.findViewById(R.id.mainlaout);

        }
    }
}