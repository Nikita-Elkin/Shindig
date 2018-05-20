package com.example.shindig1;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.gigamole.infinitecycleviewpager.OnInfiniteCyclePageTransformListener;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flipview.FlipView;
import android.os.Handler;


public class Matching extends AppCompatActivity {

    List<Integer> lstCards= new ArrayList<>();
    public static List<Integer> lstMatches=new ArrayList<>();

    Button letsLunch;
    Button maybeLater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);


        initCards();
        initMatches();

        letsLunch=(Button) findViewById(R.id.letsLunchBtn);
        maybeLater=(Button) findViewById(R.id.maybeLaterBtn);


        final FlipView flipView = (FlipView) findViewById(R.id.matchFlipView);

        flipView.setFrontImage(R.drawable.bigboy);
        flipView.setRearImage(R.drawable.match_pro_card);

        final HorizontalInfiniteCycleViewPager pager=(HorizontalInfiniteCycleViewPager)findViewById(R.id.horizontal_cycle);

        MyAdapter adapter= new MyAdapter(lstCards,getBaseContext());

        pager.setAdapter(adapter);


        pager.setOnInfiniteCyclePageTransformListener(new OnInfiniteCyclePageTransformListener() {
            @Override
            public void onPreTransform(View page, float position) {
                flipView.setFrontImage(lstMatches.get(pager.getRealItem()));
                flipView.setRearImage(lstMatches.get(pager.getRealItem()+1));
            }

            @Override
            public void onPostTransform(View page, float position) {

            }
        });

        letsLunch.setOnClickListener(new View.OnClickListener() {
            int numberOfClicks=0;
            @Override
            public void onClick(View v) {
                    int clicks=numberOfClicks+1;
                    if (clicks>=3){
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                startActivity(new Intent(Matching.this, matched.class));
                            }
                        }, 10000);
                    }
                    numberOfClicks=clicks;
            }
        });

        maybeLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        }
    });
    }

    private void initCards(){
        lstCards.add(R.drawable.card);
        lstCards.add(R.drawable.card);
        lstCards.add(R.drawable.card);
    }

    private void initMatches(){
        lstMatches.add(R.drawable.login_background);
        lstMatches.add(R.drawable.bigboyhex);
        lstMatches.add(R.drawable.shindig_circle);
        lstMatches.add(R.drawable.login_background);
        lstMatches.add(R.drawable.bigboyhex);
        lstMatches.add(R.drawable.shindig_circle);
    }



}
