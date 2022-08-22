package com.example.restful;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

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

public class price_fragment extends Fragment {

    Button price_exit_btn , price_back_btn ,price_refresh_btn;
    SearchView searchView;
    Fragment operation_fragment;
    price_adapter price_adapter;
    ListView pric_listview;
    String url = "https://mobilewebapi.ellab.az/api/mobile/";
    Retrofit_login retrofit_login;
    Retrofit retrofit;
    String token = "Bearer mmblwlP687IA1C_cHV2FHlc__6G8VisJu_fRxJmHWLgta76M5Bl6b2G29MTaelJAlewQc857xB05dG-lStngWA";
    ArrayList<String>name_list = new ArrayList<>();
    ArrayList<Integer> price_list = new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_price_fragment, container, false);

        price_back_btn = v.findViewById(R.id.price_back_btn);
        price_refresh_btn = v.findViewById(R.id.price_again_btn);
        price_exit_btn= v.findViewById(R.id.price_exit_btn);
        pric_listview = v.findViewById(R.id.price_list);
        operation_fragment = new Fragment();
        searchView = v.findViewById(R.id.searchView);

        retrofitt();

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getadapter();
                refresh_btn();
            }
        };handler.postDelayed(runnable ,500);

       //refresh_btn();



searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String s) {

        price_adapter.getFilter().filter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        price_adapter.getFilter().filter(s);
        return false;
    }
});

        price_exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                retrofitt();
                //getActivity().finishAffinity();
            }
        });


        price_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replacefragment(operation_fragment);
            }
        });

        price_refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getadapter();
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


    void  retrofitt(){
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
               // .client(okHttpClient)
                .build();
        retrofit_login = retrofit.create(Retrofit_login.class);
        Call<List<name_price>> call = retrofit_login.name_prrice(token);
        call.enqueue(new Callback<List<name_price>>() {
            @Override
            public void onResponse(Call<List<name_price>> call, Response<List<name_price>> response) {
                System.out.println("sucess nameprice");

                List<name_price> name_price_lists = response.body();
                for (name_price name_price :name_price_lists){
                    name_list.add(name_price.getName());
                    price_list.add(name_price.getPrice());
                }

              //  System.out.println("name " + name_price_lists.get(0).getName());
             //   System.out.println("name " + response.body().get(0).nnamePriceList.get(0).getName());
                System.out.println(response.code());
                System.out.println(response.message());
            }

            @Override
            public void onFailure(Call<List<name_price>> call, Throwable t) {
System.out.println(t.getMessage());
            }
        });
    }

    private void getadapter(){
        price_adapter = new price_adapter(getActivity() , name_list , price_list);
        pric_listview.setAdapter(price_adapter);
    }

    public  void refresh_btn(){
        if(price_adapter.name_list.size() ==0){
            price_refresh_btn.setVisibility(View.VISIBLE);

        }else{
            price_refresh_btn.setVisibility(View.INVISIBLE);
        }
    }
}