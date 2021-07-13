package com.example.signuppage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Second extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    FirebaseAuth firebaseAuth;
    ProgressBar p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        e1=(EditText) findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText4);
        e2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        b1=(Button) findViewById(R.id.button3);
        b2=(Button) findViewById(R.id.button4);
        p1=(ProgressBar)findViewById(R.id.progressBar2);
        firebaseAuth=FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString().trim();
                String s2=e2.getText().toString();
                if (s1.isEmpty())
                {
                    e1.setError("Fill UserName");
                    return;
                }
                else
                {
                    if(s2.isEmpty())
                    {
                        e2.setError("Fill Password");
                        return;
                    }
                }
                p1.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                       if(task.isSuccessful())
                       {
                           Toast.makeText(Second.this, "Registor Done!", Toast.LENGTH_SHORT).show();
                           p1.setVisibility(View.INVISIBLE);
                           Intent i=new Intent(Second.this,MainActivity.class);
                           startActivity(i);
                           finish();
                       }
                       else
                       {
                           Toast.makeText(Second.this, "Already Exists", Toast.LENGTH_SHORT).show();
                           p1.setVisibility(View.INVISIBLE);
                           Intent j=new Intent(Second.this,MainActivity.class);
                           startActivity(j);
                           finish();
                       }
                    }
                });
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Second.this,MainActivity.class);
                startActivity(k);
                finish();
            }
        });
    }
}