package com.python.cat.accounts.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.apkfuns.logutils.LogUtils;
import com.python.cat.accounts.R;
import com.python.cat.commonlib.net.cookie.LocalCookieIO;
import com.python.cat.commonlib.utils.ToastHelper;

import io.reactivex.disposables.Disposable;

public class LoginFragment extends DialogFragment {

    public static final String TAG = "LoginFragment-TAG";
    private EditText mEditUsername;
    private EditText mEditPassword;

    private CheckBox mCheckRemember;
    private ViewGroup mRememberLayout;
    private View mBtnLogin;
    private LoginViewModel mViewModel;
    private Disposable disposable;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditUsername = view.findViewById(R.id.edit_username);
        mEditPassword = view.findViewById(R.id.edit_password);
        mCheckRemember = view.findViewById(R.id.checkbox_remember);
        mRememberLayout = view.findViewById(R.id.remember_rl);
        mBtnLogin = view.findViewById(R.id.btn_login_now);

        mEditUsername.clearFocus();
        mEditPassword.clearFocus();
        view.requestFocus();
        final String username = "pythoncat";
        final String password = "wanandroid123";
        mEditUsername.setText(username);
        mEditUsername.setSelection(username.length());
        mEditPassword.setText(password);
        mEditPassword.setSelection(password.length());
        mRememberLayout.setOnClickListener(v -> {
            mCheckRemember.setChecked(!mCheckRemember.isChecked());
        });

        mBtnLogin.setOnClickListener(v -> {
            clickLogin();
        });
    }

    private void clickLogin() {
        String un = mEditUsername.getText().toString();
        String pw = mEditPassword.getText().toString();
//        Disposable subscribe = WanRequest.getInstance().login(requireContext(), un, pw)
//                .subscribe(LogUtils::e, LogUtils::w);
        disposable = mViewModel.login(requireContext(), un, pw)
                .subscribe(rs -> {
                    if (rs.errorCode == 0) {
                        ToastHelper.show(requireContext(), "login success");
                        if (!mCheckRemember.isChecked()) {
                            LocalCookieIO.clearCookie(requireContext());
                        }
                    } else {
                        ToastHelper.show(requireContext(), rs.errorMsg);
                    }
                }, LogUtils::w, this::dismiss);
        LogUtils.i(disposable);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
