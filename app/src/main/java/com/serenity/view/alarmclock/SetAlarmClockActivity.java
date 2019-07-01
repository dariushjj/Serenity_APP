package com.serenity.view.alarmclock;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.serenityapp.R;

import java.util.ArrayList;
import java.util.List;
//main alarm show
public class SetAlarmClockActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();
    private Boolean b_sub_square0 = false;
    private Boolean b_sub_square1 = false;
    private Boolean b_sub_square2 = false;
    private Boolean b_sub_square3 = false;
    private Boolean b_sub_square4 = false;
    private Boolean b_sub_square5 = false;
    private Boolean b_sub_square6 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmclock);
        final String[] array3 = new String[]{"男","女"};

        ActionBar actionBar = getSupportActionBar();
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList,this);
        recyclerView.setAdapter(adapter);

        if(actionBar != null){
            actionBar.hide();
        }
        Button addButton = (Button)findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SetAlarmClockActivity.this,AlarmClockActivity.class);
                startActivity(intent);
            }
        });
    }



    private void initFruits() {
            Fruit apple = new Fruit("Apple",true,false,true,true,false,true,false,false);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana",false,true,false,true,true,true,false,false);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange",true,true,true,true,true,true,false,true);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon",true,true,true,true,true,true,false,true);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear",true,true,true,true,true,true,false,true);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape",true,true,true,true,true,true,false,false);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple",true,true,true,true,true,true,false,true);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry",true,true,true,true,true,true,false,true);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry",true,true,true,true,true,true,false,false);
            fruitList.add(cherry);
            fruitList.remove(apple);
            //Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
            //fruitList.add(mango);

    }

    }
