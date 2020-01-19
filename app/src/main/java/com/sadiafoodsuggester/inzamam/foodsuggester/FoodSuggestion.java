package com.sadiafoodsuggester.inzamam.foodsuggester;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodSuggestion extends AppCompatActivity {
    Toolbar toolbar;
    FoodSuggestionAdapter adapter;
    List<ValuesList> valuesListList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_suggestion);
        toolbar=(Toolbar) findViewById(R.id.foodsuggestiontoolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Food Suggestion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView) findViewById(R.id.myrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        valuesListList=new ArrayList<>();

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("products");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               if(dataSnapshot.exists()){
                   for(DataSnapshot foodsnap: dataSnapshot.getChildren()){
                       ValuesList foods=foodsnap.getValue(ValuesList.class);
                       valuesListList.add(foods);
                   }
                   adapter=new FoodSuggestionAdapter(FoodSuggestion.this,valuesListList);
                   recyclerView.setAdapter(adapter);
               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
