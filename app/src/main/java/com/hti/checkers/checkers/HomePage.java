package com.hti.checkers.checkers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class HomePage extends AppCompatActivity {

    public void gotoOptions(View view){
        Intent options = new Intent(this, Options.class);
        startActivity(options);
    }

    public void gotoNewGame(View view){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TODO: Make the Help Activity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

}
