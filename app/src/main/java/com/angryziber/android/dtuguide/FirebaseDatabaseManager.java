package com.angryziber.android.dtuguide;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class FirebaseDatabaseManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_database_manager);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_firebase_database_manager);
        Snackbar snackbar = Snackbar
                .make(relativeLayout, "Welcome to Developer Console", Snackbar.LENGTH_LONG);

        snackbar.show();


    }
}
