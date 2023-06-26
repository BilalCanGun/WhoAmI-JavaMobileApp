package com.example.anketapp;

import android.annotation.SuppressLint;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment {

    private String userID;

    private TextView tvEmail;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private ImageView imageView;

    private YouTubePlayerView youTubePlayerView;






    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mAuth=FirebaseAuth.getInstance();

        tvEmail=(TextView) view.findViewById(R.id.tvEmail);
        tvEmail.setText(mAuth.getCurrentUser().getEmail());

        ImageView imageView =view.findViewById(R.id.imageView);

        youTubePlayerView=(YouTubePlayerView) view.findViewById(R.id.youTubePlayerView);






        return view;
    }



    }









