package com.sadiafoodsuggester.inzamam.foodsuggester;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class AddFoodActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);
        toolbar=(Toolbar) findViewById(R.id.addfoodactivitytoolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Food Here");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
