package com.example.timer;

import android.app.TimePickerDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatToggleButton;

import com.example.common.BaseManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.Locale;

public class TimerManager extends BaseManager {
    private CountDownTimer countDownTimer;
    private boolean timerRunning = false;
    private long timeLeftInMillis = 60000; // 1 minute
    private MediaPlayer mediaPlayer;

    public TimerManager(Context context, MaterialTextView main_TXT_chronometer, AppCompatToggleButton main_BTN_toggle, MaterialButton main_BTN_stop) {
        super(context, main_TXT_chronometer, main_BTN_toggle, main_BTN_stop);
        mediaPlayer = MediaPlayer.create(context, com.example.timer.R.raw.finish);
    }

    @Override
    public void setTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, (TimePicker view, int hourOfDay, int minute) -> {
            timeLeftInMillis = (hourOfDay * 3600000L) + (minute * 60000L);
            updateTimer();
        }, 0, 1, true);

        timePickerDialog.show();
    }

    @Override
    protected void onToggleButtonClicked() {
        if (timerRunning) {
            pauseTimer();
            main_BTN_toggle.setBackgroundResource(com.example.common.R.drawable.start);
        }
        else {
            startTimer();
            main_BTN_toggle.setBackgroundResource(com.example.common.R.drawable.pause);
        }
    }

    @Override
    protected void onStopButtonClicked() {
        pauseTimer();
        resetTimer();
        main_BTN_toggle.setBackgroundResource(com.example.common.R.drawable.start);
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                updateTimer();
                Toast.makeText(context, "Timer finished!", Toast.LENGTH_SHORT).show();
                playFinishSound();
                main_BTN_toggle.setBackgroundResource(com.example.common.R.drawable.start);
            }
        }.start();

        timerRunning = true;
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
    }

    private void resetTimer() {
        timeLeftInMillis = 0; // 00:00:00
        updateTimer();
    }

    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 60000);
        int seconds = (int) (timeLeftInMillis % 60000) / 1000;
        int milliseconds = (int) ((timeLeftInMillis % 1000) / 10);
        main_TXT_chronometer.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", minutes, seconds, milliseconds));
    }

    private void playFinishSound() {
        if (mediaPlayer != null)
            mediaPlayer.start();
    }

    @Override
    protected void finalize() throws Throwable { // Release MediaPlayer resources when done
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.finalize();
    }
}
