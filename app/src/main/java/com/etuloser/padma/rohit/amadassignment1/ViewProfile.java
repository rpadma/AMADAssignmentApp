package com.etuloser.padma.rohit.amadassignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.etuloser.padma.rohit.amadassignment1.Interface.IRetrofit;
import com.etuloser.padma.rohit.amadassignment1.Model.loginresponse;
import com.etuloser.padma.rohit.amadassignment1.Model.updaterequest;
import com.etuloser.padma.rohit.amadassignment1.Model.user;
import com.etuloser.padma.rohit.amadassignment1.R;
import com.etuloser.padma.rohit.amadassignment1.contants.IConstant;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ViewProfile extends AppCompatActivity {

    EditText name,age,weight,address;
    Button btnsave,btnedit,btncancel;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String token =sharedpreferences.getString("usertoken",null);
        init();
        Rview(token);

    }

    public void init(){

        name=(EditText)findViewById(R.id.profile_name_et);
        age=(EditText)findViewById(R.id.profile_age_et);
        weight=(EditText)findViewById(R.id.profile_Weight_et);
        address=(EditText)findViewById(R.id.profile_address_et);

    }


    public void getuser(user u)
    {

        name.setText(u.getName());
        age.setText(u.getAge());
        weight.setText(u.getWeight());
        address.setText(u.getAddress());

    }

    public void Rview(String token)
    {
        loginresponse lr=new loginresponse();
        lr.setToken(token);



        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(IConstant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service=retrofit.create(IRetrofit.class);

        Call<user> call=service.viewuser(lr);
        call.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Response<user> response, Retrofit retrofit) {
                try{

                    user ur=new user();
                    ur.setEmail(response.body().getEmail());
                    ur.setName(response.body().getName());
                    ur.setWeight(response.body().getWeight());
                    ur.setAge(response.body().getAge());
                    getuser(ur);
              //      Log.d("Response:",String.valueOf(response.body().getToken())+" "+response.body().getCode()+" "+response.body().getSuccess());

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


    public void Rupdate(updaterequest ur)
    {

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
