package com.sadiafoodsuggester.inzamam.foodsuggester;

import android.content.Intent;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button btnfooddiary;
    Button btnfoodsuggest;
    RelativeLayout relativeLayout;
    PieChart pieChart;
    private float[] yData={2275f};
    private String[] xData={"calories"};
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout=(RelativeLayout) findViewById(R.id.piechartlayout);
        pieChart=(PieChart) findViewById(R.id.mypiechart);
        pieChart.setRotationEnabled(false);
        pieChart.setHoleRadius(60f);
        pieChart.setTransparentCircleAlpha(2);
        pieChart.setCenterText("Consumed = ");
        pieChart.setCenterTextSize(15);
        pieChart.setDrawEntryLabels(false);

        /*pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d("inzy", e.toString());
                Log.d("inzy", h.toString());

                int pos=e.toString().indexOf("Food Meterial");
                String foodingre=e.toString().substring(pos);

            }


            @Override
            public void onNothingSelected() {

            }
        });*/

        addDataIntoPieChartMethod();

        toolbar=(Toolbar) findViewById(R.id.mainavtivitytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Activity");
        //getSupportActionBar().setSubtitle("XXXXXXX");

        btnfooddiary=(Button) findViewById(R.id.fooddiarybtn);
        btnfoodsuggest=(Button) findViewById(R.id.foodsuggestionbtn);
        btnfooddiary.setOnClickListener(this);
        btnfoodsuggest.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivitymenufile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg="Default String";
        switch (item.getItemId()){
            case R.id.profile:
                Intent intent=new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.search:
                msg="Search is Clicked";
                break;
            case R.id.logout:
                msg="Logout is Clicked";
                break;
        }
        Toast.makeText(MainActivity.this, msg+"", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if(view.getId()==R.id.fooddiarybtn){
            intent=new Intent(MainActivity.this, FoodDiary.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.foodsuggestionbtn){
            intent=new Intent(MainActivity.this, FoodSuggestion.class);
            startActivity(intent);
        }
        }
        public void addDataIntoPieChartMethod(){
            Log.d("inzy", "addDataIntoPieChartMethod");
            ArrayList<PieEntry> yentry=new ArrayList<>();
            ArrayList<String> xentry=new ArrayList<>();
            for(int i=0; i<yData.length; i++){
                yentry.add(new PieEntry(yData[i], i));
            }
            for(int i=0; i<xData.length; i++){
                xentry.add(xData[i]);
            }
            //Create a DataSet
            PieDataSet pieDataSet=new PieDataSet(yentry, "Required Chalories");
            pieDataSet.setSliceSpace(5);
            pieDataSet.setValueTextSize(13);

            //Adding Colors
            ArrayList<Integer> colors=new ArrayList<>();
            colors.add(Color.MAGENTA);
            pieDataSet.setColors(colors);

            //Adding a Legend
            Legend l=pieChart.getLegend();
            l.setForm(Legend.LegendForm.CIRCLE);
            //Setting Data

            PieData pieData=new PieData(pieDataSet);
            pieChart.setData(pieData);
            pieChart.invalidate();
        }
    }
