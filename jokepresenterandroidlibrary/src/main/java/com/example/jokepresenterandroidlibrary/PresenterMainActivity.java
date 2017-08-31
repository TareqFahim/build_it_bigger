package com.example.jokepresenterandroidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PresenterMainActivity extends AppCompatActivity {

    TextView jokesTV;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenter_main);
        jokesTV = (TextView) findViewById(R.id.joke_presenter_tv);
        intent = getIntent();
        if(intent.hasExtra(getString(R.string.joke_intent_extra))){
            String joke = intent.getStringExtra(getString(R.string.joke_intent_extra));
            jokesTV.setText(joke);
        }else{
            jokesTV.setText("No Recived Jokes");
        }
    }
}
