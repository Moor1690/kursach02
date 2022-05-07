package com.example.kursash01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.CellSignalStrengthNr;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kursash01.log.Par;
import com.example.kursash01.log.Update;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView tvOut;
    Button btnOk;
    Button btnPrev;
    Button btnNext;
    Par p = new Par();
    String[][] mstr = new String[6][12];

    int today = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        TextView tv = findViewById(R.id.edit);






//        btnOk.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View viev){
//                tvOut.setText("some text");
//            }
//        });

//        try {
//            System.out.println("try ");
//            System.out.println("p.find\t" + p.find(message.toUpperCase()), mstr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnNext:
                        today += 1;
                        if (today == 6){today = 0;}
                        tvOut = (TextView) findViewById(R.id.textViewName1);
                        tvOut.setText(mstr[today][0]);
                        tvOut = (TextView) findViewById(R.id.textViewName2);
                        tvOut.setText(mstr[today][2]);
                        tvOut = (TextView) findViewById(R.id.textViewName3);
                        tvOut.setText(mstr[today][4]);
                        tvOut = (TextView) findViewById(R.id.textViewName4);
                        tvOut.setText(mstr[today][6]);
                        tvOut = (TextView) findViewById(R.id.textViewName5);
                        tvOut.setText(mstr[today][8]);
                        tvOut = (TextView) findViewById(R.id.textViewName6);
                        tvOut.setText(mstr[today][10]);
                        break;
                    case R.id.btnPrev:
                        today -= 1;
                        if (today == -1){today = 5;}
                        tvOut = (TextView) findViewById(R.id.textViewName1);
                        tvOut.setText(mstr[today][0]);
                        tvOut = (TextView) findViewById(R.id.textViewName2);
                        tvOut.setText(mstr[today][2]);
                        tvOut = (TextView) findViewById(R.id.textViewName3);
                        tvOut.setText(mstr[today][4]);
                        tvOut = (TextView) findViewById(R.id.textViewName4);
                        tvOut.setText(mstr[today][6]);
                        tvOut = (TextView) findViewById(R.id.textViewName5);
                        tvOut.setText(mstr[today][8]);
                        tvOut = (TextView) findViewById(R.id.textViewName6);
                        tvOut.setText(mstr[today][10]);
                        break;
                    case R.id.btnOk:
                        EditText editText = (EditText) findViewById(R.id.edit_message);
                        String message = editText.getText().toString();
                        try {
                            System.out.println("try ");
                            tvOut.setText(p.find(message.toUpperCase(), mstr));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        tvOut = (TextView) findViewById(R.id.textViewName1);
                        tvOut.setText(mstr[today][0]);
                        tvOut = (TextView) findViewById(R.id.textViewName2);
                        tvOut.setText(mstr[today][2]);
                        tvOut = (TextView) findViewById(R.id.textViewName3);
                        tvOut.setText(mstr[today][4]);
                        tvOut = (TextView) findViewById(R.id.textViewName4);
                        tvOut.setText(mstr[today][6]);
                        tvOut = (TextView) findViewById(R.id.textViewName5);
                        tvOut.setText(mstr[today][8]);
                        tvOut = (TextView) findViewById(R.id.textViewName6);
                        tvOut.setText(mstr[today][10]);
                        break;
                }
            }
        };

        btnOk.setOnClickListener(onClickListener);
        btnNext.setOnClickListener(onClickListener);
        btnPrev.setOnClickListener(onClickListener);


    }


    /*public void  sendMessage(View view){
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        try {
            System.out.println("try ");
//            System.out.println("p.find\t" + p.find(message.toUpperCase(),mstr));
            p.find(message.toUpperCase(), mstr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvOut = (TextView) findViewById(R.id.textViewName1);
        tvOut.setText(mstr[today][0]);
        tvOut = (TextView) findViewById(R.id.textViewName2);
        tvOut.setText(mstr[today][2]);
        tvOut = (TextView) findViewById(R.id.textViewName3);
        tvOut.setText(mstr[today][4]);
        tvOut = (TextView) findViewById(R.id.textViewName4);
        tvOut.setText(mstr[today][6]);
        tvOut = (TextView) findViewById(R.id.textViewName5);
        tvOut.setText(mstr[today][8]);
        tvOut = (TextView) findViewById(R.id.textViewName6);
        tvOut.setText(mstr[today][10]);


    }*/




}