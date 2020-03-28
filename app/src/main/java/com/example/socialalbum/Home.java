package com.example.socialalbum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Home extends AppCompatActivity {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bnv = findViewById(R.id.nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_home()).commit();
        bottomNavListener();
        loginCheck();
    }
    private void loginCheck(){
        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(this,MainActivity.class));
        }
    }
    private void bottomNavListener(){
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

                switch (menuItem.getItemId()){
                    case R.id.home:{
                        selectedFragment = new fragment_home();
                        break;
                    }
                    case R.id.myalbums:{
                        selectedFragment = new fragment_myalbums();
                        break;
                    }
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return  true;
            }
        });
    }
}
