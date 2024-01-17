package com.example.ns_individualproj2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Product_details extends AppCompatActivity {


    ImageView image,inc,dec;
    TextView text ,desc,price,quan;
    Button addtocart;
    Spinner size;
    int quantityvalue = 1;
    String quantity;


    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser Userauth;

    int imgid;
    String name,descp,img, uid,pid;
    String size_selected;
    Double amt;
    ImageView cartimg,logout,home;
ArrayList<cartItems> cartprod = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


         image = findViewById(R.id.productimage);
         text = findViewById(R.id.prodName);
         desc = findViewById(R.id.description);
         price = findViewById(R.id.price);
         quan = findViewById(R.id.quantityTextView);
         inc =findViewById(R.id.increase);
         dec = findViewById(R.id.decrease);
         addtocart = findViewById(R.id.btnAddToCart);
        size = findViewById(R.id.prod_size);
        cartimg = findViewById(R.id.cart_img);
        logout = findViewById(R.id.user_logout);
        home = findViewById(R.id.home_screen);




        Intent detailintent = getIntent();
     name =   detailintent.getStringExtra("clickedName");
      img = detailintent.getStringExtra("clickedImg");
      descp = detailintent.getStringExtra("clickeddesc");
      amt =  detailintent.getDoubleExtra("clickedprice",1);
    pid =  detailintent.getStringExtra("pid");


        text.setText(name);
        desc.setText(descp);
        price.setText("$"+ String.valueOf(amt));

         imgid = getResources().getIdentifier(img,"drawable",getPackageName());
        image.setImageResource(imgid);


    size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @SuppressLint("SuspiciousIndentation")
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        size_selected =    adapterView.getItemAtPosition(position).toString();
            Log.i("size",size_selected);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
        ArrayList<String> sizearray = new ArrayList<>();
        sizearray.add("S");
        sizearray.add("M");
        sizearray.add("L");
        sizearray.add("XL");
        ArrayAdapter<String> sizeadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,sizearray);
        sizeadapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        size.setAdapter(sizeadapter);


//    increase and decrease of quantity
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantityvalue > 100){
                    quantityvalue = 100;
                    quan.setText(String.valueOf(quantityvalue));


                }
                else if(quantityvalue >= 0 ){

                    quantityvalue += 1;
                    quan.setText(String.valueOf(quantityvalue));

                }
            }
        });


        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantityvalue < 0){
                    quantityvalue = 0;
                    quan.setText(String.valueOf(quantityvalue));

                }else if(quantityvalue >= 1){
                quantityvalue -= 1;
                quan.setText(String.valueOf(quantityvalue));
                }
            }
        });


//     add to cart

        addtocart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("AddToCart","Inside Addto cart");


                String quantity = quan.getText().toString();

                cartItems cart = new cartItems(pid,name,img,size_selected,amt,quantity);

//                DatabaseReference db_id = database.getReference("Cart_data");
//            String product_id = db_id.child("pid").get().toString();
// Log.d("cartvalues", product_id);
//

                database=FirebaseDatabase.getInstance();

            myRef=database.getReference("Cart_data");
//
//


            Userauth = FirebaseAuth.getInstance().getCurrentUser();
            uid = Userauth.getUid();
// userc currentid

                if(uid != null) {

                        myRef.child(uid).push().setValue(cart).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("infodb", "Inserted");
                            }
                        });


                    Intent intent = new Intent(Product_details.this, cart_list.class);
                    startActivity(intent);
                finish();
                }
                else if(uid == null){
                    Intent intent = new Intent(Product_details.this, Login.class);
                    startActivity(intent);
                }














            }








        });





//cart
        cartimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),cart_list.class);
                startActivity(intent);
            }
        });


//    logout
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



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Product_recycler.class);
                startActivity(intent);
            }
        });










    }






}