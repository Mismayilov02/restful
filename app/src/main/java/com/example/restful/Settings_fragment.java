package com.example.restful;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Settings_fragment extends Fragment {

    Button setting_exit_btn , settings_save_btn , setting_back_btn;
    Fragment login_feragment ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings_fragment, container, false);
        setting_exit_btn = v.findViewById(R.id.settings_exit_btn);
        settings_save_btn = v.findViewById(R.id.settings_save_btn);
        setting_back_btn = v.findViewById(R.id.settings_back_btn);
         login_feragment = new Login_fragment();


        setting_exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finishAffinity();
            }
        });

        setting_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefragment(login_feragment);
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
}