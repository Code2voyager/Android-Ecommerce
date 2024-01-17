package com.example.ns_individualproj2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class cart_list extends AppCompatActivity {


   int totalamount;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutmanager;

    Button checkout;
    TextView carttotal;
    ImageView cart_i,logout,home;

    private String uid;
    FirebaseUser Userauth;
    ArrayList<Double> gettotalPricesArray = new ArrayList<>();

  List<cartItems> cart_values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        checkout = findViewById(R.id.btnCheckout);
        carttotal = findViewById(R.id.cart_total);
        cart_i = findViewById(R.id.cart_img);
        logout = findViewById(R.id.user_logout);
        home = findViewById(R.id.home_screen);





        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("MyTotalPrice"));


//        current user
        Userauth = FirebaseAuth.getInstance().getCurrentUser();
        uid = Userauth.getUid();

//        fetching cartitems


        DatabaseReference cart_ref = FirebaseDatabase.getInstance().getReference("Cart_data");
        cart_ref.child(uid).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot cart_snapshot) {
                for(DataSnapshot snapshot1 :cart_snapshot.getChildren()){
                    String User_key = snapshot1.getKey();
                    String PID = snapshot1.child("PID").getValue(String.class);
                    String Product_Name = snapshot1.child("product_name").getValue(String.class);
                    String Product_Img = snapshot1.child("product_image").getValue(String.class);
                    String Product_Size = snapshot1.child("product_size").getValue(String.class);
                    Double Product_Price = snapshot1.child("product_price").getValue(Double.class);
                    String Product_Quantity = snapshot1.child("product_quantity").getValue(String.class);



                    cartItems Items = new cartItems(PID,Product_Name,Product_Img,Product_Size,Product_Price,Product_Quantity);
                    cart_values.add(Items);



//            Log.i("fetchinfo","DB: "+cart_list.toString());


                }
                adapter.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView = findViewById(R.id.cartView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new cartAdapter(cart_values);
        recyclerView.setAdapter(adapter);



        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Initialize the Firebase Realtime Database


                cart_values.clear();

                Intent intent = new Intent(cart_list.this,Checkout.class);
                startActivity(intent);
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Product_recycler.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser Userauth = FirebaseAuth.getInstance().getCurrentUser();
                String  login_user = Userauth.getUid();

                if(login_user == null){
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
            }
        });

        cart_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),cart_list.class);
                startActivity(intent);
            }
        });
    }


    public BroadcastReceiver mMessageReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

                double total = intent.getDoubleExtra("totalprice",0.0);
                String quantity = intent.getStringExtra("quantity");
                Log.d("info","quanity" +total);
                carttotal.setText("$"+total);

            BroadcastManager.setClearBroadcasts(false);
        }
    };



}