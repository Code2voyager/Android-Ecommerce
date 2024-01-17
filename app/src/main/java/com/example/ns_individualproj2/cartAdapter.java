package com.example.ns_individualproj2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.MyViewHolder> {


    private static List<cartItems> Cart_Products;

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser Userauth;
//    String uid = Userauth.getUid();

    double totalprice =0.0;
    int quantityvalue = 1;


    public cartAdapter(List<cartItems> cart_values) {
        Cart_Products = cart_values;

    }

    @NonNull
    @Override
    public cartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater,parent);
    }

    @Override
    public void onBindViewHolder( cartAdapter.MyViewHolder holder, int position) {
        BroadcastManager.setClearBroadcasts(false);
        cartItems Cart = Cart_Products.get(position);
        Context mycontext = holder.itemView.getContext();
        int imgid = mycontext.getResources().getIdentifier(Cart.getProduct_image(),"drawable",mycontext.getPackageName());

//        Double price = Cart.getProduct_price();
        holder.cartName.setText(Cart.getProduct_name());
        holder.cartImage.setImageResource(imgid);
        Double fpricing = Double.parseDouble(Cart.getProduct_quantity()) * Cart.getProduct_price();
        holder.cartPrice.setText("$"+String.valueOf(fpricing));
        holder.cartQuantity.setText(Cart.getProduct_quantity());





//

//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String pid = Cart.getPID();
//
//                database=FirebaseDatabase.getInstance();
//
//                myRef=database.getReference("Cart_data");
//                Userauth = FirebaseAuth.getInstance().getCurrentUser();
//              String uid = Userauth.getUid();
//                if(uid != null){
//                    myRef = FirebaseDatabase.getInstance().getReference("Cart_data");
//                    myRef.child(uid).child(pid).removeValue();
//                }
//                else{
//                    Intent intent = new Intent()
//                }
//
//                int position =   holder.getAdapterPosition();
//                Cart_Products.remove(position);
//                notifyItemRemoved(position);
//
//            }
//        });



//        passing the total price of the cart
//        totalPricesArray.clear();

        totalprice += Cart.getProduct_price() * Double.parseDouble(Cart.getProduct_quantity()) ;


//        totalPricesArray.add(totalprice);
        if(totalprice != 0){


        Log.i("totalamt", String.valueOf(totalprice));
        Intent intent = new Intent("MyTotalPrice");
        intent.putExtra("totalprice",totalprice);

        LocalBroadcastManager.getInstance(mycontext).sendBroadcast(intent);
        }
        else{
            Intent intent = new Intent("MyTotalPrice");
            intent.putExtra("totalprice",0);

            LocalBroadcastManager.getInstance(mycontext).sendBroadcast(intent);
        }



        holder.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantityvalue > 100){
                    quantityvalue = 100;
                    holder.cartQuantity.setText(String.valueOf(quantityvalue));

                    Double pricing = quantityvalue * Cart.getProduct_price();
                    holder.cartPrice.setText("$"+String.valueOf(pricing));
                    String priceText = holder.cartPrice.getText().toString();

                    totalprice += pricing;

                    Intent intent = new Intent("MyTotalPrice");
                    intent.putExtra("totalprice",totalprice);

                    LocalBroadcastManager.getInstance(mycontext).sendBroadcast(intent);
                   
//                    updateprice(pricing);


                }
                else if(quantityvalue >= 0 ){

                    quantityvalue ++;
                    holder.cartQuantity.setText(String.valueOf(quantityvalue));
                    Double pricing = quantityvalue * Cart.getProduct_price();
                    holder.cartPrice.setText("$"+String.valueOf(pricing));

//                    String priceText = holder.cartPrice.getText().toString();
//                    double price = Double.parseDouble(priceText);
                    totalprice += pricing;

                    Intent intent = new Intent("MyTotalPrice");
                    intent.putExtra("quantity",quantityvalue);
                    intent.putExtra("totalprice",totalprice);


                    LocalBroadcastManager.getInstance(mycontext).sendBroadcast(intent);
//                    updateQuantityInDatabase(quantityvalue);
//                    updateprice(pricing);

                }
            }

//            private void updateQuantityInDatabase(int quantityvalue) {
//                DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("Cart_data");
//
//                // Assuming you have a user UID for reference
//
//                if (uid != null) {
//                    cartRef.child(uid).child("product_quantity").setValue(quantityvalue)
//                            .addOnCompleteListener(task -> {
//                                if (task.isSuccessful()) {
//                                    Log.d("DatabaseUpdate", "Quantity updated successfully");
//                                } else {
//                                    Log.e("DatabaseUpdate", "Failed to update quantity: " + task.getException());
//                                }
//                            });
//                }
//            }


        });

        holder.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quanvalue = holder.cartQuantity.getText().toString();
                int quantity = Integer.parseInt(quanvalue);

                 if(quantity >1){
                     quantity--;

                    // Update the quantity in the UI
                    holder.cartQuantity.setText(String.valueOf(quantity));

                    // Calculate the new pricing and update the UI
                    Double pricing = quantity * Cart.getProduct_price();
                    holder.cartPrice.setText("$" + String.valueOf(pricing));

                    // Update the total price
                    totalprice -= Cart.getProduct_price();

                    // Log the updated total price
                    Log.i("totalprice", String.valueOf(totalprice));
//                    String quanvalue = holder.cartQuantity.getText().toString();
//                    int quantity = Integer.parseInt(quanvalue);
//                    // Update the quantity in the UI
//                    quantityvalue --;
//                    holder.cartQuantity.setText(String.valueOf(quantityvalue));
//                    Double pricing = quantityvalue * Cart.getProduct_price();
//                    holder.cartPrice.setText("$" + String.valueOf(pricing));
//
//                    totalprice -= pricing;
//                    Log.i("totalprice1", String.valueOf(totalprice));

                    // Send a broadcast to update the total price in the UI
                    Intent intent = new Intent("MyTotalPrice");
                    intent.putExtra("totalprice", totalprice);
                    LocalBroadcastManager.getInstance(mycontext).sendBroadcast(intent);



                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Cart_Products.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView cartName,cartPrice,cartQuantity,carttotal;
        ImageView cartImage;
        Button checkout;
        ImageView inc,dec, delete;
        MyViewHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.activity_add_to_cart,parent,false));
            cartName = itemView.findViewById(R.id.cartName);
            cartImage = itemView.findViewById(R.id.cartImage);
            cartPrice = itemView.findViewById(R.id.cartPrice);
            cartQuantity = itemView.findViewById(R.id.cartQuantity);
            checkout = itemView.findViewById(R.id.btnCheckout);
            inc = itemView.findViewById(R.id.cart_inc);
            dec = itemView.findViewById(R.id.cart_dec);
//            delete = itemView.findViewById(R.id.cart_delete);






//           checkout.setOnClickListener(this);
        }


    }



}
