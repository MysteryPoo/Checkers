package com.hti.checkers.checkers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class StartNewGame extends AppCompatActivity {
    public void closeStartNewGame(View view) {
        Intent gobacktoHome = new Intent(this, HomePage.class);
        startActivity(gobacktoHome);
    }

    public void mainActivity (View view){
        Intent gotoCheckerBoard = new Intent (this, MainActivity.class);
        startActivity(gotoCheckerBoard);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("New Game");
    }
}
