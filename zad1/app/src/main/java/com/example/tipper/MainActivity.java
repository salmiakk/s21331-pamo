package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.TextView; // for displaying text

public class MainActivity extends AppCompatActivity {

    private double height = 0.0;
    private double weight = 0.0; // bill amount entered by the user
    private double bmi = 0.0; // initial tip percentage
    private EditText heightEditText; // shows formatted bill amount
    private EditText weightEditText; // shows tip percentage
    private TextView bmiTextView; // shows calculated tip amount

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        heightEditText = (EditText) findViewById(R.id.heightEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);

        bmiTextView = (TextView) findViewById(R.id.bmiTextView);

        heightEditText.addTextChangedListener(heightEditTextWatcher);
        weightEditText.addTextChangedListener(weightEditTextWatcher);

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


/*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
