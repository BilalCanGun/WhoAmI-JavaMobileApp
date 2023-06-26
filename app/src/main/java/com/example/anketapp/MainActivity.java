package com.example.anketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;


import android.Manifest;

import android.location.Address;

import android.location.Location;



import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


import com.google.android.gms.tasks.OnFailureListener;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private EditText editText_Mail,editText_Password;
    private String txtEmail,txtPassword;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView lattitude,longitude,address,city,country;
    Button getLocation;
    private final static int REQUEST_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_Mail=(EditText) findViewById(R.id.editText_Mail);
        editText_Password=(EditText) findViewById(R.id.editText_Password);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        country = findViewById(R.id.country);
        getLocation = findViewById(R.id.getLocation);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLastLocation();

            }
        });

    }
    public void giris(View view){

        txtEmail=editText_Mail.getText().toString();
        txtPassword=editText_Password.getText().toString();

        if (!TextUtils.isEmpty(txtEmail)&&!TextUtils.isEmpty(txtPassword))
        {
            mAuth.signInWithEmailAndPassword(txtEmail,txtPassword)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            mUser=mAuth.getCurrentUser();
                            System.out.println(mUser.getUid());
                            Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                            home.putExtra("userID", mUser.getUid());
                            startActivity(home);
                            finish();
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Kullanıcı Bulunamadı!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else
        {
            Toast.makeText(MainActivity.this, "EMAİL ŞİFRE BOŞ OLAMAZ", Toast.LENGTH_SHORT).show();
        }

    }

    public void hesabimyok(View view){
        Intent intent = new Intent(MainActivity.this,KayitOl.class);
        startActivity(intent);
    }
    private void getLastLocation(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){


            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                            if (location != null){



                                try {
                                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                                    country.setText("Country: "+addresses.get(0).getCountryName());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }

                        }
                    });


        }else {

            askPermission();


        }



    }
    private void askPermission() {

        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {

        if (requestCode == REQUEST_CODE){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){


                getLastLocation();

            }else {


                Toast.makeText(MainActivity.this,"Please provide the required permission",Toast.LENGTH_SHORT).show();

            }



        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
