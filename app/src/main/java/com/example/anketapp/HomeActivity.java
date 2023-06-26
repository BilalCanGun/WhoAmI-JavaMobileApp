package com.example.anketapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    private String userID;

    private DrawerLayout mDrawer;
    private NavigationView mNav;
    private Toolbar customToolbar;
    private ActionBarDrawerToggle mToggle;

    private HomeFragment homeFragment;
    private TestFragment testFragment;

    private TextView tvUserName;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle = getIntent().getExtras();
        userID=bundle.getString("userID");

        mDrawer = (DrawerLayout) this.findViewById(R.id.home_drawerLayout);
        mNav = (NavigationView) this.findViewById(R.id.navigationView);
        customToolbar = (Toolbar) this.findViewById(R.id.customToolbar);

        setSupportActionBar(customToolbar);
        mNav.setItemIconTintList(null);

        mToggle = new ActionBarDrawerToggle(this, mDrawer, customToolbar, R.string.nav_open, R.string.nav_close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();

        homeFragment = new HomeFragment();

        setFragment(homeFragment,"");

        tvUserName=(TextView) mNav.getHeaderView(0).findViewById(R.id.tvK_Adi);

        mAuth=FirebaseAuth.getInstance();

        tvUserName.setText(mAuth.getCurrentUser().getEmail());

        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mProfile:
                        setFragment(homeFragment,userID);
                        mDrawer.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.test1:
                        testFragment = new TestFragment();
                        setFragment(testFragment,"Ne kadar komiksin?");
                        mDrawer.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.test2:
                        testFragment = new TestFragment();
                        setFragment(testFragment,"Ne kadar Umutlusun?");
                        mDrawer.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.test3:
                        testFragment = new TestFragment();
                        setFragment(testFragment,"Ne kadar kendini geliştiriyorsun?");
                        mDrawer.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.test4:
                        testFragment = new TestFragment();
                        setFragment(testFragment,"Ne kadar streslisin?");
                        mDrawer.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.test5:
                        testFragment = new TestFragment();
                        setFragment(testFragment,"Ne kadar Güvensizsin?");
                        mDrawer.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.mLogout:
                        Intent logout = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(logout);
                        finish();
                        mDrawer.closeDrawer(GravityCompat.START);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment,String arg) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!TextUtils.isEmpty(arg)){
            Bundle bnd=new Bundle();
            bnd.putString("key",arg);
            fragment.setArguments(bnd);
        }
        transaction.replace(R.id.home_frameLayout, fragment);
        transaction.commit();
    }


}