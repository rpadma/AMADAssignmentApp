package com.etuloser.padma.rohit.amadassignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText email,pwd,cpwd,age,name,weight,address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

    }

    public void init()
    {

        email=(EditText)findViewById(R.id.sign_up_email_et);
        pwd=(EditText)findViewById(R.id.sign_up_password_et);
        cpwd=(EditText)findViewById(R.id.sign_up_repassword_et);
        age=(EditText)findViewById(R.id.sign_up_age_et);
        weight=(EditText)findViewById(R.id.sign_up_Weight_et);
        address=(EditText)findViewById(R.id.sign_up_address_et);
        name=(EditText)findViewById(R.id.sign_up_name_et);

    }

    public void OnCancel(View v)
    {
        finish();
    }
    public void OnRegister(View v)
    {

    }

}
