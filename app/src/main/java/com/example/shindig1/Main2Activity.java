package com.example.shindig1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {
    ImageButton shindigSession;
    ImageButton settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        settings = (ImageButton) findViewById(R.id.settingsButton);
        setSupportActionBar(toolbar);



        shindigSession = (ImageButton) findViewById(R.id.start_shindig);
        shindigSession.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, ShindigSession.class));
            }

        });

        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, Profile_view.class));
            }
        });

    }
    @Override
    public void onBackPressed() { }
}


