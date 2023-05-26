package com.example.casinocardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.casinocardgame.API.ApiConnection;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables
    ApiConnection apiConnection = ApiConnection.getInstance();

    //UI
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.loginButton);
        login.setOnClickListener(this);
        register = findViewById(R.id.registerLoginButton);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == login){
            String usernameInput = username.getText().toString();
            String passwordInput = password.getText().toString();
        }
        if (view == register){
            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerIntent);
        }

    }
}