package com.dinner.dinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final CheckBox rememberMe = findViewById(R.id.remember_me);
        Button loginBtn = findViewById(R.id.login_btn);
        Button registerBtn = findViewById(R.id.register_btn);

        final User user = new User(LoginActivity.this);

        rememberMe.setChecked(user.isRememberedForLogin());

        if (rememberMe.isChecked()) {
            username.setText(user.getUsernameForLogin(), TextView.BufferType.EDITABLE);
            password.setText(user.getPasswordForLogin(), TextView.BufferType.EDITABLE);
        } else {
            username.setText("", TextView.BufferType.EDITABLE);
            password.setText("", TextView.BufferType.EDITABLE);
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

                //----------------------------------------------------iš kur-------------į kur---------//
                if (validUserName && validPassword) {
                    user.setUsernameForLogin(username2);
                    user.setPasswordForLogin(password2);
                    if (rememberMe.isChecked()) {
                        user.setRememberMeKey(true);
                    } else {
                        user.setRememberMeKey(false);
                    }

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