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
import android.widget.TextView;
import android.widget.Toast;

import com.etuloser.padma.rohit.amadassignment1.Interface.IRetrofit;
import com.etuloser.padma.rohit.amadassignment1.Model.loginrequest;
import com.etuloser.padma.rohit.amadassignment1.Model.loginresponse;
import com.etuloser.padma.rohit.amadassignment1.contants.IConstant;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

EditText username,password;

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.editTextEmail);
        password=(EditText)findViewById(R.id.editTextPassword);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String token =sharedpreferences.getString("usertoken",null);

if(token!=null)
{
    Intent i=new Intent(this,Profile.class);
    startActivity(i);
    finish();
}

    }


    public void OnLogin(View v)
    {

        if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            username.setError("Invalid username");
            password.setError("Invalid password");

        }
        else
        {

            ConnectivityManager cm=(ConnectivityManager)MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni=cm.getActiveNetworkInfo();
            if(ni!=null)
            {
                loginrequest lr=new loginrequest();
                lr.setEmail(username.getText().toString());
                lr.setPassword(password.getText().toString());
                Rlogin(lr);

            }
            else {
                Snackbar.make(v, "No Internet", Snackbar.LENGTH_LONG).show();
            }

        }


    }


    public void Rlogin(final loginrequest lr)
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(IConstant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service=retrofit.create(IRetrofit.class);

        Call<loginresponse> call=service.login(lr);
        call.enqueue(new Callback<loginresponse>() {
            @Override
            public void onResponse(Response<loginresponse> response, Retrofit retrofit) {
                try {


                    if (response.body().getCode().equals("200")) {
                        onsuccesslogin(response.body().token,response.body().email,lr.getPassword());
                        onloginerror(response.body().getSuccess());
                        Log.d("Response:", String.valueOf(response.body().getToken()) + " " + response.body().getCode() + " " + response.body().getSuccess());
                    } else {

                        onloginerror(response.body().getSuccess());
                    }

                } catch (Exception e) {
                    Log.d("OnResponse", "There is a error");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Throwable t) {

                Log.d("onFailure",t.toString());
            }
        });

    }



    public void onloginerror(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    public void onsuccesslogin(String token,String email,String passwor)
    {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("usertoken",token);
editor.putString("useremail",email);
        editor.putString("userpassword",passwor);

        editor.commit();
        Intent i=new Intent(this,Profile.class);
        startActivity(i);
        finish();

    }

    public void OnSignUp(View v)
    {
        Intent i=new Intent(this,Register.class);
        startActivity(i);
    }



}
