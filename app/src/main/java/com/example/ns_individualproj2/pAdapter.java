package com.example.ns_individualproj2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class pAdapter<S, S1> extends  RecyclerView.Adapter<pAdapter.MyViewHolder> {

   private static List<products> products;



    public pAdapter(List<products> prod) {

       products = prod;
    }


    @Override
    public pAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater,parent);
    }

    @Override
    public void onBindViewHolder( pAdapter.MyViewHolder holder, int position) {

       products p = products.get(position);


        Context mycontext = holder.itemView.getContext();

        int imgid = mycontext.getResources().getIdentifier(p.getImageurl(),"drawable",mycontext.getPackageName());


        holder.image.setImageResource(imgid);
        holder.title.setText(p.getName());

        Log.i("infoo","data" + holder);


        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Context context = view.getContext();

                Intent intent = new Intent(context, Product_details.class);
                intent.putExtra("clickedName",p.getName());
                intent.putExtra("clickedImg",p.getImageurl());
                intent.putExtra("clickeddesc",p.getDescription());
                intent.putExtra("clickedprice",p.getPrice());
                intent.putExtra("clickedquantity",p.getQuantity());
                intent.putExtra("pid",p.getPID());

                context.startActivity(intent);


            }
        });





    }

    @Override
    public int getItemCount() {


        return products.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder  {

        ImageView image;
        TextView title;
        CardView cardView;



        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.activity_products, parent, false));
            image = itemView.findViewById(R.id.prodimage);
            title = itemView.findViewById(R.id.prodtitle);
            cardView = itemView.findViewById(R.id.cardView);

        }















    }

}
