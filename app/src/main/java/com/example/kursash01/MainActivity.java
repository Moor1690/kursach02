package com.example.kursash01;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.CellSignalStrength;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthNr;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kursash01.log.Par;
import com.example.kursash01.log.Update;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvOut,tv, tv2;
    Button btnOk;
    Button btnPrev;
    Button btnNext;
    Par p = new Par();
    String[][] mstr = new String[6][14];
    String[][] kabinet = new String[6][12];
    int chet2;
    int chet = 0;
    SharedPreferences sPref;

    Date date = new Date();
    int today = date.getDay();


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        loadText();
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        tv = (TextView) findViewById(R.id.day);
        TextView toDay = (TextView) findViewById(R.id.today);

        Update up = new Update();
        up.getWebsite();



        Date date = new Date();
        System.out.println("date.getDay()\t" + date.getDay());


        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, Calendar.SEPTEMBER, 1);
        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.YEAR, Calendar.FEBRUARY, 7);


        if((cal.get(Calendar.WEEK_OF_YEAR) - cal2.get(Calendar.WEEK_OF_YEAR)) >= 0){
            chet = (1+(cal.get(Calendar.WEEK_OF_YEAR) - cal2.get(Calendar.WEEK_OF_YEAR)));
        }else if ((cal.get(Calendar.WEEK_OF_YEAR) - cal2.get(Calendar.WEEK_OF_YEAR)) < 0){
            chet = (1+(cal.get(Calendar.WEEK_OF_YEAR) - cal3.get(Calendar.WEEK_OF_YEAR)));
        }
        System.out.println("chet\t" + chet + "chet\t" + chet%2);



