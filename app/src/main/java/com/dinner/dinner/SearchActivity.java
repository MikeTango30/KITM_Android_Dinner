package com.dinner.dinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button newEntryBtn = findViewById(R.id.new_entry_btn);

        newEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoNewEntryActivity = new Intent(SearchActivity.this, NewEntryActivity.class);
                startActivity(gotoNewEntryActivity);
            }
        });
    }

}
