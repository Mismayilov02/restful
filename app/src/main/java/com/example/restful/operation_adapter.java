package com.example.restful;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class operation_adapter extends ArrayAdapter<String> {

    ArrayList<String> operation_name_list = new ArrayList<>();
    ArrayList<String> operation_tupe_list = new ArrayList<>();
    Context context;

    public operation_adapter(Context context , ArrayList<String> operation_name_list  , ArrayList<String> operation_tupe_list){
        super(context , R.layout.operation_designer  , R.id.operation_nam_text , operation_name_list);
        this.operation_name_list = operation_name_list;
        this.operation_tupe_list  = operation_tupe_list;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =  LayoutInflater.from(context).inflate(R.layout.operation_designer , null);

        TextView operation_name_text = v.findViewById(R.id.operation_nam_text);
        TextView operation_type_text = v.findViewById(R.id.operation_type_text);

        operation_name_text.setText(operation_name_list.get(position));
        operation_type_text.setText(operation_tupe_list.get(position));


        return  v;
    }
}
