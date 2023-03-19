package com.example.tipper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bmiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bmiFragment extends Fragment {

    private double height = 0.0;
    private double weight = 0.0; // bill amount entered by the user
    private double bmi = 0.0; // initial tip percentage
    private EditText heightEditText; // shows formatted bill amount
    private EditText weightEditText; // shows tip percentage
    private TextView bmiTextView; // shows calculated tip amount
    public bmiFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bmi, container, false);
        heightEditText = (EditText) rootView.findViewById(R.id.heightEditText);
        weightEditText = (EditText) rootView.findViewById(R.id.weightEditText);

        bmiTextView = (TextView) rootView.findViewById(R.id.bmiTextView);

        heightEditText.addTextChangedListener(heightEditTextWatcher);
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        return rootView;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void calculate() {
        if (height == 0 || weight == 0) {
            bmiTextView.setText("Wrong data!");
        } else {
            double bmi = weight / (height * height);
            bmiTextView.setText(String.valueOf(bmi));
        }
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