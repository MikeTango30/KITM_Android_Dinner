package com.dinner.dinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.login_btn);
        Button registerBtn = findViewById(R.id.register_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //čia rašomas kodas, kuris bus vykdomas ant mygtuko paspaudimo//
                String username2 = username.getText().toString();
                boolean validUserName = InputValidator.isCredentialsValid(username2);
                if (!validUserName) {
                    Toast.makeText(LoginActivity.this, "Username: " + username2 + " is not valid", Toast.LENGTH_SHORT).show();
                }
                String password2 = password.getText().toString();
                boolean validPassword = InputValidator.isCredentialsValid(password2);
                if (!validPassword) {
                    Toast.makeText(LoginActivity.this, "Password: " + password2 + " is not valid", Toast.LENGTH_SHORT).show();
                }
                //----------------------------------------------------iš kur-------------į kur---------//
                if (validUserName && validPassword) {
                    Toast.makeText(LoginActivity.this, "Welcome back, : " + username2, Toast.LENGTH_SHORT).show();
                    Intent gotoSearchActivity = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(gotoSearchActivity);
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegisterActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(gotoRegisterActivity);
            }
        });

    }

}