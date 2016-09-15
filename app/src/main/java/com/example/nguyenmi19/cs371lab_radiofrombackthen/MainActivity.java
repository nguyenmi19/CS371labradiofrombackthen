package com.example.nguyenmi19.cs371lab_radiofrombackthen;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnLongClickListener {

    ToggleButton toggleButton;
    TextView stationDisplay;
    Switch AMFM;
    Button presetButton1;
    Button presetButton2;
    Button presetButton3;
    Button presetButton4;
    Button presetButton5;
    Button presetButton6;

    SeekBar tunerAMFM;

    boolean stateAMFM;
    double rangeFM;
    int rangeAM;


    int[] presetArrayAM = {550, 600, 650, 700, 750, 800};
    double[] presetArrayFM = {90.9, 92.9, 94.9, 96.9, 98.9, 100.9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //on-off button
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        //station display object
        stationDisplay = (TextView) findViewById(R.id.textView);

        //am and fm switch
        AMFM = (Switch) findViewById(R.id.switch1);

        //station preset buttons 1-6
        presetButton1 = (Button) findViewById(R.id.button);
        presetButton2 = (Button) findViewById(R.id.button2);
        presetButton3 = (Button) findViewById(R.id.button3);
        presetButton4 = (Button) findViewById(R.id.button4);
        presetButton5 = (Button) findViewById(R.id.button5);
        presetButton6 = (Button) findViewById(R.id.button6);

        tunerAMFM = (SeekBar) findViewById(R.id.seekBar);
        tunerAMFM.setOnSeekBarChangeListener(this);

        rangeFM = 88.1;
        rangeAM = 530;

        presetButton1.setOnLongClickListener(this);
        presetButton2.setOnLongClickListener(this);
        presetButton3.setOnLongClickListener(this);
        presetButton4.setOnLongClickListener(this);
        presetButton5.setOnLongClickListener(this);
        presetButton6.setOnLongClickListener(this);


    }


    public void onClickBtn(View v) {
        Log.i("hello", "hello");

        if (v.equals(toggleButton)) {
            if (toggleButton.isChecked()) {
                stationDisplay.setTextColor(Color.WHITE);
                AMFM.setTextColor(Color.WHITE);
                presetButton1.setBackgroundColor(Color.WHITE);
                presetButton2.setBackgroundColor(Color.WHITE);
                presetButton3.setBackgroundColor(Color.WHITE);
                presetButton4.setBackgroundColor(Color.WHITE);
                presetButton5.setBackgroundColor(Color.WHITE);
                presetButton6.setBackgroundColor(Color.WHITE);

            } else {

                stationDisplay.setTextColor(Color.BLACK);
                AMFM.setTextColor(Color.BLACK);
                presetButton1.setBackgroundColor(Color.BLACK);
                presetButton2.setBackgroundColor(Color.BLACK);
                presetButton3.setBackgroundColor(Color.BLACK);
                presetButton4.setBackgroundColor(Color.BLACK);
                presetButton5.setBackgroundColor(Color.BLACK);
                presetButton6.setBackgroundColor(Color.BLACK);

            }
        }
    }

    public void AMFMbtn(View v) {

        if (v.equals(AMFM)) {
            if (AMFM.isChecked()) {  //if button is on FM
                stateAMFM = true;
                onProgressChanged(tunerAMFM, 0, true);
                tunerAMFM.incrementProgressBy(2);
                tunerAMFM.setMax((1079 - 881));
            } else {
                stateAMFM = false;   //if button is on AM
                onProgressChanged(tunerAMFM, 0, false);
                tunerAMFM.incrementProgressBy(10);
                tunerAMFM.setMax(1700 - 530);

            }
        }
    }

    double stepFM = 2;
    int stepAM = 10;

    public void onProgressChanged(SeekBar tuner, int progress, boolean fromUser) {
        String stringAMFM;
        if (stateAMFM)
        {
            stringAMFM = "FM";
            if (progress % 2 == 0)
            {
                progress--;
            }

        } else {
            stringAMFM = "AM";
            progress -= progress % 10;
        }



        if (tuner.equals(tunerAMFM)) {  //FM
            if (stateAMFM) {
                progress = ((int)Math.round(((int)(progress/stepFM))*stepFM));
                rangeFM = ((progress + 881.0) / 10);
                stationDisplay.setText(Double.toString(rangeFM) + stringAMFM);
            } else {   //AM
                progress = ((int)Math.round(progress/stepAM))*stepAM;
                rangeAM = progress + 530;
                stationDisplay.setText(Integer.toString(rangeAM) + stringAMFM);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void presetBtn1(View v) {
        if (v.equals(presetButton1)) {
            if (stateAMFM) {  //FM
                rangeFM = presetArrayFM[0];
                stationDisplay.setText(Double.toString(rangeFM) + "FM");
            } else { //AM
                rangeAM = presetArrayAM[0];
                stationDisplay.setText(Double.toString(rangeAM) + "AM");
            }
        }
    }
    public void presetBtn2(View v) {
        if (v.equals(presetButton2)) {
            if (stateAMFM) {  //FM
                rangeFM = presetArrayFM[1];
                stationDisplay.setText(Double.toString(rangeFM) + "FM");
            } else { //AM
                rangeAM = presetArrayAM[1];
                stationDisplay.setText(Double.toString(rangeAM) + "AM");
            }
        }
    }
    public void presetBtn3(View v) {
        if (v.equals(presetButton3)) {
            if (stateAMFM) {  //FM
                rangeFM = presetArrayFM[2];
                stationDisplay.setText(Double.toString(rangeFM) + "FM");
            } else { //AM
                rangeAM = presetArrayAM[2];
                stationDisplay.setText(Double.toString(rangeAM) + "AM");
            }
        }
    }
    public void presetBtn4(View v) {
        if (v.equals(presetButton4)) {
            if (stateAMFM) {  //FM
                rangeFM = presetArrayFM[3];
                stationDisplay.setText(Double.toString(rangeFM) + "FM");
            } else { //AM
                rangeAM = presetArrayAM[3];
                stationDisplay.setText(Double.toString(rangeAM) + "AM");
            }
        }
    }
    public void presetBtn5(View v) {
        if (v.equals(presetButton5)) {
            if (stateAMFM) {  //FM
                rangeFM = presetArrayFM[4];
                stationDisplay.setText(Double.toString(rangeFM) + "FM");
            } else { //AM
                rangeAM = presetArrayAM[4];
                stationDisplay.setText(Double.toString(rangeAM) + "AM");
            }
        }
    }
    public void presetBtn6(View v) {
        if (v.equals(presetButton6)) {
            if (stateAMFM) {  //FM
                rangeFM = presetArrayFM[5];
                stationDisplay.setText(Double.toString(rangeFM) + "FM");
            } else { //AM
                rangeAM = presetArrayAM[5];
                stationDisplay.setText(Double.toString(rangeAM) + "AM");
            }
        }
    }


    @Override
    public boolean onLongClick(View v) {
        if (v.equals(presetButton1)) {
            if (stateAMFM) {  //FM
                presetArrayFM[0] = rangeFM;
            } else {  //AM
                presetArrayAM[0] = rangeAM;
            }
        }
        if (v.equals(presetButton2)) {
            if (stateAMFM) {  //FM
                presetArrayFM[1] = rangeFM;
            } else {  //AM
                presetArrayAM[1] = rangeAM;
            }
        }
        if (v.equals(presetButton3)) {
            if (stateAMFM) {  //FM
                presetArrayFM[2] = rangeFM;
            } else {  //AM
                presetArrayAM[2] = rangeAM;
            }
        }
        if (v.equals(presetButton4)) {
            if (stateAMFM) {  //FM
                presetArrayFM[3] = rangeFM;
            } else {  //AM
                presetArrayAM[3] = rangeAM;
            }
        }
        if (v.equals(presetButton5)) {
            if (stateAMFM) {  //FM
                presetArrayFM[4] = rangeFM;
            } else {  //AM
                presetArrayAM[4] = rangeAM;
            }
        }
        if (v.equals(presetButton6)) {
            if (stateAMFM) {  //FM
                presetArrayFM[5] = rangeFM;
            } else {  //AM
                presetArrayAM[5] = rangeAM;
            }
        }
        return false;
    }
}

