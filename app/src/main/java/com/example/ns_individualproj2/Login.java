package com.example.ns_individualproj2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {


    EditText username,password;
    Button  loginbtn;
    FirebaseAuth LAuth;
    ProgressBar progbar;
    TextView reglink;
    ImageView image;



//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = LAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(Login.this,Product_recycler.class);
//            startActivity(intent);
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        loginbtn = findViewById(R.id.loginBtn);
//        progbar = findViewById(R.id.progressBar);
        reglink = findViewById(R.id.regLink);
        image = findViewById(R.id.cart_img);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,cart_list.class);
                startActivity(intent);

            }
        });



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username,Password;
                Username = String.valueOf(username.getText());
                Password = String.valueOf(password.getText());


                if(TextUtils.isEmpty(Username) || !isValidEmail(Username)){
                    Toast.makeText(Login.this,"Enter the Username",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(Password) || !isValidPassword(Password)){
                    Toast.makeText(Login.this,"Enter the Password",Toast.LENGTH_LONG).show();

                    return;
                }

                LAuth.signInWithEmailAndPassword(Username, Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progbar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, " Successfully LoggedIn",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this,Product_recycler.class);
                                    startActivity(intent);
                                    finish();
                                } else {

                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });















            }

            private boolean isValidPassword(String password) {
                return password.length() >= 8;
            }

            private boolean isValidEmail(String username) {
                String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
                return Pattern.compile(emailPattern).matcher(username).matches();
            }
        });

        reglink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),registeration.class);
                startActivity(intent);

            }
        });




    }
}