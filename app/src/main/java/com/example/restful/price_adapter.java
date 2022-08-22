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

public class price_adapter extends ArrayAdapter<String> {

    ArrayList<String> name_list = new ArrayList<>();
    ArrayList<Integer> price_list = new ArrayList<>();
    Context context;

    public price_adapter(Context context , ArrayList<String> name_list , ArrayList<Integer> price_list){
        super(context , R.layout.operation_designer , R.id.operation_nam_text , name_list);
        this.context = context;
        this.price_list = price_list;
        this.name_list = name_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =  LayoutInflater.from(context).inflate(R.layout.operation_designer , null);

        TextView price_name_text = v.findViewById(R.id.operation_nam_text);
        TextView price_pric_text = v.findViewById(R.id.operation_type_text);

        price_name_text.setText(name_list.get(position));
        price_pric_text.setText(String.valueOf(price_list.get(position)));

        return v;
    }
}
