package com.etuloser.padma.rohit.amadassignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;

public class Profile extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }


    public void OnLogout(View v)
    {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        Snackbar.make(v, "Logged out", Snackbar.LENGTH_LONG).show();
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }
}
