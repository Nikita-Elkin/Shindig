package com.example.shindig1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class matched extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched);
        String matched_intro = "Congratulations! You\'ve been matched with:";
        String match_name = "Match Name";
        String matched_text = matched_intro + " " + match_name;

        TextView matchedTextView = findViewById(R.id.congratulations);
        matchedTextView.setText(matched_text);

        String match_position = "Match Position";
        String matched_description = match_name + "\n" + match_position;

    }

    // Lucien needs to make a method to change the match_name string to the actual name of the
    // match. All I did was separate the constant match introduction and match name to make it
    // easier for him to do. As of now, match_name will always be the default "Match Name".

}
