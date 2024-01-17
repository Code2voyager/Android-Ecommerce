package com.example.ns_individualproj2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Product_recycler extends AppCompatActivity {



    ImageView ucart ,ulogout;

    RecyclerView recyclerView;
    RecyclerView.Adapter prodAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<products> myproductList = new ArrayList<>();
    product_singleton singleton = product_singleton.getProdsingleton();

//    products myproductList = new products();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_recycler);

        ucart = findViewById(R.id.user_cart);
        ulogout = findViewById(R.id.user_logout);

        myproductList = singleton.getProductList();

        recyclerView = findViewById(R.id.reView);
        prodAdapter = new pAdapter(myproductList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(prodAdapter);


//    fetch data from firebase
        DatabaseReference db_ref = FirebaseDatabase.getInstance().getReference("Product_data");
        db_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dbsnapshot) {
                myproductList.clear();

                for (DataSnapshot snapshot : dbsnapshot.getChildren()) {

                    String pid = snapshot.child("pid").getValue(String.class);
                    String image = snapshot.child("imageurl").getValue(String.class);
                    Double price = snapshot.child("price").getValue(double.class);
                    String name = snapshot.child("name").getValue(String.class);
                    String desc = snapshot.child("description").getValue(String.class);
                    String quantity = snapshot.child("quantity").getValue(String.class);
                    products item = new products(pid,name,image,desc,price, quantity);
                    myproductList.add(item);

                    Log.i("Infooo","Snap" + pid);

                }
                prodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ucart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),cart_list.class);
                startActivity(intent);
            }
        });


        ulogout.setOnClickListener(new View.OnClickListener() {
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



    }












}