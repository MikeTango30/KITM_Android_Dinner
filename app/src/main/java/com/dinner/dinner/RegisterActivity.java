package com.dinner.dinner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;

import static com.dinner.dinner.NewEntryActivity.INSERT_URL;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        final EditText name = findViewById(R.id.name);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        Button registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //čia rašomas kodas, kuris bus vykdomas ant mygtuko paspaudimo//
            String nameValue = name.getText().toString();
            String emailValue = email.getText().toString();
            String passwordValue = password.getText().toString();

            boolean validEmail = InputValidator.isEmailValid(emailValue);
            boolean validPassword = InputValidator.isCredentialsValid(passwordValue);
            // purge error logs
            email.setError(null);
            password.setError(null);
            if (!validEmail || !validPassword) {
                //show error
                email.setError(getResources().getString(R.string.register_invalid_email_message));
                password.setError(getResources().getString(R.string.register_invalid_password_message));
                email.requestFocus();
            }
            //----------------------------------------------------iš kur-------------į kur---------//
            if (validEmail && validPassword) {
                String passwordHash = BCrypt.hashpw(passwordValue, BCrypt.gensalt());;
                User user = new User(nameValue, emailValue, passwordHash);
                insertToDB(user);
//                Toast.makeText(RegisterActivity.this, "Welcome aboard!", Toast.LENGTH_SHORT).show();
//                Intent gotoSearchActivity = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(gotoSearchActivity);
            }
            }
        });

    }

    private void insertToDB(User user) {
        class Register extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            DB db = new DB();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this,

                        getResources().getString(R.string.new_entry_database_info),
                        null, true, true);

            }

            @Override
            protected String doInBackground(String... strings) {
                // Pirmas string yra raktas, antras - reiksmé.
                HashMap<String, String> userData = new HashMap<String, String>();
                userData.put("name", strings[0]);
                userData.put("password", strings[1]);
                userData.put("email", strings[2]);
                userData.put("actionUser", "insertUser");
                String result = db.sendPostRequest(INSERT_URL, userData);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
                Intent goToLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(goToLoginActivity);
            }
        }

        Register register = new Register();

        register.execute(
                user.getUsernameForRegistration(),
                user.getEmailForRegistration(),
                user.getPasswordForRegistration()
        );

    }

}

