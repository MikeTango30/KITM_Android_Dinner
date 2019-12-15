package com.dinner.dinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        final EditText name = findViewById(R.id.name);
//        final EditText surname = findViewById(R.id.surname);
//        final EditText phone = findViewById(R.id.phone);
        final EditText email = findViewById(R.id.email);
        Button registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //čia rašomas kodas, kuris bus vykdomas ant mygtuko paspaudimo//
            String name2 = name.getText().toString();
            String email2 = email.getText().toString();
            boolean validEmail = InputValidator.isEmailValid(email2);
            // purge error logs
            email.setError(null);
            if (!validEmail) {
                //show error
                email.setError(getResources().getString(R.string.register_invalid_email_message));
                email.requestFocus();
            }
            //----------------------------------------------------iš kur-------------į kur---------//
            if (validEmail) {
                Toast.makeText(RegisterActivity.this, "Welcome aboard!", Toast.LENGTH_SHORT).show();
                Intent gotoSearchActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(gotoSearchActivity);
            }
            }
        });

    }

}

