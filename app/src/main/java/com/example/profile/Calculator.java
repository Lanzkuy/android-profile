package com.example.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class Calculator extends AppCompatActivity {

    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnPlus,btnMinus,btnMultiply,btnDivision,btnEquals,btnClear;
    private TextView tvResult, tvTemp;
    private double num=Double.NaN;
    private double num2;
    private String process, operator;
    private String status="process";
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        final DecimalFormat format = new DecimalFormat("#.##");
        format.setDecimalFormatSymbols((DecimalFormatSymbols.getInstance(Locale.ENGLISH)));
        format.setDecimalSeparatorAlwaysShown(false);

        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);

        btnPlus=findViewById(R.id.btnPlus);
        btnMinus=findViewById(R.id.btnMinus);
        btnMultiply=findViewById(R.id.btnMultiply);
        btnDivision=findViewById(R.id.btnDivision);
        btnEquals=findViewById(R.id.btnEqual);
        btnClear=findViewById(R.id.btnClear);

        tvResult=findViewById(R.id.txtResult);
        tvTemp=findViewById(R.id.txtTemp);

        btnClear.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setText("");
                tvTemp.setText("");
                num=Double.NaN;
                num2 =Double.NaN;
                play(R.raw.zapsplat_multimedia_click_003_19369);
            }
        }));

        btn0.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvResult.getText().toString().equals("0")&&status.equals("process"))
                {
                    process= tvResult.getText().toString();
                    tvResult.setText(process+"0");
                }
                else if(!tvResult.getText().toString().equals("0")&&status.equals("clear"))
                {
                    status="process";
                    tvResult.setText("0");
                }
                else
                {
                    tvResult.setText("0");
                }
                play(R.raw.zapsplat_multimedia_button_press_plastic_click_002_36869);
            }
        }));

        btn1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvResult.getText().toString().equals("0")&&status.equals("process"))
                {
                    process= tvResult.getText().toString();
                    tvResult.setText(process+"1");
                }
                else if(!tvResult.getText().toString().equals("0")&&status.equals("clear"))
                {
                    status="process";
                    tvResult.setText("1");
                }
                else
                {
                    tvResult.setText("1");
                }
                play(R.raw.zapsplat_multimedia_button_press_plastic_click_001_36868);
            }
        }));

        btn2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvResult.getText().toString().equals("0")&&status.equals("process"))
                {
                    process= tvResult.getText().toString();
                    tvResult.setText(process+"2");
                }
                else if(!tvResult.getText().toString().equals("0")&&status.equals("clear"))
                {
                    status="process";
                    tvResult.setText("2");
                }
                else
                {
                    tvResult.setText("2");
                }
                play(R.raw.zapsplat_multimedia_button_press_plastic_click_004_36871);
            }
        }));

        btn3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!tvResult.getText().toString().equals("0")&&status.equals("process"))
                {
                    process= tvResult.getText().toString();
                    tvResult.setText(process+"3");
                }
                else if(!tvResult.getText().toString().equals("0")&&status.equals("clear"))
                {
                    status="process";
                    tvResult.setText("3");
                }
                else
                {
                    tvResult.setText("3");
                }
                play(R.raw.zapsplat_multimedia_button_press_plastic_click_002_36869);
            }
        }));

        btn4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvResult.getText().toString().equals("0")&&status.equals("process"))
                {
                    process= tvResult.getText().toString();
                    tvResult.setText(process+"4");
                }
                else if(!tvResult.getText().toString().equals("0")&&status.equals("clear"))
                {
                    status="process";
                    tvResult.setText("4");
                }
                else
                {
                    tvResult.setText("4");
                }
                play(R.raw.zapsplat_multimedia_button_press_plastic_click_003_36870);
            }
        }));

        btn5.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvResult.getText().toString().equals("0")&&status.equals("process"))
                {
                    process= tvResult.getText().toString();
                    tvResult.setText(process+"5");
                }
                else if(!tvResult.getText().toString().equals("0")&&status.equals("clear"))
                {
                    status="process";
                    tvResult.setText("5");
                }
                else
                {
                    tvResult.setText("5");
                }
                play(R.raw.zapsplat_multimedia_button_press_plastic_click_001_36868);
            }
        }));

        btn6.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvResult.getText().toString().equals("0")&&status.equals("process"))
                {
                    process= tvResult.getText().toString();
                    tvResult.setText(process+"6");
                }
                else if(!tvResult.getText().toString().equals("0")&&status.equals("clear"))
                {
                    status="process";
                    tvResult.setText("6");
                }
                else
                {
                    tvResult.setText("6");
                }
                play(R.raw.zapsplat_multimedia_click_003_19369);
            }
        }));

        btn7.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvResult.getText().toString().equals("0")&&status.equals("process"))
                {
                    process= tvResult.getText().toString();
                    tvResult.setText(process+"7");
                }
                else if(!tvResult.getText().toString().equals("0")&&status.equals("clear"))
                {
                    status="process";
                    tvResult.setText("7");
                }
                else
                {
                    tvResult.setText("7");
                }
                play(R.raw.zapsplat_multimedia_button_press_plastic_click_005_36872);
            }
        }));

        btn8.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvResult.getText().toString().equals("0")&&status.equals("process"))
                {
                    process= tvResult.getText().toString();
                    tvResult.setText(process+"8");
                }
                else if(!tvResult.getText().toString().equals("0")&&status.equals("clear"))
                {
                    status="process";
                    tvResult.setText("8");
                }
                else
                {
                    tvResult.setText("8");
                }
                play(R.raw.zapsplat_multimedia_button_press_plastic_click_004_36871);
            }
        }));

        btn9.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvResult.getText().toString().equals("0")&&status.equals("process"))
                {
                    process= tvResult.getText().toString();
                    tvResult.setText(process+"9");
                }
                else if(!tvResult.getText().toString().equals("0")&&status.equals("clear"))
                {
                    status="process";
                    tvResult.setText("9");
                }
                else
                {
                    tvResult.setText("9");
                }
                play(R.raw.zapsplat_multimedia_click_003_19369);
            }
        }));

        btnPlus.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvTemp.getText().equals(""))
                {
                    operator ="plus";
                    tvTemp.setText(process+"+");
                }
                else
                {
                    Calc();
                    process= tvResult.getText().toString();
                    tvResult.setText("");
                    num=Double.parseDouble(process);
                    operator ="plus";
                    tvTemp.setText(process+"+");
                }
                play(R.raw.multimedia_button_click_014);
            }
        }));

        btnMinus.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvTemp.getText().equals(""))
                {
                    operator ="minus";
                    tvTemp.setText(process+"-");
                }
                else
                {
                    Calc();
                    process= tvResult.getText().toString();
                    tvResult.setText("");
                    num=Double.parseDouble(process);
                    operator ="minus";
                    tvTemp.setText(process+"-");
                }
                play(R.raw.multimedia_button_click_015);
            }
        }));

        btnMultiply.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvTemp.getText().equals(""))
                {
                    operator ="multi";
                    tvTemp.setText(process+"×");
                }
                else
                {
                    Calc();
                    process= tvResult.getText().toString();
                    tvResult.setText("");
                    num=Double.parseDouble(process);
                    operator ="multi";
                    tvTemp.setText(process+"×");
                }
                play(R.raw.zapsplat_multimedia_click_002_19368);
            }
        }));

        btnDivision.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvTemp.getText().equals(""))
                {
                    operator ="division";
                    tvTemp.setText(process+"÷");
                }
                else
                {
                    Calc();
                    process= tvResult.getText().toString();
                    tvResult.setText("");
                    num=Double.parseDouble(process);
                    operator ="division";
                    tvTemp.setText(process+"÷");
                }
                play(R.raw.multimedia_button_click_015);
            }
        }));

        btnEquals.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvResult.getText().equals(""))
                {
                    Calc();
                    operator ="other";
                    tvResult.setText(String.valueOf(format.format(num)));
                    tvTemp.setText(null);
                    play(R.raw.multimedia_button_click_014);
                }
                else
                {

                }
            }
        }));

        Button btnExit=findViewById(R.id.btnExit_Calculator);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toProfile();
            }
        });
    }

    private void Calc()
    {
        if(!Double.isNaN(num))
        {
            num2 =Double.parseDouble(tvResult.getText().toString());
            switch (operator)
            {
                case "plus":
                    num=num+ num2;
                    status="clear";
                    break;
                case "minus":
                    num=num- num2;
                    status="clear";
                    break;
                case "multi":
                    num=num* num2;
                    status="clear";
                    break;
                case "division":
                    num=num/ num2;
                    status="clear";
                    break;
                default :
                    break;
            }
        }
        else
        {
            num=Double.parseDouble(tvResult.getText().toString());
        }
    }

    private void play(int sound)
    {
        if(mp==null)
        {
            mp=MediaPlayer.create(this,sound);
        }
        mp.start();
    }

    private void toProfile()
    {
        Intent i=new Intent(Calculator.this,MainActivity.class);
        startActivity(i);
    }
}