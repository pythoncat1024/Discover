package com.pycat.phone.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.apkfuns.logutils.LogUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState,
                         @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        LogUtils.d("");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("");
    }

    protected Activity get() {
        if (isDestroyed() || isFinishing()) {
            LogUtils.w("activity may be null or let memory leak ###");
        }
        return this;
    }
}
