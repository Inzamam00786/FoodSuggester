package com.sadiafoodsuggester.inzamam.foodsuggester;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{

 Toolbar toolbar;
 EditText edname, edpass;
 Button loginbtn;
 TextView regtv;
 public static final String emailtext="sadia@gmail.com";
 public static final String passtext="123456";
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar=(Toolbar) findViewById(R.id.logintoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login Here");
        loginbtn=(Button) findViewById(R.id.loginbtn);
        regtv=(TextView) findViewById(R.id.textView6);

        mAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(mAuth.getCurrentUser()!=null){
                    Toast.makeText(LoginActivity.this, "Register Yourself first", Toast.LENGTH_SHORT).show();
                }
            }
        };

        regtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegistrationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

    public void signInMethod(View view) {

        String email, password;
        edname=(EditText) findViewById(R.id.editText4);
        edpass=(EditText) findViewById(R.id.editText5);
        email=edname.getText().toString().trim();
        password=edpass.getText().toString().trim();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Enter Empty fields", Toast.LENGTH_SHORT).show();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(LoginActivity.this, "Enter a valid Email address", Toast.LENGTH_SHORT).show();
        }
        if(edname.getText().toString().equalsIgnoreCase(emailtext) && edpass.getText().toString().equalsIgnoreCase(passtext)){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            edname.setText("");
            edpass.setText("");
            finish();
        }

       /** mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
               if(task.isSuccessful()){
                   startActivity(new Intent(LoginActivity.this, MainActivity.class));

               }else{
                   Toast.makeText(LoginActivity.this, "Authentication is failed", Toast.LENGTH_SHORT).show();
               }
            }

        });**/
    }

}

