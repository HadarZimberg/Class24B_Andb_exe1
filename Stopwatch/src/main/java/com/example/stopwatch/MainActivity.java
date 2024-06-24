package com.example.stopwatch;

import android.os.Bundle;

import com.example.common.BaseMainActivity;
import com.example.common.BaseManager;

public class MainActivity extends BaseMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected BaseManager createManager() {
        return new StopwatchManager(this, main_TXT_chronometer, main_BTN_toggle, main_BTN_stop);
    }
}