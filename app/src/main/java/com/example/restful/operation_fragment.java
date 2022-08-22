package com.example.restful;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class operation_fragment extends Fragment {

Button operation_exit_btn , operation_again_btn;
price_fragment price_fagment;
ListView operation_list;
    String url = "https://mobilewebapi.ellab.az/api/mobile/";
    Retrofit_login retrofit_login;
    operation_adapter operation_adapter;
    Retrofit retrofit;
    String token = "Bearer eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZ-lStngWA";
    ArrayList<String> operation_name_list = new ArrayList<>();
    ArrayList<String> operation_tupe_list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_operation_fragment, container, false);

        operation_exit_btn  =v.findViewById(R.id.operation_exit_btn);
        operation_list = v.findViewById(R.id.operation_list);
        operation_again_btn = v.findViewById(R.id.operation_again_btn);
        price_fagment = new price_fragment();

        getretrofit();

        Handler handler = new Handler();
         Runnable runnable = new Runnable() {
             @Override
             public void run() {
                 get_adapter();
                 refresh_btn();
             }
         };handler.postDelayed(runnable , 200);


        operation_exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getActivity().finishAffinity();
                getretrofit();
            }
        });

        operation_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                replacefragment(price_fagment);
            }
        });

        operation_again_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_adapter();
                refresh_btn();
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

    public void getretrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())

                //.client(okHttpClient)
                .build();
        retrofit_login = retrofit.create(Retrofit_login.class);

        Call<List<operation_list>> operation_listCall = retrofit_login.operation_list(token);
        operation_listCall.enqueue(new Callback<List<operation_list>>() {
            @Override
            public void onResponse(Call<List<operation_list>> call, Response<List<operation_list>> response) {

                List<operation_list> operation_lists = response.body();

                for(operation_list operation_list : operation_lists){
                    operation_name_list.add(operation_list.getOperationName());
                    operation_tupe_list.add(operation_list.getOperationtype());
                    System.out.println("type" + operation_list.getOperationtype());
                    System.out.println(operation_list.getOperationName());
                }

                //System.out.println("name" +response.body().getOperationName());

                System.out.println(response.code());
             //   System.out.println("sucess operation list");
            }

            @Override
            public void onFailure(Call<List<operation_list>> call, Throwable t) {
                System.out.println("eror");
                System.out.println(t);

            }
        });
    }
    private void  get_adapter(){
        operation_adapter = new operation_adapter(getActivity() , operation_name_list , operation_tupe_list);
        operation_list.setAdapter(operation_adapter);
    }


    public  void refresh_btn() {
        if (operation_adapter.operation_tupe_list.size() == 0) {
            operation_again_btn.setVisibility(View.VISIBLE);

        } else {
            operation_again_btn.setVisibility(View.INVISIBLE);
        }
    }
}