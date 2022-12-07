package com.example.lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvCalc, tvResult;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot, btnPlus, btnMinus, btnMultiply, btnDivide, btnEqual, btnC;
    Button btnCE, btnPlusminus, btnRoot, btnBackspace;
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
        assignId(btnCE,R.id.btnCE);
        assignId(btnPlusminus,R.id.btnPlusMinus);
        assignId(btnRoot,R.id.btnRootSqr);
        assignId(btnBackspace,R.id.btnBackSpace);
    }

    void assignId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        MathOperations mo = new MathOperations();
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = tvCalc.getText().toString();

        if(buttonText.equals("CE") || buttonText.equals("C"))
        {
            tvCalc.setText("");
            tvResult.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            if(dataToCalculate.contains("√"))
            {
                tvResult.setText(String.valueOf(mo.sqrRoot(dataToCalculate)));

            }else{
                tvCalc.setText(tvResult.getText().toString());
            }
            return;
        }
        if(buttonText.equals("←"))
        {
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else if(buttonText.equals("±")) {

            dataToCalculate = mo.changeSign(dataToCalculate);

        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }



        tvCalc.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Error"))
        {
            tvResult.setText(finalResult);
        }
    }
    String getResult(String data)
    {

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            else
            {
                double value = Double.parseDouble(finalResult);
                value = Double.parseDouble(new DecimalFormat("##.########").format(value));
                finalResult = String.valueOf((value));
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}