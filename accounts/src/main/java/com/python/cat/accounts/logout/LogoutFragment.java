package com.python.cat.accounts.logout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.apkfuns.logutils.LogUtils;
import com.python.cat.accounts.R;
import com.python.cat.commonlib.net.cookie.LocalCookieIO;
import com.python.cat.commonlib.utils.ToastHelper;

import io.reactivex.disposables.Disposable;

public class LogoutFragment extends DialogFragment {

    public static final String TAG = "LogoutFragment-TAG";
    private LogoutViewModel mViewModel;
    private Disposable disposable;

    public LogoutFragment() {
    }


    public static LogoutFragment newInstance() {
        Bundle args = new Bundle();
        LogoutFragment fragment = new LogoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.logout_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_logout_now)
                .setOnClickListener(v -> {
                    disposable = mViewModel.logout(requireContext())
                            .subscribe(rs -> {
                                if (rs.errorCode == 0) {
                                    ToastHelper.show(requireContext(), "logout success");
                                    LocalCookieIO.clearCookie(requireContext());
                                } else {
                                    ToastHelper.show(requireContext(), rs.errorMsg);
                                }
                            }, LogUtils::w, this::dismiss);
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LogoutViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
