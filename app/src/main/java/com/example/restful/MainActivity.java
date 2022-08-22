package com.example.restful;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    com.example.restful.open_screen_fragment open_screen_fragment;
    Login_fragment login_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open_screen_fragment = new open_screen_fragment();
        login_fragment = new Login_fragment();
//        setFragment(open_screen_fragment);


        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                setFragment(login_fragment);
            }
        };handler.postDelayed(runnable , 2200);

    }

    void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragmnet, fragment);
        fragmentTransaction.commit();
    }
}