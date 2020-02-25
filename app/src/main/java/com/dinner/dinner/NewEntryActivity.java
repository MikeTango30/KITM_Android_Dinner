package com.dinner.dinner;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.content.Intent;

import java.util.HashMap;

import android.widget.Spinner;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewEntryActivity extends AppCompatActivity {

    private static final String INSERT_URL = "http://dinner-dinner.epizy.com/mobile/db.php";

    private RadioButton deliveryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        final CheckBox checkSoup = findViewById(R.id.soup);
        final CheckBox checkMainDish = findViewById(R.id.main);
        final CheckBox checkSalad = findViewById(R.id.salad);
        final RadioGroup deliveryGroup = findViewById(R.id.new_entry_delivery_group);
        final EditText price = findViewById(R.id.new_entry_price);
        final Spinner payment = findViewById(R.id.payment);

        Button createBtn = findViewById(R.id.new_entry_create_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //get checkboxes input
                String dinnerTypes = "";
                if (checkSoup.isChecked()) {
                    dinnerTypes = dinnerTypes + checkSoup.getText().toString() + " ";
                }
                if (checkMainDish.isChecked()) {
                    dinnerTypes = dinnerTypes + checkMainDish.getText().toString() + " ";
                }
                if (checkSalad.isChecked()) {
                    dinnerTypes = dinnerTypes + checkSalad.getText().toString();
                }

                // get radio input
                int selectedId = deliveryGroup.getCheckedRadioButtonId();
                deliveryBtn = findViewById(selectedId);
                String deliveryType = deliveryBtn.getText().toString();

                //get price
                double priceValue = Double.parseDouble(price.getText().toString());

                // get payment
                String paymentValue = String.valueOf(payment.getSelectedItem());

                Dinner dinner = new Dinner(dinnerTypes, deliveryType, priceValue, paymentValue);
                insertToDB(dinner);

//                Toast.makeText(NewEntryActivity.this,
//                        "Dinner type: " + dinner.getDinnerType() + "\n" +
//                                "Delivery type: " + dinner.getDelivery() + "\n" +
//                                "Price: " + dinner.getPrice() + "\n" +
//                                "Payment: " + dinner.getPayment() + "\n",
//                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void insertToDB(Dinner dinner) {
        class NewEntry extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            DB db = new DB();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(NewEntryActivity.this,

                        getResources().getString(R.string.new_entry_database_info),
                        null, true, true);

            }

            @Override
            protected String doInBackground(String... strings) {
                // Pirmas string yra raktas, antras - reiksmé.
                HashMap<String, String> dinnerData = new HashMap<String, String>();
                dinnerData.put("dinner_type", strings[0]);
                dinnerData.put("delivery", strings[1]);
                dinnerData.put("price", strings[2]);
                dinnerData.put("payment", strings[3]);
                dinnerData.put("action", "insert");
                String result = db.sendPostRequest(INSERT_URL, dinnerData);


                return result;

            }

            @Override

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(NewEntryActivity.this, s, Toast.LENGTH_SHORT).show();
                Intent goToSearchActivity = new Intent(NewEntryActivity.this, SearchActivity.class);
                startActivity(goToSearchActivity);
            }
        }

        NewEntry newEntry = new NewEntry();

        newEntry.execute(
                dinner.getDinnerType(),
                dinner.getDelivery(),
                Double.toString(dinner.getPrice()),
                dinner.getPayment()
                );

    }
}



