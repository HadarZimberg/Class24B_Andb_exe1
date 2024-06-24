package com.example.common;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatToggleButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public abstract class BaseMainActivity extends AppCompatActivity {
    protected MaterialTextView main_TXT_chronometer;
    protected AppCompatToggleButton main_BTN_toggle;
    protected MaterialButton main_BTN_stop;
    protected BaseManager baseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.common.R.layout.activity_main_base);

        findViews();
        main_TXT_chronometer.setText("00:00:00");
        initViews();
        baseManager = createManager();
    }

    protected abstract BaseManager createManager();

    private void initViews() {
        main_TXT_chronometer.setOnClickListener(v -> baseManager.setTime());
        main_BTN_toggle.setOnClickListener(v -> baseManager.onToggleButtonClicked());
        main_BTN_stop.setOnClickListener(v -> baseManager.onStopButtonClicked());
    }

    private void findViews() {
        main_TXT_chronometer = findViewById(R.id.main_TXT_chronometer);
        main_BTN_toggle = findViewById(R.id.main_BTN_toggle);
        main_BTN_stop = findViewById(R.id.main_BTN_stop);
    }
}