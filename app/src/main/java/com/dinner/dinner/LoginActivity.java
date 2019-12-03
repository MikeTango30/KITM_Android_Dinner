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
                String password2 = password.getText().toString();
                Toast.makeText(LoginActivity.this, "Username: " + username2 + "\n" + "Password: "
                        + password2, Toast.LENGTH_SHORT).show();
                //----------------------------------------------------iš kur-------------į kur---------//
                Intent gotoSearchActivity = new Intent(LoginActivity.this, SearchActivity.class);
                startActivity(gotoSearchActivity);
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