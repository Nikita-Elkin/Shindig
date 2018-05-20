package com.example.shindig1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class matched extends AppCompatActivity {
    List<String> lstNames= new ArrayList<>();
    List<Integer> lstMatches= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched);

        initMatches();
        addListNames();
        Random r = new Random();
        int person = r.nextInt(3);

        ImageView matchImage= (ImageView) findViewById(R.id.match_image);
        matchImage.setImageResource(lstMatches.get(2*person));

        String matched_intro = "And You Both Want To Shindig Together";
        String match_name = lstNames.get(person);
        String matched_text = match_name + " " + matched_intro;

        TextView matchedTextView = findViewById(R.id.congratulations);
        matchedTextView.setText(matched_text);

        String match_position = "Match Position";
        String matched_description = match_name + "\n" + match_position;

    }

    private void addListNames(){
        lstNames.add("Bob");
        lstNames.add("Phil");
        lstNames.add("Lewis");
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
