package com.pjatk.myapplication.ui.whattoeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pjatk.myapplication.databinding.FragmentWhattoeatBinding;

public class WhatToEatFragment extends Fragment {
    private double height = 0.0;
    private double weight = 0.0; // bill amount entered by the user
    private double hb = 0.0; // initial tip percentage
    private EditText ageEditText; // shows formatted bill amount
    private EditText heightEditText; // shows formatted bill amount
    private EditText weightEditText; // shows tip percentage
    private TextView hbTextView; // shows calculated tip amount
    private String gender;

    private FragmentWhattoeatBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWhattoeatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView result = binding.hbTextView;
        Button calculateButton = binding.buttonId;
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText(calculate().toString());
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private Double calculate() {
        EditText weightEditText = binding.weightEditText1;
        EditText heightEditText = binding.heightEditText1;
        EditText ageEditText = binding.ageEditText;
        Spinner genderSpinner = binding.genderSpinner;

        double weight = Double.parseDouble(weightEditText.getText().toString());
        double height = Double.parseDouble(heightEditText.getText().toString());
        double age = Double.parseDouble(ageEditText.getText().toString());

        if(genderSpinner.getSelectedItem().toString().equals("Male")){
            return 66.5 + (13.75 * weight) + (5.003  * height) - (6.775 * age);
        } else {
            return 655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age);
        }
    }
}