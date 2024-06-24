package com.example.timer;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.common.BaseMainActivity;
import com.example.common.BaseManager;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends BaseMainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseManager createManager() {
        return new TimerManager(this, main_TXT_chronometer, main_BTN_toggle, main_BTN_stop);
    }
}