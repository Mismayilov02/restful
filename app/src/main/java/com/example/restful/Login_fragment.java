package com.example.restful;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Login_fragment extends Fragment {

    Button exit_btn , settings_btn , login_btn;
    Fragment settings_fragment , operation_fragment , price_framnet;
    String url = "https://mobilewebapi.ellab.az/api/mobile/";
    Retrofit_login retrofit_login;
    Retrofit retrofit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login_fragment, container, false);
        exit_btn = v.findViewById(R.id.login_exit_btn);
        settings_btn = v.findViewById(R.id.login_settings_btn);
        login_btn = v.findViewById(R.id.login_login_btn);
        settings_fragment = new Fragment();
        operation_fragment = new Fragment();
        price_framnet = new Fragment();



        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replacefragment(price_framnet);
                //getActivity().finishAffinity();
            }
        });


        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefragment(settings_fragment);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_Retrofit();

            }
        });

        return v;
    }

    void replacefragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        transaction.replace(R.id.main_fragmnet, fragment);
        transaction.commit();
    }

private void  set_Retrofit() {

        login login = new login("ATAMALI" , "Mas123456");

     retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
   retrofit_login = retrofit.create(Retrofit_login.class);

    try {
    Call<List<ResponseBody>> call = retrofit_login.llogin(login);

    call.enqueue(new Callback<List<ResponseBody>>() {
            @Override
            public void onResponse(Call<List<ResponseBody>> call, Response<List<ResponseBody> > response) {
                System.out.println("succes: " + response.body());
                System.out.println(response);
                System.out.println(response.message()+" "+response.code());

            }

            @Override
            public void onFailure(Call<List<ResponseBody>> call, Throwable t) {
                System.out.println("eror");
                System.out.println(t);
            }
        });
    }catch (Exception e){
        System.out.println("try" +e);
    }
}
}




