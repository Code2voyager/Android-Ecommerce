package com.example.ns_individualproj2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registeration extends AppCompatActivity {

    EditText regemail,regpassword;
    Button regbtn;
    FirebaseAuth RAuth;
    ProgressBar progbar;
    TextView loginlink;
    ImageView image;

    DatabaseReference regref;
    FirebaseDatabase db;

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = RAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(registeration.this,Product_recycler.class);
//            startActivity(intent);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);


        regemail = findViewById(R.id.regEmail);
        regpassword = findViewById(R.id.regPassword);
        regbtn = findViewById(R.id.regBtn);
//        progbar = findViewById(R.id.progressBar);
        loginlink = findViewById(R.id.loginLink);
        image = findViewById(R.id.reg_img);
        RAuth= FirebaseAuth.getInstance();
       db = FirebaseDatabase.getInstance();
       regref = db.getReference("Product_Users");




        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registeration.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progbar.setVisibility(View.VISIBLE);
                String Email,Password;
                Email = String.valueOf(regemail.getText());
                Password = String.valueOf(regpassword.getText());

                RAuth = FirebaseAuth.getInstance();
                if(TextUtils.isEmpty(Email) || !isValidEmail(Email)){


                    Toast.makeText(registeration.this,"Enter the Username",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(Password)|| !isValidPassword(Password)){
                    Toast.makeText(registeration.this,"Enter the Password",Toast.LENGTH_LONG).show();

                    return;
                }

//                register authentication
                RAuth.createUserWithEmailAndPassword(Email,Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                account acc = new account(Email,Password);

                                if (task.isSuccessful()) {
//                                    progbar.setVisibility(View.GONE);
                                    Toast.makeText(registeration.this, "Register Successfully.",
                                            Toast.LENGTH_SHORT).show();
                                        regref.child(RAuth.getCurrentUser().getUid()).setValue(acc).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Intent intent = new Intent(registeration.this, Login.class);
                                                startActivity(intent);
                                            }
                                        });

                                } else {
                                    // If sign in fails, display a message to the user.
//                                    progbar.setVisibility(View.VISIBLE);
                                    Toast.makeText(registeration.this, "Register failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }

            private boolean isValidPassword(String password) {

                if(password.length() >= 8){
                    return true;

                }else {
                    Toast.makeText(registeration.this,"Enter the 8 character Password",Toast.LENGTH_LONG).show();

                    return false;
                }
            }

            private boolean isValidEmail(String email) {
                String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
                if(Pattern.compile(emailPattern).matcher(email).matches()){
                    return true;

                }else {
                    Toast.makeText(registeration.this,"Enter the correct Email ",Toast.LENGTH_LONG).show();

                    return false;
                }


            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registeration.this,cart_list.class);
                startActivity(intent);
            }
        });






    }




}