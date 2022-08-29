package com.example.basiccalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    private EditText display;
    private ToggleButton toggleButton;
    private Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);


        display= findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });

        toggleButton=findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    // The toggle is disabled
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        });
    }
     //android:theme="@style/Theme.BasicCalculator"

    private  void updateText(String strToAdd){
        String oldStr= display.getText().toString();
        int cursorPor= display.getSelectionStart();
        String leftStr= oldStr.substring(0, cursorPor);
        String rightStr= oldStr.substring(cursorPor);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPor+1);

        }else{
            display.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
            display.setSelection(cursorPor + 1);
        }

    }

    public void zeroBTN(View view){
        updateText("0");


    }
    public void pointBTN(View view){
        updateText(".");


    }
    public void equalBTN(View view){
        String userExp= display.getText().toString();

        userExp= userExp.replaceAll("÷","/");
        userExp= userExp.replaceAll("×","/");

        Expression exp= new Expression(userExp); //it will cal the ans
        String result= String.valueOf(exp.calculate());
        display.setText(result);

        //setting the cursor
        display.setSelection(result.length());


    }
    public void plusMinusBTN(View view){
        updateText("%");

    }
    public void addBTN(View view){
        updateText("+");


    }
    public void threeBTN(View view){

        updateText("3");

    }
    public void twoBTN(View view){

        updateText("2");

    }
    public void oneBTN(View view){
        updateText("1");


    }
    public void subtractBTN(View view){
        updateText("-");


    }
    public void sixBTN(View view){
        updateText("6");


    }
    public void fiveBTN(View view){
        updateText("5");



    }
    public void fourBTN(View view){
        updateText("4");


    }
    public void multiplyBTN(View view){
        updateText("*");


    }
    public void nineBTN(View view){
        updateText("9");


    }
    public void eightBTN(View view){
        updateText("8");


    }
    public void sevenBTN(View view){
        updateText("7");


    }
    public void divideBTN(View view){
        updateText("/");

    }
    public void exponentBTN(View view){
        updateText("^");

    }
    public void parenthesesBTN(View view){
        int cursorPos= display.getSelectionStart();
        int openPar = 0;
        int closePar= 0;
        int textLen = display.getText().length();
        //here we have to increase the parenthesis variables depending
        //depending on where the cursor is.
        for(int i=0; i<cursorPos; i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPar +=1;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closePar +=1;
            }
        }
        //(8+7)*7

        if (openPar == closePar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
        }
        else if (closePar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos +1);



    }
    public void clearBTN(View view){
       // updateText("");
        display.setText("");


    }
    public void backspaceBTN(View view){
        int cursorPos= display.getSelectionStart();
        int textLen= display.getText().length();

        if(cursorPos !=0 && textLen !=0){
            //replace different characters
            SpannableStringBuilder selection= (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }



    }

}

/*
* The reason of choosing table layout is to keep each button or view within their respective
* row or column and to make responsive for all devices.
* */