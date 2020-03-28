package com.example.socialalbum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loginCheck();
    }
    private void loginCheck(){
        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
