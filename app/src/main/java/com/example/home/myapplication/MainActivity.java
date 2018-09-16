package com.example.home.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//Only one API has been used for now, Sir/Ma'am, rest will be available soon.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tech(View view) {
        Intent i = new Intent(this, techNews.class);
        startActivity(i);
    }
}
    /**public void current(View view) {                                 WILL TRY SOON
        Intent i = new Intent(this, current.class);
        startActivity(i);


    }*/
