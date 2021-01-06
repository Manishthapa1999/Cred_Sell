package com.example.spage;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {
    Context context;
    ArrayList cusname, cusphone, cusamount;

    CustomerAdapter(Context context, ArrayList cus_name, ArrayList cus_ph, ArrayList cus_amount) {
        this.context = context;
        this.cusname = cus_name;
        this.cusphone = cus_ph;
        this.cusamount = cus_amount;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mycardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.cus_name.setText(String.valueOf(cusname.get(position)));
        holder.cus_ph.setText(String.valueOf(cusphone.get(position)));
        holder.cus_amount.setText(String.valueOf(cusamount.get(position)));
        holder.rows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + cusphone.get(position)));

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                context.startActivity(callIntent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return cusname.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cus_name,cus_ph,cus_amount;
        LinearLayout rows;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cus_name=itemView.findViewById(R.id.name);
            cus_ph=itemView.findViewById(R.id.phone);
            cus_amount=itemView.findViewById(R.id.amt);
            rows=itemView.findViewById(R.id.rows);
        }
    }
}

