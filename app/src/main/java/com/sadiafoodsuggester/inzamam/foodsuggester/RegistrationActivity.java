package com.sadiafoodsuggester.inzamam.foodsuggester;

import android.opengl.Visibility;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edmail, edpass, edname, edage, edheight, edweight;
    RadioButton rbmale, rbfemale;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        toolbar=(Toolbar) findViewById(R.id.regtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar=(ProgressBar)findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.GONE);
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void createProfileMethod(View v) {
        edmail=(EditText) findViewById(R.id.regnameEditText);
        edpass=(EditText) findViewById(R.id.regEmailEdittext);
        edname=(EditText) findViewById(R.id.regconatctEditText);
        edage=(EditText) findViewById(R.id.regageEditText);
        edheight=(EditText) findViewById(R.id.regheightEditText);
        edweight=(EditText) findViewById(R.id.regweightEditText);

        rbmale=(RadioButton) findViewById(R.id.regMaleRadioButton);
        rbfemale=(RadioButton) findViewById(R.id.regFemaleRadioButton);

        final String email=edmail.getText().toString();
        String password=edpass.getText().toString();
        final String username=edname.getText().toString();
        final String age=edage.getText().toString();
        final String height=edheight.getText().toString();
        final String weight=edweight.getText().toString();

        final String gender;
        if(rbmale.isChecked()){
            gender="Male";
        }else{
            gender="Female";

        }
        progressBar.setVisibility(View.VISIBLE);

           mAuth.createUserWithEmailAndPassword(email, username).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(RegistrationActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                       Users usersobj=new Users(email, username, age, height, weight, gender);
                       FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                               .setValue(usersobj).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    edname.setText("");
                                    edpass.setText("");
                                    edmail.setText("");
                                    edage.setText("");
                                    edheight.setText("");
                                    edweight.setText("");
                                }
                                else if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(RegistrationActivity.this, "User is Already Present", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(RegistrationActivity.this, "Data is not Added", Toast.LENGTH_SHORT).show();
                                }
                           }
                       });
                   }
                    else{
                       Toast.makeText(RegistrationActivity.this, "Registration Not Succeded", Toast.LENGTH_SHORT).show();
                   }
               }
           }) ;
    }

}
