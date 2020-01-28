package com.dinner.dinner;

import android.content.Intent;
import android.widget.Spinner;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewEntryActivity extends AppCompatActivity {

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

                Toast.makeText(NewEntryActivity.this,
                        "Your order: " + dinner.getDinnerType() + "\n" +
                        "Delivery type: " + dinner.getDelivery() + "\n" +
                        "Price: " + dinner.getPrice() + "\n" +
                        "Payment Type: " + dinner.getPayment() + "\n",
                        Toast.LENGTH_SHORT).show();
            }
            //----------------------------------------------------iš kur-------------į kur---------//
//                if (validEmail) {
//                    Toast.makeText(RegisterActivity.this, "Welcome aboard!", Toast.LENGTH_SHORT).show();
//                    Intent gotoSearchActivity = new Intent(RegisterActivity.this, LoginActivity.class);
//                    startActivity(gotoSearchActivity);
//                }

        });

    }

}
