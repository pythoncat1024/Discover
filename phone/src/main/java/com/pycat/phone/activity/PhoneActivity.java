package com.pycat.phone.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.apkfuns.logutils.LogUtils;
import com.pycat.phone.R;
import com.pycat.phone.base.BaseActivity;
import com.pycat.phone.base.PhoneApp;
import com.pycat.phone.vm.PhoneViewModel;
import com.python.cat.accounts.play.AccountsFragment;
import com.python.cat.commonlib.utils.ToastHelper;
import com.python.cat.splash.SplashFragment;
import com.python.cat.splash.funny.FunnyFragment;

import io.reactivex.disposables.Disposable;

public class PhoneActivity extends BaseActivity {

    private Fragment inUseFragment;
    private PhoneViewModel mViewModel;
    private Disposable delayDisposable;

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
        AccountsFragment fragment = AccountsFragment.newInstance();
        inUseFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerID, fragment)
                .commitAllowingStateLoss()
        ;
    }

    //    ####################################################################################
//    开始生命周期方法 { ->
//    ####################################################################################
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


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
//    ####################################################################################
//    结束生命周期方法  <- }
//    ####################################################################################


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

}
