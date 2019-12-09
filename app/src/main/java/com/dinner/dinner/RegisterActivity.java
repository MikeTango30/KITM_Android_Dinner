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
            Toast.makeText(RegisterActivity.this, "Name: " + name2 + "\n" + "Email: "
                    + email2, Toast.LENGTH_SHORT).show();
            //----------------------------------------------------iš kur-------------į kur---------//
            Intent gotoLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(gotoLoginActivity);
            }
        });

    }

}

