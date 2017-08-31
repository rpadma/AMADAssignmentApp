package com.etuloser.padma.rohit.amadassignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.etuloser.padma.rohit.amadassignment1.Interface.IRetrofit;
import com.etuloser.padma.rohit.amadassignment1.Model.loginresponse;
import com.etuloser.padma.rohit.amadassignment1.Model.updaterequest;
import com.etuloser.padma.rohit.amadassignment1.Model.user;
import com.etuloser.padma.rohit.amadassignment1.Model.viewuser;
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
String utoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String token =sharedpreferences.getString("usertoken",null);
        init();
        Rview(token);
        utoken=token;

    }

    public void init(){

        name=(EditText)findViewById(R.id.profile_name_et);
        age=(EditText)findViewById(R.id.profile_age_et);
        weight=(EditText)findViewById(R.id.profile_Weight_et);
        address=(EditText)findViewById(R.id.profile_address_et);
        btncancel=(Button)findViewById(R.id.VCancel);
        btnedit=(Button)findViewById(R.id.VEdit);
        btnsave=(Button)findViewById(R.id.VSave);

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

        Call<viewuser> call=service.viewuser(lr);
        call.enqueue(new Callback<viewuser>() {
            @Override
            public void onResponse(Response<viewuser> response, Retrofit retrofit) {
                try{

                // Log.d("code",String.valueOf(response.isSuccess()));
                  user ur=new user();
                    ur.setEmail(response.body().getUser().getEmail());
                    ur.setName(response.body().getUser().getName());
                    ur.setWeight(response.body().getUser().getWeight());
                    ur.setAge(response.body().getUser().getAge());
                    ur.setAddress(response.body().getUser().getAddress());

                    getuser(ur);
                   Log.d("Response:",String.valueOf(response.body().getUser().getName())+" "+response.body().getUser().getAge()+" "+response.body().getUser().getAddress());

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


    public void getuser(user u)
    {

        name.setText(u.getName());
        age.setText(u.getAge());
        weight.setText(u.getWeight());
        address.setText(u.getAddress());

    }




    public void Rupdate(updaterequest ur)
    {


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(IConstant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service=retrofit.create(IRetrofit.class);

        Call<loginresponse> call=service.updateuser(ur);
        call.enqueue(new Callback<loginresponse>() {
            @Override
            public void onResponse(Response<loginresponse> response, Retrofit retrofit) {
                try{

                    if(response.body().getCode().equals(200)) {
                        Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();
                    }
                        // Log.d("Response:",String.valueOf(response.body().getUser().getName())+" "+response.body().getUser().getAge()+" "+response.body().getUser().getAddress());

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

    public void OnSave(View v)
    {

        updateuser();
        Toast.makeText(this,"Saved Profile",Toast.LENGTH_SHORT).show();
        btnsave.setEnabled(false);
        DisableUI();
    }

    public void updateuser()
    {
        updaterequest ur=new updaterequest();
        ur.setToken(utoken);
        user u=new user();
        u.setAddress(address.getText().toString());
        u.setName(name.getText().toString());
        u.setAge(age.getText().toString());
        u.setWeight(weight.getText().toString());
        ur.setUser(u);
        Rupdate(ur);
        getuser(ur.getUser());


    }
    public void OnEdit(View v)
    {
       EnableUI();
        btnsave.setEnabled(true);

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
