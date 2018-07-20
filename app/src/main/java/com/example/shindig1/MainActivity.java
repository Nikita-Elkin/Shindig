package com.example.shindig1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import org.json.*;
import android.util.Log;




public class MainActivity extends AppCompatActivity {
    Button b1;
    ImageButton settings;
    EditText mEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        b1 = (Button) findViewById(R.id.button);
        mEdit = (EditText) findViewById(R.id.emailEditText);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = "http://httpbin.org/get?param1=hello";
                        HttpCOP cop = HttpCOP.getInstance(MainActivity.this);
                        Response.ErrorListener stderror_listener = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Volley Error", error.toString());
                            }
                        };

                        Request getRequest = new StringRequest(Request.Method.POST, Shinfo.SERVER_ADDRESS,
                                response -> { Log.d("Volley Response",response.toString());}
                                , null);

                        cop.addToRequestQueue(getRequest, "Test");

                        //Example: HttpCOP.postData("Example.php", "param1="+mEdit.getText().toString());
                        //or make Java static method to determine if login is good or not
                        if (mEdit.getText().toString().equals("admin")) {//for testing purposes only
                            startActivity(new Intent(MainActivity.this, Main2Activity.class));
                            System.out.println("admin logged in");
                        }
                        else{
                            startActivity(new Intent(MainActivity.this, Main2Activity.class));//do it anyways im too lazy to type admin every time
                        }
                    }
                });


};

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    }