/*        if(date.getDay() == 0){
            //toDay.setText(((int) date)+1).toString());
            System.out.println("date.equals(0)");
        }else {
            toDay.setText(date.toString());
        }*/
        toDay.setText("Сегодня " + Integer.toString(date.getDay()) + "." + (cal.get(Calendar.MONTH)+1)
                + "." +(cal.get(Calendar.YEAR)-2000)  + "\t\tНеделя: " + chet);
        tv.setText("День: " + Integer.toString(date.getDay()) + "\tНеделя: " + chet);

        /*boolean chet2 = (boolean) (chet % 2);*/
        chet2 = ((chet+1) % 2);


        /*List<CellSignalStrength> cellInfoList;
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        System.out.println("tm\t" + tm);
        cellInfoList = tm.getSignalStrength().getCellSignalStrengths();
        System.out.println("cellInfoList\t"+cellInfoList);
        for (CellSignalStrength cellInfo : cellInfoList) {

            System.out.println("cellInfo\t" + cellInfo);

            if (cellInfo instanceof CellSignalStrengthLte) {
                System.out.println("if");
                tv2 = (TextView) findViewById(R.id.getRsrp);
                System.out.println("tv2");
                tv2.setText("Rsrp: " + String.valueOf(((CellSignalStrengthLte) cellInfo).getRsrp()));
                tv2 = (TextView) findViewById(R.id.getRsrq);
                System.out.println("tv2");
                tv2.setText("Rsrq: " + String.valueOf(((CellSignalStrengthLte) cellInfo).getRsrq()));
                tv2 = (TextView) findViewById(R.id.getRssnr);
                System.out.println("tv2");
                tv2.setText("Rssnr: " + String.valueOf(((CellSignalStrengthLte) cellInfo).getRssnr()));

                tv2 = (TextView) findViewById(R.id.getCqi);
                tv2.setText("Cqi: " + String.valueOf(((CellSignalStrengthLte) cellInfo).getCqi()));
                System.out.println("getCqi");


            }
        }*/




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
                String strDay;
                if (today == 0){
                    today+=1;
                }

                switch (view.getId()){
                    case R.id.btnNext:
                        today += 1;
                        if (today == 7){
                            today = 1;
                            chet+=1;
                            if (chet2 == 1){
                                chet2 = 0;
                            }else {
                                chet2 = 1;
                            }
                        }
                        strDay = Integer.toString(today);
                        tv.setText("День: " + strDay + "\tНеделя: " + chet);
                        tvOut = (TextView) findViewById(R.id.textViewName1);
                        tvOut.setText(mstr[today-1][0+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet1);
                        tvOut.setText(kabinet[today-1][0+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewName2);
                        tvOut.setText(mstr[today-1][2+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet2);
                        tvOut.setText(kabinet[today-1][2+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewName3);
                        tvOut.setText(mstr[today-1][4+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet3);
                        tvOut.setText(kabinet[today-1][4+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewName4);
                        tvOut.setText(mstr[today-1][6+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet4);
                        tvOut.setText(kabinet[today-1][6+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewName5);
                        tvOut.setText(mstr[today-1][8+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet5);
                        tvOut.setText(kabinet[today-1][8+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewName6);
                        tvOut.setText(mstr[today-1][10+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet6);
                        tvOut.setText(kabinet[today-1][10+chet2]);
                        break;
                    case R.id.btnPrev:
                        today -= 1;
                        if (today == 0){
                            today = 6;
                            chet-=1;
                            if (chet2 == 1){
                                chet2 = 0;
                            }else {
                                chet2 = 1;
                            }
                        }
                        strDay = Integer.toString(today);
                        tv.setText("День: " + strDay + "\tНеделя: " + chet);
                        tvOut = (TextView) findViewById(R.id.textViewName1);
                        tvOut.setText(mstr[today-1][0+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet1);
                        tvOut.setText(kabinet[today-1][0+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewName2);
                        tvOut.setText(mstr[today-1][2+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet2);
                        tvOut.setText(kabinet[today-1][2+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewName3);
                        tvOut.setText(mstr[today-1][4+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet3);
                        tvOut.setText(kabinet[today-1][4+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewName4);
                        tvOut.setText(mstr[today-1][6+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet4);
                        tvOut.setText(kabinet[today-1][6+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewName5);
                        tvOut.setText(mstr[today-1][8+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet5);
                        tvOut.setText(kabinet[today-1][8+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewName6);
                        tvOut.setText(mstr[today-1][10+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet6);
                        tvOut.setText(kabinet[today-1][10+chet2]);

                        break;
                    case R.id.btnOk:
                        saveText();
                        EditText editText = (EditText) findViewById(R.id.edit_message);
                        String message = editText.getText().toString();
                        tvOut = (TextView) findViewById(R.id.tvOut);
                        try {
                            System.out.println("try ");
                            if (message.toUpperCase().matches("[А-Я]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}")){
                                tvOut.setText(p.find(message.toUpperCase(), mstr, kabinet));
                            }
                            else {
                                tvOut.setText("Неверный формат группы");
                                System.out.println("try is loose");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        strDay = Integer.toString(today);
                        tv.setText("День: " + strDay + "\tНеделя: " + chet);
                        tvOut = (TextView) findViewById(R.id.textViewName1);
                        tvOut.setText(mstr[today-1][0+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet1);
                        tvOut.setText(kabinet[today-1][0+chet2]);

                        tvOut = (TextView) findViewById(R.id.textViewName2);
                        tvOut.setText(mstr[today-1][2+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet2);
                        tvOut.setText(kabinet[today-1][2+chet2]);

                        tvOut = (TextView) findViewById(R.id.textViewName3);
                        tvOut.setText(mstr[today-1][4+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet3);
                        tvOut.setText(kabinet[today-1][4+chet2]);

                        tvOut = (TextView) findViewById(R.id.textViewName4);
                        tvOut.setText(mstr[today-1][6+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet4);
                        tvOut.setText(kabinet[today-1][6+chet2]);

                        tvOut = (TextView) findViewById(R.id.textViewName5);
                        tvOut.setText(mstr[today-1][8+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet5);
                        tvOut.setText(kabinet[today-1][8+chet2]);

                        tvOut = (TextView) findViewById(R.id.textViewName6);
                        tvOut.setText(mstr[today-1][10+chet2]);
                        tvOut = (TextView) findViewById(R.id.textViewCabinet6);
                        tvOut.setText(kabinet[today-1][10+chet2]);

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


    public void saveText(){
        EditText et = findViewById(R.id.edit_message);
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("SAVED_TEXT", et.getText().toString());
        ed.commit();
    }

    public void loadText(){
        EditText et = findViewById(R.id.edit_message);
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString("SAVED_TEXT", "");
        et.setText(savedText);
    }



}