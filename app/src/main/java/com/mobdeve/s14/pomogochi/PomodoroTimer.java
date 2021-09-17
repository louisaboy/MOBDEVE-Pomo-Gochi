package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class PomodoroTimer extends AppCompatActivity {

    private ImageView btnStart;
    private ImageView btnCancel;
    private TextView tvTime;
    private String finaltime;
    private boolean timerStarted = false;
    private ImageView ivTimer;
    private ImageView ivTodo;
    private ImageView ibShop;
    private ImageView ibSettings;
    private ImageView ivHome;
    private TextView tvMoney;
    private CountDownTimer countDownTimer;
    private Double remainingTime = 20.00;

    private int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_timer);
        tvTime = findViewById(R.id.tv_time);
        btnStart = findViewById(R.id.btn_start);
        btnCancel = findViewById(R.id.btn_cancel);

        ivTimer = findViewById(R.id.iv_timer);
        ivTodo = findViewById(R.id.iv_todo);
        ibShop = findViewById(R.id.ib_shop);
        ibSettings = findViewById(R.id.ib_settings);
        ivHome = findViewById(R.id.iv_home);
        tvMoney = findViewById(R.id.tv_money);

        tvMoney.setText(String.valueOf(MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY)));
    }

    // creates the Dialog for the Pomodoro Timer
    public void popTimePicker(View view) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(PomodoroTimer.this);
            View mView = getLayoutInflater().inflate(R.layout.dialog_time, null);
            final NumberPicker num1 = (NumberPicker) mView.findViewById(R.id.tv_num1);
            final NumberPicker num2 = (NumberPicker) mView.findViewById(R.id.tv_num2);
            Button confirm = (Button) mView.findViewById(R.id.b_confirm);

            String [] stime = tvTime.getText().toString().split(":");
            String minute = stime[0];
            String second = stime[1];

            num1.setMaxValue(60);
            num1.setMinValue(0);
            num2.setMaxValue(60);
            num2.setMinValue(0);
            num1.setValue(Integer.parseInt(minute));
            num2.setValue(Integer.parseInt(second));
            mBuilder.setView(mView);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();

            // confirms that the time is valid
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int valuePicker1 = num1.getValue();
                    int valuePicker2 = num2.getValue();

                    finaltime = "";

                    if (valuePicker1 < 10)
                        finaltime += "0" + String.valueOf(valuePicker1);
                    else
                        finaltime += String.valueOf(valuePicker1);

                    finaltime += ":";

                    if (valuePicker2 < 10)
                        finaltime += "0" + String.valueOf(valuePicker2);
                    else
                        finaltime += String.valueOf(valuePicker2);

                    tvTime.setText(finaltime);
                    dialog.dismiss();
                    if (timerStarted == true)
                    {
                        reset();
                    }

                }
            });


    }

    // when user cancels the timer
    public void cancelTapped(View view) {
        reset();
    }

    // when user starts the tiemr
    public void startTapped(View view) {
        String [] stime = tvTime.getText().toString().split(":");
        String s1 = stime[0];
        String s2 = stime[1];
        int num1 = Integer.parseInt(s1);
        int num2 = Integer.parseInt(s2);

        if (num1 != 0 || num2 != 0)
        {
            if(timerStarted == false)
            {
                timerStarted = true;
                btnStart.setVisibility(View.INVISIBLE);
                btnCancel.setVisibility((View.VISIBLE));

                countDownTimer = new CountDownTimer( (num1 * 60 + num2) * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        update((int) millisUntilFinished / 1000);
                    }

                    @Override
                    public void onFinish() {
                        reset();
                        AlertDialog finishDialog = new AlertDialog.Builder(PomodoroTimer.this).create();
                        finishDialog.setTitle("Alert");
                        finishDialog.setMessage("You Completed A Session!");
                        finishDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        money = num1 * 60 + num2;
                        Log.d("MONEY", String.valueOf(money));



                        tvMoney.setText(String.valueOf(Integer.parseInt(tvMoney.getText().toString()) + money));
                        int total_money = MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY) + money;
                        tvMoney.setText(String.valueOf(total_money));
                        MainActivity.informationStorage.setCurrency(MainActivity.informationStorage.CURRENCY, total_money);
                        finishDialog.show();

                        tvTime.setText("00:00");

                    }
                }.start();

            }
            else
            {
                reset();
            }
        }
        else
        {
            AlertDialog invalidDialog = new AlertDialog.Builder(PomodoroTimer.this).create();
            invalidDialog.setTitle("Alert");
            invalidDialog.setMessage("Set A Valid Time");
            invalidDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            invalidDialog.show();
        }

    }

    // run-time updates on the timer
    private void update(int progress) {
        int minutes = progress / 60;
        int seconds = progress % 60;
        String secondsFinal = "";
        String minutesFinal = "";

        if (minutes <= 9)
        {
            minutesFinal = "0" + minutes;
        }
        else
        {
            minutesFinal = "" + minutes;
        }

        if (seconds <= 9)
        {
            secondsFinal = "0" + seconds;
        }
        else
        {
            secondsFinal = "" + seconds;
        }

        tvTime.setText(minutesFinal + ":" + secondsFinal);


    }

    // when the user has finished the session or user wants to reset
    private void reset() {
        tvTime.setText(finaltime);
        countDownTimer.cancel();
        btnStart.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);
        timerStarted = false;
    }

    private void reset(String value) {
        tvTime.setText("60:00");
        countDownTimer.cancel();
        btnStart.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);
        timerStarted = false;
    }


    // timer Pause
    @Override
    protected void onPause() {
        super.onPause();
        if(timerStarted) {
            countDownTimer.cancel();
        }
    }

    // timer refresh or destroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timerStarted) {
            countDownTimer.cancel();
        }
    }
}