package com.dinner.dinner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class NewEntryActivity extends AppCompatActivity {

    public static final String INSERT_URL = "http://dinner-dinner.epizy.com/mobile/db.php";
    Dinner dinner;

    CheckBox checkSoup;
    CheckBox checkMainDish;
    CheckBox checkSalad;
    RadioGroup deliveryGroup;
    EditText price;
    Spinner payment;

    private RadioButton deliveryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        //check if new or existsting entry
        Intent intent = getIntent();
        dinner = (Dinner) intent.getSerializableExtra(AdapterDinner.ENTRY);

        if (dinner == null) { //new entry - values by default
            dinner = new Dinner(
                    -1, // not in db
                    getResources().getString(R.string.new_entry_dinner_type_main),
                    getResources().getString(R.string.new_entry_delivery_type_no_delivery),
                    0,
                    getResources().getStringArray(R.array.new_entry_payment_type).toString()
            );
        } else { // exixting entry, values by entry
            setDataFromEntry(dinner);
            // TODO impelement update and delete
        }

        setDataFromEntry(dinner);

        checkSoup = findViewById(R.id.soup);
        checkMainDish = findViewById(R.id.main);
        checkSalad = findViewById(R.id.salad);
        deliveryGroup = findViewById(R.id.new_entry_delivery_group);
        price = findViewById(R.id.new_entry_price);
        payment = findViewById(R.id.payment);

        Button createBtn = findViewById(R.id.new_entry_create_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Dinner dinnerFromForm = getDataFromForm();
                insertToDB(dinnerFromForm);

//                Toast.makeText(NewEntryActivity.this,
//                        "Dinner type: " + dinner.getDinnerType() + "\n" +
//                                "Delivery type: " + dinner.getDelivery() + "\n" +
//                                "Price: " + dinner.getPrice() + "\n" +
//                                "Payment: " + dinner.getPayment() + "\n",
//                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Dinner getDataFromForm() {
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

        return dinner;
    }

    private void setDataFromEntry(Dinner dinner) {

        boolean isChecked = false;
        if (dinner.getDinnerType().contains(getResources().getString(R.string.new_entry_dinner_type_soup))) {
            checkSoup.setChecked(true);
            isChecked = true;
        }
        if (dinner.getDinnerType().contains(getResources().getString(R.string.new_entry_dinner_type_main))) {
            checkMainDish.setChecked(true);
            isChecked = true;
        }
        if (dinner.getDinnerType().contains(getResources().getString(R.string.new_entry_dinner_type_salad))) {
            checkSalad.setChecked(true);
            isChecked = true;
        }
        if(!isChecked) {
            checkMainDish.setChecked(true);
        }

        if (!dinner.getDelivery().equalsIgnoreCase(getResources().getString(R.string.new_entry_delivery_type_no_delivery))) {
            ((RadioButton)deliveryGroup.getChildAt(0)).setChecked(true);
        } else {
            ((RadioButton)deliveryGroup.getChildAt(1)).setChecked(true);
        }

        price.setText(String.valueOf(dinner.getPrice()));

        //TODO spinner
//        if(dinner.getPayment().equalsIgnoreCase(getResources().getString(R.string.new_entry_payment_type))) {
//        payment.setSelection();
//        }

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



