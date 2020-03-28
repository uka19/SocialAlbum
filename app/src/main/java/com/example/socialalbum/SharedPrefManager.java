package com.example.socialalbum;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_SURNAME = "surname";
    private static final String KEY_USER_EMAIL = "useremail";
    private static final String KEY_USER_IMAGE_URL = "imageurl";
    private  static final String KEY_USER_IMAGE = "image";

    private static SharedPrefManager mInstance;
    private static Context mCtx;



    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(String id, String name, String surname, String email, String username) {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_USER_ID, id);
        editor.putString(KEY_USER_NAME,name);
        editor.putString(KEY_USER_SURNAME,surname);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USERNAME, username);
        editor.commit();
        editor.apply();
        return true;
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME, null)!=null) {
            return true;
        }
        return false;
    }
    public boolean logout() {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
    public String getUserId(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_ID,null);
    }
    public String getUserName(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_NAME,null);
    }
    public String getUserSurname(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_SURNAME,null);
    }
    public String getUserEmail(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL,null);
    }
    public String getUsername() {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }


}
