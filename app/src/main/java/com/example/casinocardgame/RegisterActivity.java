package com.example.casinocardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.casinocardgame.API.ApiConnection;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    //variables
    ApiConnection apiConnection = ApiConnection.getInstance();

    //UI

    private EditText username;
    private EditText password;
    private EditText passwordRepeat;
    private Button register;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.editTextUsernameRegister);
        password = findViewById(R.id.editTextRegisterPassword);
        passwordRepeat = findViewById(R.id.editTextRegisterPasswordRepeat);
        register = findViewById(R.id.registerButton);
        register.setOnClickListener(this);
        cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == cancel){
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }

    }
}