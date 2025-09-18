package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    private AddCityDialogListener listener;
    private City city;
    private boolean edit ;
    public static AddCityFragment newInstance(City city){
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        args.putBoolean("edit", true);
        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public static AddCityFragment newInstance() {
        Bundle args = new Bundle();
        args.putBoolean("edit", false);
        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }
    interface AddCityDialogListener{
        void addCity(City city);
        void editCity(City city);
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if (context instanceof AddCityDialogListener){
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null){
            edit = args.getBoolean("edit", false);
            city = (City) args.getSerializable("city");
        }

        View view = getLayoutInflater().inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
       //Pre-fill the context
        if(edit && city != null){
           editCityName.setText(city.getName());
           editProvinceName.setText(city.getProvince());
       }


        //if(floating btn) then this builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setView(view)
                .setTitle(edit ? "Edit City" : "Add City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton(edit ? "Save" : "Add", (dialog, which) ->{
                    String cityName = editCityName.getText().toString().trim();
                    String provinceName = editProvinceName.getText().toString().trim();
                    if(cityName.isEmpty()) return;
                    if(edit && city != null){
                        city.setName(cityName);
                        city.setProvince(provinceName);
                        listener.editCity(city);
                    } else{
                        listener.addCity(new City(cityName, provinceName));
                    }
                });
        return builder.create();
    }
}
