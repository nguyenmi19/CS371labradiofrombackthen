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

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

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

    int[] AM;
    double[] FM;

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

    



}

