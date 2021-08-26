package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.provider.SyncStateContract.Helpers.update;

public class PomodoroTimer extends AppCompatActivity {

    ImageView iv_start;
    ImageView iv_cancel;
    TextView tv_timer;
    int hour, minute;
    boolean timerStarted = false;

    CountDownTimer countDownTimer;
    Double remainingTime = 20.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_timer);
        tv_timer = findViewById(R.id.tv_time);
        iv_start = findViewById(R.id.btn_start);
        iv_cancel = findViewById(R.id.btn_cancel);
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timepicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                tv_timer.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));

            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle(String.valueOf(remainingTime));
        timePickerDialog.show();

    }

    public void cancelTapped(View view) {
        reset();
    }

    public void startTapped(View view) {
        if(timerStarted == false)
        {
            timerStarted = true;
            iv_start.setVisibility(View.INVISIBLE);
            iv_cancel.setVisibility((View.VISIBLE));
            countDownTimer = new CountDownTimer( 60 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    update((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    reset();
                    tv_timer.setText("Finished");
                }
            }.start();

        }
        else
        {
            reset();
        }
    }

    private void update(int progress) {
        int minutes = progress / 60;
        int seconds = progress % 60;
        String secondsFinal = "";
        if (seconds <= 9)
        {
            secondsFinal = "0" + seconds;
        }
        else
        {
            secondsFinal = "" + seconds;
        }

        tv_timer.setText("" + minutes + ":" + secondsFinal);


    }

    private void reset() {
        tv_timer.setText("60:00");
        countDownTimer.cancel();
        iv_start.setVisibility(View.VISIBLE);
        iv_cancel.setVisibility(View.INVISIBLE);
        timerStarted = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(timerStarted) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timerStarted) {
            countDownTimer.cancel();
        }
    }
}