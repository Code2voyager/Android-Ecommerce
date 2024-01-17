package com.example.ns_individualproj2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Checkout extends AppCompatActivity {


    Button proceed;
    EditText fName,email,address,phoneNo,zipCode,cardName,cardNo,CCV,cexpiry;
    Spinner Country;
    RadioButton option1,option2;
    RadioGroup radio;
    LinearLayout radio_details;
    ImageView cart;

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser Userauth;

    String country;

    List<cartItems> cartvalues = new ArrayList<>();

    private String[] countries = {"Select Country", "USA", "Canada", "UK", "Australia","India"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        cart = findViewById(R.id.cart_icon);
        proceed = findViewById(R.id.proceedbtn);
        fName = findViewById(R.id.edit_Fname);
        email = findViewById(R.id.edit_Email);
        address = findViewById(R.id.edit_Address);
        phoneNo = findViewById(R.id.edit_Phoneno);
        zipCode = findViewById(R.id.edit_zipCode);
        Country = findViewById(R.id.edit_country);
        cardName = findViewById(R.id.edit_cardName);
        cardNo = findViewById(R.id.edit_cardNo);
        CCV = findViewById(R.id.edit_CCV);
        option1 = findViewById(R.id.edit_card);
        option2 = findViewById(R.id.edit_cod);
        radio = findViewById(R.id.radio_chk);
        radio_details = findViewById(R.id.card_details);
        cexpiry = findViewById(R.id.edit_cardexpiry);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Country.setAdapter(adapter);
        Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = (String) parent.getItemAtPosition(position);
                if (!selectedCountry.equals("Select Country")) {
                    country = selectedCountry;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.edit_card){
                    radio_details.setVisibility(View.VISIBLE);



                }else if(checkedId == R.id.edit_cod){
                    radio_details.setVisibility(View.GONE);

                }

            }
        });
//    checkout button
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String full_name = fName.getText().toString();
                String email_id = email.getText().toString();
                String phone_no = phoneNo.getText().toString();
                String add = address.getText().toString();
                String zip = zipCode.getText().toString();
                String card_name = cardName.getText().toString();
                String card_no = cardNo.getText().toString();
                String ccv = CCV.getText().toString();
                String expiry = cexpiry.getText().toString();

                database =FirebaseDatabase.getInstance();
                myRef = database.getReference("Orders");
                Userauth = FirebaseAuth.getInstance().getCurrentUser();
                String uid = Userauth.getUid();

                if(radio.getCheckedRadioButtonId() == R.id.edit_card){
                    String payment = "credit";

                    boolean radio_result = validateradio(card_name,card_no,expiry,ccv);
                    boolean result =  validatedata(full_name,phone_no,email_id,add,zip);




                    if(result ==true && radio_result == true){

                        if(uid != null){
                            orders order = new orders(uid,full_name,phone_no,email_id,add,zip,country,payment,card_name,card_no,ccv,expiry);


                            myRef.child(uid).push().setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Log.d("infodb", "Inserted");
                                }
                            });
                            Intent intent = new Intent(Checkout.this, success.class);
                            startActivity(intent);
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Sorry Check Information Again",Toast.LENGTH_LONG).show();
                    }
                }else if(radio.getCheckedRadioButtonId() == R.id.edit_cod){


                    boolean result =  validatedata(full_name,phone_no,email_id,add,zip);
                    String payment = "cod";
//                    String name = cardName.setText("");

                    if(result ==true ){

                        if(uid !=null){
                            orders order = new orders(uid,full_name,phone_no,email_id,add,zip,country,payment,card_name,card_no,ccv,expiry);

                            myRef.child(uid).push().setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Log.d("infodb", "Inserted");
                                }
                            });
                        }

                        Intent intent = new Intent(Checkout.this, success.class);
                        startActivity(intent);
                    }

                    }else{
                        Toast.makeText(getApplicationContext(),"Sorry Check Information Again",Toast.LENGTH_LONG).show();
                    }

                DatabaseReference cartref = FirebaseDatabase.getInstance().getReference("Cart_data").child(uid);
                cartref.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i("removed ","Successful remove");
                    }
                });

            }

            private boolean validateradio(String card_name, String card_no, String expiry, String ccv) {

                if (card_name.length() == 0) {
                    cardName.requestFocus();
                    cardName.setError("Cardholder Name cannot be empty");
                    return false;
                } else if (!card_name.matches("^[A-Za-z ]+$")) {
                    cardName.requestFocus();
                    cardName.setError("Please enter a valid Cardholder Name");
                    return false;
                } else if (card_no.length() == 0 || !card_no.matches("^(\\d{13,19})$")) {
                    cardNo.requestFocus();
                    cardNo.setError("Please enter a valid Card Number");
                    return false;
                }
                else if(expiry.length() == 0 || !expiry.matches("^(0[1-9]|1[0-2])\\/?([0-9]{2}|[0-9]{4})$"))
                {
                    cexpiry.requestFocus();
                    cexpiry.setError("Please enter the expiry date in MM/YYYY or MM/YY Format ");
                    return false;
                }
                else if (ccv.length() == 0 || !ccv.matches("^[0-9]{3}$")) {
                    CCV.requestFocus();
                    CCV.setError("Please enter a valid CCV");
                    return false;
                }
                else{
                    return true;
                }
            }

            private boolean validatedata(String full_name, String phone_no,String email_id,String add,String zip) {
                if(full_name.length() == 0 ){
                    fName.requestFocus();
                    fName.setError("This Field Cannot Be Empty!!");
                    return false;
                }else if(!full_name.matches("^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")){
                    fName.requestFocus();
                    fName.setError("Please Enter Correct Name");
                    return false;

                }else if(phone_no.length() == 0){
                    phoneNo.requestFocus();
                    phoneNo.setError("This Field Cannot Be Empty!!");
                    return false;
                }
                else if(!phone_no.matches("^\\+(?:\\d{1,4})?[\\s-]?\\(?(?:\\d{1,4})\\)?[\\s-]?\\d{1,6}[\\s-]?\\d{1,6}[\\s-]?\\d{1,6}$")){
                    phoneNo.requestFocus();
                    phoneNo.setError("Please Enter Correct Format  +1 999-999-9999");
                    return false;

                } else if(email_id.length() == 0){
                    email.requestFocus();
                    email.setError("This Field Cannot Be Empty!!");
                    return false;
                }
                else if(!email_id.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
                    email.requestFocus();
                    email.setError("Please Enter Valid  Email");
                    return false;

                }else if(add.length() == 0){
                    address.requestFocus();
                    address.setError("This Field Cannot Be Empty!!");
                    return false;
                }
                else if(!add.matches("^[A-Za-z0-9\\s.,'-]+$")){
                    address.requestFocus();
                    address.setError("Please Enter Valid  Adresss");
                    return false;
                }else if(zip.length() == 0){
                    zipCode.requestFocus();
                    zipCode.setError("This Field Cannot Be Empty!!");
                    return false;
                }
                else if(!zip.matches("^[a-zA-Z0-9]{6}$")){
                    zipCode.requestFocus();
                    zipCode.setError("Please Enter Valid  ZipCode");
                    return false;
                }
                else {
                    return  true;
                }
            }




//






        });



    cart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(),cart_list.class);
            startActivity(intent);
        }
    });








    }
}