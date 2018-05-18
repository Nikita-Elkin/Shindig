package com.example.shindig1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Toast;


public class ShindigSession extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_questions);

        Button b3 = (Button) findViewById(R.id.button2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NumberPicker np = findViewById(R.id.numberPicker);

        np.setMinValue(0);
        np.setMaxValue(4);

        np.setOnValueChangedListener(onValueChangeListener);


        b3.setOnClickListener(
                new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                NumberPicker np = findViewById(R.id.numberPicker);

                np.setMinValue(0);
                np.setMaxValue(4);

                np.setOnValueChangedListener(onValueChangeListener);

                RadioGroup radioGroup;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

                if ((((radioGroup.getCheckedRadioButtonId() == R.id.radioButton4)|(radioGroup.getCheckedRadioButtonId() == R.id.radioButton3)|(radioGroup.getCheckedRadioButtonId() == R.id.radioButton5)))& np.getValue()!=0)
                            {
                    startActivity(new Intent(ShindigSession.this, Matching.class));
                }
        }
    });

    }


    NumberPicker.OnValueChangeListener onValueChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(ShindigSession.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);
                }};
}
