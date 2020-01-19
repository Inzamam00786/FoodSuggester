package com.sadiafoodsuggester.inzamam.foodsuggester;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class FoodDiary extends AppCompatActivity {
    Toolbar toolbar;
    Button addfoodbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_diary);
        toolbar=(Toolbar) findViewById(R.id.fooddiarytoolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Food Diary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addfoodbtn=(Button) findViewById(R.id.addfoodbtn);
        addfoodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FoodDiary.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });

    }
}
