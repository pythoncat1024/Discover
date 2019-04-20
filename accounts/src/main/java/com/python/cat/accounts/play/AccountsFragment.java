package com.python.cat.accounts.play;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.python.cat.accounts.R;
import com.python.cat.accounts.login.LoginFragment;
import com.python.cat.accounts.register.RegisterFragment;
import com.python.cat.commonlib.utils.ToastHelper;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * 账户相关
 */
public class AccountsFragment extends Fragment {

    private AccountsViewModel mViewModel;
    private ImageView imgAvatar;
    private ViewGroup accountCotainer;
    private Button btnExit, btnRegister, btnLogin, btnLogout;
    private TextView accountStatus;
    private Disposable disposable;

    public static AccountsFragment newInstance() {
        return new AccountsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.accounts_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgAvatar = view.findViewById(R.id.account_avatar);
        accountCotainer = view.findViewById(R.id.account_container);
        btnExit = view.findViewById(R.id.btn_exit);
        btnLogin = view.findViewById(R.id.btn_login);
        btnLogout = view.findViewById(R.id.btn_logout);
        btnRegister = view.findViewById(R.id.btn_register);
        accountStatus = view.findViewById(R.id.account_status);
        setClick();
    }

    private void setClick() {
        btnLogin.setOnClickListener(v -> {
            toLogin();
        });

        btnRegister.setOnClickListener(v -> {
            toRegister();
        });

        btnLogout.setOnClickListener(v -> {
            ToastHelper.show(requireContext(), "界面太多，不实现了");
            LogUtils.v("logout...");
        });

        btnExit.setOnClickListener(v -> {
            ToastHelper.show(requireContext(), "退出程序！！！");
            Disposable subscribe = Flowable.timer(1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(LogUtils::v, LogUtils::e, () -> {
                        requireActivity().finish();
                        System.exit(0);
                    });

        });
    }

    private void toRegister() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null) {
            ToastHelper.show(requireContext(), "fm == null");
            LogUtils.e("fm == null");
            return;
        }
        RegisterFragment.newInstance().show(fragmentManager, RegisterFragment.TAG);
    }

    private void toLogin() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null) {
            ToastHelper.show(requireContext(), "fm == null");
            LogUtils.e("fm == null");
            return;
        }
        LoginFragment.newInstance().show(fragmentManager, LoginFragment.TAG);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AccountsViewModel.class);
        loadImg();
        btnExit.setEnabled(true);
        if (mViewModel.online()) {
            btnRegister.setEnabled(false);
            btnLogin.setEnabled(false);
            btnExit.setEnabled(true);
        } else {
            btnRegister.setEnabled(true);
            btnLogin.setEnabled(true);
            btnExit.setEnabled(false);

        }
    }

    private void loadImg() {
        disposable = mViewModel.getImageUri().subscribe(play -> {
            String url = play.results.get(0).url;
            Glide.with(requireContext())
                    .load(url)
                    .centerCrop()
                    //                    .circleCrop()
                    .into(imgAvatar);
        }, LogUtils::e);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
