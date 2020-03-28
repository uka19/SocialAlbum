package com.example.socialalbum;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText email,password;
    private Button login,signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findID();
        login_button();
        signin_button();
    }
    private void findID() {
        email = findViewById(R.id.email_edittext);
        password = findViewById(R.id.password_edittext);
        login = findViewById(R.id.login_button);
        signin = findViewById(R.id.signin_button);
    }
    private void login_button() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });
    }
    private void signin_button(){
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,sign_in.class));
            }
        });
    }
    public void onLogin() {
        final String email = this.email.getText().toString();
        final String password = this.password.getText().toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.LOGIN_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                try {
                    //Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    if(!jsonObject.getBoolean("error")) {
                        SharedPrefManager.getInstance(MainActivity.this).userLogin(
                                jsonObject.getString("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("surname"),
                                jsonObject.getString("email"),
                                jsonObject.getString("username"));
                        finish();
                        startActivity(new Intent(MainActivity.this, Home.class));
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("email", email);
                params.put("password",password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
