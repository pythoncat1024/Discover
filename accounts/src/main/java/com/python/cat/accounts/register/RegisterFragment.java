package com.python.cat.accounts.register;

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

public class RegisterFragment extends DialogFragment {

    private RegisterViewModel mViewModel;

    public RegisterFragment() {
    }


    public static final String TAG = "RegisterFragment-TAG";
    private EditText mEditUsername;
    private EditText mEditPassword;
    private EditText mEditRePassword;

    private CheckBox mCheckRemember;
    private Disposable disposable;


    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditUsername = view.findViewById(R.id.edit_username);
        mEditPassword = view.findViewById(R.id.edit_password);
        mEditRePassword = view.findViewById(R.id.edit_re_password);
        mCheckRemember = view.findViewById(R.id.checkbox_remember);
        ViewGroup mRememberLayout = view.findViewById(R.id.remember_rl);
        View mBtnRegister = view.findViewById(R.id.btn_register_now);

        mEditUsername.clearFocus();
        mEditPassword.clearFocus();
        view.requestFocus();

        mRememberLayout.setOnClickListener(v -> {
            mCheckRemember.setChecked(!mCheckRemember.isChecked());
        });

        mBtnRegister.setOnClickListener(v -> {
            clickRegister();
        });
    }

    private void clickRegister() {
        String username = mEditUsername.getText().toString();
        String password = mEditPassword.getText().toString();
        String rePassword = mEditRePassword.getText().toString();


        disposable = mViewModel.register(requireContext(), username, password, rePassword)
                .subscribe(rs -> {
                    if (rs.errorCode == 0) {
                        ToastHelper.show(requireContext(), "register success: " + username);
                        if (!mCheckRemember.isChecked()) {
                            LocalCookieIO.clearCookie(requireContext());
                        }
                    } else {
                        ToastHelper.show(requireContext(), rs.errorMsg);
                    }
                }, LogUtils::e, this::dismiss);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
