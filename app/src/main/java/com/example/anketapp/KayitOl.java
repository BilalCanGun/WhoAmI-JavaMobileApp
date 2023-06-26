package com.example.anketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class KayitOl extends AppCompatActivity {

    private EditText editTextKayitEmail,editTextKayitPassword;
    private String txtEmail,txtPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        editTextKayitEmail= (EditText) findViewById(R.id.editTextKayitEmail);
        editTextKayitPassword= (EditText) findViewById(R.id.editTextKayitPassword);

        mAuth=FirebaseAuth.getInstance();
    }
    public void anasayfa(View v){
        Intent intent = new Intent(KayitOl.this,MainActivity.class);
        startActivity(intent);
    }
    public void kayit(View v){
        txtEmail=editTextKayitEmail.getText().toString();
        txtPassword=editTextKayitPassword.getText().toString();
        if (!TextUtils.isEmpty(txtEmail)&& !TextUtils.isEmpty(txtPassword)){

            mAuth.createUserWithEmailAndPassword(txtEmail,txtPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                                Toast.makeText(KayitOl.this, "KAYIT İŞLEMİ BAŞARILI AFERİM", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(KayitOl.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }
        else
        {
            Toast.makeText(this, "EMAİL VE ŞİFRE BOŞ OLAMAZ", Toast.LENGTH_SHORT).show();
        }



    }



}