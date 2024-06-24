package com.example.stopwatch;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatToggleButton;

import com.example.common.BaseManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class StopwatchManager extends BaseManager {
    private boolean running = false;
    private boolean paused = false;
    private long startTime = 0;
    private long pausedTime = 0;
    private Handler handler = new Handler();

    public StopwatchManager(Context context, MaterialTextView main_TXT_chronometer, AppCompatToggleButton main_BTN_toggle, MaterialButton main_BTN_stop) {
        super(context, main_TXT_chronometer, main_BTN_toggle, main_BTN_stop);
    }

    @Override
    public void setTime() {
        // Not currently relevant for the stopwatch because it counts forward from 0
    }

    @Override
    protected void onToggleButtonClicked() {
        if (running)
        {
            pauseStopwatch();
            main_BTN_toggle.setBackgroundResource(com.example.common.R.drawable.start);
        }

        else {
            startStopwatch();
            main_BTN_toggle.setBackgroundResource(com.example.common.R.drawable.pause);
        }

    }

    @Override
    protected void onStopButtonClicked() {
        stopStopwatch();
        resetStopwatch();
        main_BTN_toggle.setBackgroundResource(com.example.common.R.drawable.start);
    }

    private void startStopwatch() {
        running = true;
        paused = false;
        startTime = System.currentTimeMillis() - pausedTime;
        handler.post(updateTimeTask);
    }

    private void pauseStopwatch() {
        running = false;
        paused = true;
        pausedTime = System.currentTimeMillis() - startTime;
        handler.removeCallbacks(updateTimeTask);
    }

    private void stopStopwatch() {
        running = false;
        paused = false;
        pausedTime = 0;
        handler.removeCallbacks(updateTimeTask);
    }

    private void resetStopwatch() {
        startTime = 0;
        pausedTime = 0;
        main_TXT_chronometer.setText("00:00:00");
    }

    private Runnable updateTimeTask = new Runnable() {
        @Override
        public void run() {
            if (running) {
                long elapsedMillis = System.currentTimeMillis() - startTime;
                int minutes = (int) (elapsedMillis / 60000);
                int seconds = (int) (elapsedMillis % 60000) / 1000;
                int milliseconds = (int) (elapsedMillis % 1000 / 10);
                main_TXT_chronometer.setText(String.format("%02d:%02d:%02d", minutes, seconds, milliseconds));
                handler.postDelayed(this, 10);
            }
        }
    };
}
