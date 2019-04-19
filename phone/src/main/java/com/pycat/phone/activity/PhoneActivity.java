package com.pycat.phone.activity;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pycat.phone.R;
import com.pycat.phone.base.BaseActivity;
import com.python.cat.splash.SplashFragment;

public class PhoneActivity extends BaseActivity {

    private Fragment inUseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        Fragment splashF = inUseFragment = SplashFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_phone_root,
                        splashF,
                        SplashFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (inUseFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(inUseFragment)
                    .commitNowAllowingStateLoss();
        }
    }
}
