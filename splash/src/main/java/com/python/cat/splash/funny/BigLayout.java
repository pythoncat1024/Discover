package com.python.cat.splash.funny;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BigLayout extends FrameLayout {

    private boolean disableTouch;


    public void setDisableTouch(boolean disableTouch) {
        this.disableTouch = disableTouch;
    }

    public BigLayout(@NonNull Context context) {
        super(context);
    }

    public BigLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BigLayout(@NonNull Context context,
                     @Nullable AttributeSet attrs,
                     int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BigLayout(@NonNull Context context, @Nullable AttributeSet attrs,
                     int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 不处理 touch 让 child 处理
        if (disableTouch) {
            return false;
        } else {
            return super.onTouchEvent(event);
        }
    }
}
