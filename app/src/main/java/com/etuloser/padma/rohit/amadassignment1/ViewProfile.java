package com.etuloser.padma.rohit.amadassignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.etuloser.padma.rohit.amadassignment1.R;

public class ViewProfile extends AppCompatActivity {

    EditText name,age,weight,address;
    Button btnsave,btnedit,btncancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        init();

    }

    public void init(){

        name=(EditText)findViewById(R.id.profile_name_et);
        age=(EditText)findViewById(R.id.profile_age_et);
        weight=(EditText)findViewById(R.id.profile_Weight_et);
        address=(EditText)findViewById(R.id.profile_address_et);

    }


    public void OnSave(View v)
    {

    }

    public void OnEdit(View v)
    {
       EnableUI();

    }

    public void OnCancel(View v)
    {
        DisableUI();

    }

    public void EnableUI()
    {
        name.setEnabled(true);
        age.setEnabled(true);
        weight.setEnabled(true);
        address.setEnabled(true);
    }
    public void DisableUI()
    {
        name.setEnabled(false);
        age.setEnabled(false);
        weight.setEnabled(false);
        address.setEnabled(false);
    }


}
