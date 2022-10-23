package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvCalc, tvResult;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot, btnPlus, btnMinus, btnMultiply, btnDivide, btnEqual, btnC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCalc = findViewById(R.id.tvCalculations);
        tvResult = findViewById(R.id.tvResult);

        assignId(btn0,R.id.btn0);
        assignId(btn1,R.id.btn1);
        assignId(btn2,R.id.btn2);
        assignId(btn3,R.id.btn3);
        assignId(btn4,R.id.btn4);
        assignId(btn5,R.id.btn5);
        assignId(btn6,R.id.btn6);
        assignId(btn7,R.id.btn7);
        assignId(btn8,R.id.btn8);
        assignId(btn9,R.id.btn9);
        assignId(btnDot,R.id.btnDot);
        assignId(btnPlus,R.id.btnPlus);
        assignId(btnMinus,R.id.btnMinus);
        assignId(btnMultiply,R.id.btnMultiply);
        assignId(btnDivide,R.id.btnDivide);
        assignId(btnEqual,R.id.btnEqual);
        assignId(btnC,R.id.btnC);
    }

    void assignId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = tvCalc.getText().toString();

        if(buttonText.equals("C"))
        {
            tvCalc.setText("");
            tvResult.setText("0");
            return;

        }
        dataToCalculate = dataToCalculate+buttonText;
        tvCalc.setText(dataToCalculate);
    }
}