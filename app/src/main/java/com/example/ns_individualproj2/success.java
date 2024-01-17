package com.example.ns_individualproj2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class success extends AppCompatActivity {


    ImageView cart,home;
    Button btn;
    FirebaseUser Userauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess);



        cart = findViewById(R.id.c_img);
        btn = findViewById(R.id.mainbtn);
        home = findViewById(R.id.home_screen);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference cartItemsRef = database.getReference("Cart_data");
                Userauth = FirebaseAuth.getInstance().getCurrentUser();
                String uid = Userauth.getUid();
                DatabaseReference itemToRemoveRef = cartItemsRef.child(uid);
                itemToRemoveRef.removeValue();
                Intent intent  = new Intent(success.this,cart_list.class);
                startActivity(intent);

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(success.this,Product_recycler.class);
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





    }
}