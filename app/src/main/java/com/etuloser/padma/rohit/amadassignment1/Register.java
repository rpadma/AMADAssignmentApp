package com.etuloser.padma.rohit.amadassignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.etuloser.padma.rohit.amadassignment1.Interface.IRetrofit;
import com.etuloser.padma.rohit.amadassignment1.Model.loginrequest;
import com.etuloser.padma.rohit.amadassignment1.Model.loginresponse;
import com.etuloser.padma.rohit.amadassignment1.Model.user;
import com.etuloser.padma.rohit.amadassignment1.contants.IConstant;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

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


        if(!validatefields())
        {


            ConnectivityManager cm=(ConnectivityManager)Register.this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni=cm.getActiveNetworkInfo();
            if(ni!=null)
            {
                user u=new user();
                u.setName(name.getText().toString());
                u.setEmail(email.getText().toString());
                u.setPassword(pwd.getText().toString());
                u.setAddress(address.getText().toString());
                u.setAge(age.getText().toString());
                u.setWeight(weight.getText().toString());

                Rregister(u);

            }
            else {
                Snackbar.make(v, "No Internet", Snackbar.LENGTH_LONG).show();
            }


        }



    }


    public void Rregister(user u)
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(IConstant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service=retrofit.create(IRetrofit.class);

        Call<loginresponse> call=service.register(u);
        call.enqueue(new Callback<loginresponse>() {
            @Override
            public void onResponse(Response<loginresponse> response, Retrofit retrofit) {
                try{

loginresponse lr=new loginresponse();
                    lr.setCode(response.body().getCode());
                    lr.setSuccess(response.body().getSuccess());
                    onsuccessregister(lr);
                    Log.d("Response:",String.valueOf(response.body().getToken())+" "+response.body().getCode()+" "+response.body().getSuccess());

                }
                catch (Exception e)
                {
                    Log.d("OnResponse","There is a error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("onFailure",t.toString());
            }
        });

    }

    public void onsuccessregister(loginresponse lr)
    {
        Toast.makeText(getApplicationContext(), lr.getSuccess(), Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();

    }

    public boolean validatefields()
    {
        boolean flag=false;
        if(email.getText().toString().isEmpty())
        {
            email.setError("Enter Email");
            flag=true;
        }
        if(pwd.getText().toString().isEmpty())
        {
            email.setError("Enter Password");
            flag=true;
        }
        if(!cpwd.getText().toString().equals(pwd.getText().toString()))
        {
            cpwd.setError("Entered password should be same");
            pwd.setError("Entered password should be same");
            flag=true;
        }

        return flag;

    }
}
