package com.example.shindig1;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;



public class Matching extends AppCompatActivity {

    List<Integer> lstImages= new ArrayList<>();


    private View.OnDragListener changepic = new View.OnDragListener(View,) {
        public void onClick(View v) {
            // do something when the button is clicked
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);


        initData();

        HorizontalInfiniteCycleViewPager pager=(HorizontalInfiniteCycleViewPager)findViewById(R.id.horizontal_cycle);

        MyAdapter adapter= new MyAdapter(lstImages,getBaseContext());

        pager.setAdapter(adapter);


        pager.setOnDragListener(new View.OnDragListener() );


    }

    private void initData(){
        lstImages.add(R.drawable.card);
        lstImages.add(R.drawable.card);
        lstImages.add(R.drawable.card);
    }



}
