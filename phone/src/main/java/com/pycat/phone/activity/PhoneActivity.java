package com.pycat.phone.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.apkfuns.logutils.LogUtils;
import com.pycat.phone.R;
import com.pycat.phone.base.BaseActivity;
import com.pycat.phone.base.PhoneApp;
import com.pycat.phone.vm.PhoneViewModel;
import com.python.cat.commonlib.utils.ToastHelper;
import com.python.cat.splash.SplashFragment;
import com.python.cat.splash.funny.FunnyFragment;

import io.reactivex.disposables.Disposable;

public class PhoneActivity extends BaseActivity {

    private Fragment inUseFragment;
    private PhoneViewModel mViewModel;
    private Disposable delayDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        mViewModel = ViewModelProviders.of(this).get(PhoneViewModel.class);

        int containerID = R.id.activity_phone_root;
        if (!mViewModel.splashDone()) {
            loadWelcome(containerID);
        } else {
            // load normal fragment
            LogUtils.e("load normal ui");
            ToastHelper.show(PhoneApp.get(), "load normal...");
            loadNormal(containerID);
        }

    }

    private void loadWelcome(int containerID) {
        SplashFragment splashF = SplashFragment.newInstance();
        inUseFragment = splashF;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerID,
                        splashF,
                        SplashFragment.TAG)
                .commitAllowingStateLoss();

        splashF.setCompleteListener(
                () -> {
                    LogUtils.v("欢迎页完成。。。");
                    mViewModel.setSplashDone();
                    //                        delayDisposable = mViewModel.delay(1, t -> finish());
                    loadNormal(containerID);
                });
    }

    private void loadNormal(int containerID) {
        FunnyFragment fragment = FunnyFragment.newInstance();
        inUseFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerID, fragment)
                .commitAllowingStateLoss()
        ;
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

        if (delayDisposable != null && !delayDisposable.isDisposed()) {
            delayDisposable.dispose();
        }

        ToastHelper.cancel();
    }
}
