package com.dinner.dinner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

        final SharedPreferences preferences = getApplicationContext().getSharedPreferences("RememberMe", 0);
        final SharedPreferences.Editor editor = preferences.edit();
        final CheckBox rememberMe = findViewById(R.id.remember_me);

        if(preferences.contains("Login")) {
            username.setText(preferences.getString("Login", null));
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //čia rašomas kodas, kuris bus vykdomas ant mygtuko paspaudimo//
                String username2 = username.getText().toString();
                String password2 = password.getText().toString();
                boolean validUserName = InputValidator.isCredentialsValid(username2);
                boolean validPassword = InputValidator.isCredentialsValid(password2);
                // purge error logs
                username.setError(null);
                password.setError(null);
                if (!validPassword || !validUserName) {
                    //show error
                    username.setError(getResources().getString(R.string.login_invalid_credentials_message));
                    username.requestFocus();
                }
               if (rememberMe.isChecked()) {
                   editor.putString("Login", username2);
                   editor.apply();
               } else {
                   editor.putString("Login", "");
                   editor.apply();
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

//TODO email regex