package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.EditText;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.lang.Math;
import android.util.Log;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    private EditText amountDisplay;

    private java.util.Date todays_date=new java.util.Date();

    Calendar myCalendar = new GregorianCalendar(2021, 0, 0);
    Date myDate = myCalendar.getTime();

    long days = daysBetween(todays_date, myDate);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();

    }

    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup4);
        btnDisplay = (Button) findViewById(R.id.button);
        amountDisplay = (EditText) findViewById(R.id.editText2);


        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                String which_card_to_use = which_card();
                Toast.makeText(MainActivity.this,
                        "USE YOUR: " +  which_card_to_use, Toast.LENGTH_SHORT).show();


            }

        });

    }

    private static long daysBetween(Date one, Date two) {
        long difference = (one.getTime()-two.getTime())/86400000;
        return Math.abs(difference);
    }

    private String which_card(){
        Double amount = Double.valueOf(amountDisplay.getText().toString());
        String type = radioButton.getText().toString();
        Double percentage;

        if (new String("General").equals(type)) {
            // block of code to be executed if condition1 is true
            percentage = 0.01;

        }
        else if (new String("Costco").equals(type)) {
            percentage = 0.02;
        }

        else if (new String("Restaurant").equals(type)) {
            percentage = 0.03;

        }
        else if (new String("Travel").equals(type)) {
            percentage = 0.03;

        }
        else if (new String("Gas").equals(type)) {
            percentage = 0.04;

        }

        else {
            // block of code to be executed if the condition1 is false and condition2 is false
            percentage = 0.0;
        }

        Double roundup =  Math.ceil(amount) - amount; //roundup amount

        Double debit_card_gain;
        if (roundup < 0.01) {
            debit_card_gain = days * 1.0 * (0.07/365.0);

        }
        else{
            debit_card_gain = days * roundup * (0.07/365.0);

        }



        Double credit_card_gain = amount*percentage;
        String Card = "";


        if (debit_card_gain >= credit_card_gain){
            Card = "Debit Card";

        }
        else{
            Card = "Credit Card";
        }

        System.out.print(debit_card_gain);
        System.out.print(credit_card_gain);
        return Card;

    }


}
