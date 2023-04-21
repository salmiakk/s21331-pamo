package com.pjatk.myapplication.ui.bmi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pjatk.myapplication.databinding.FragmentBmiBinding;

public class BmiFragment extends Fragment {

    private double height = 0.0;
    private double weight = 0.0; // bill amount entered by the user
    private double bmi = 0.0; // initial tip percentage
    private EditText heightEditText; // shows formatted bill amount
    private EditText weightEditText; // shows tip percentage
    private TextView bmiTextView; // shows calculated tip amount
    private FragmentBmiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText weightEditText = binding.weightEditText;
        EditText heightEditText = binding.heightEditText;

        weightEditText.addTextChangedListener(weightEditTextWatcher);
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        return root;
    }
    private void calculate() {
        TextView bmiTextView = binding.bmiTextView;

        if (height == 0 || weight == 0) {
            bmiTextView.setText("Wrong data!");
        } else {
            double bmi = weight / (height * height);
            bmiTextView.setText(String.valueOf(bmi));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {  // Get height converted to meters
                height = Double.parseDouble(s.toString()) / 100;
            } catch (NumberFormatException e) { // if s is empty or non-numeric
                height = 0.0;
            }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            try {
                weight = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                weight = 0.0;
            }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };
}