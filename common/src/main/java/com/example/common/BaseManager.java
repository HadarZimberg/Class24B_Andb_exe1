package com.example.common;

import android.content.Context;

import androidx.appcompat.widget.AppCompatToggleButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public abstract class BaseManager {
    protected MaterialTextView main_TXT_chronometer;
    protected AppCompatToggleButton main_BTN_toggle;
    protected MaterialButton main_BTN_stop;
    protected Context context;

    public BaseManager(Context context, MaterialTextView main_TXT_chronometer, AppCompatToggleButton main_BTN_toggle, MaterialButton main_BTN_stop) {
        this.context = context;
        this.main_TXT_chronometer = main_TXT_chronometer;
        this.main_BTN_toggle = main_BTN_toggle;
        this.main_BTN_stop = main_BTN_stop;
    }

    public abstract void setTime();
    protected abstract void onToggleButtonClicked();
    protected abstract void onStopButtonClicked();
}
