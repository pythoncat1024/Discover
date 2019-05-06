package com.python.cat.accounts.play;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.python.cat.accounts.R;
import com.python.cat.accounts.login.LoginFragment;
import com.python.cat.accounts.logout.LogoutFragment;
import com.python.cat.accounts.register.RegisterFragment;
import com.python.cat.commonlib.net.cookie.LocalCookieIO;
import com.python.cat.commonlib.utils.ToastHelper;

import java.util.Locale;
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
    private ViewGroup accountContainer;
    private Button btnExit, btnRegister, btnLogin, btnLogout;
    private TextView accountStatus;
    private Disposable disposable;
    private SharedPreferences.OnSharedPreferenceChangeListener spListener;
    private View.OnClickListener mAvatarClickListener;
    private TextView mTitleText;

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
        mTitleText = view.findViewById(R.id.account_desc);
        imgAvatar = view.findViewById(R.id.account_avatar);
        accountContainer = view.findViewById(R.id.account_container);
        btnExit = view.findViewById(R.id.btn_exit);
        btnLogin = view.findViewById(R.id.btn_login);
        btnLogout = view.findViewById(R.id.btn_logout);
        btnRegister = view.findViewById(R.id.btn_register);
        accountStatus = view.findViewById(R.id.account_status);
        accountStatus.setEnabled(false);
        spListener = (sp, key) -> {
            LogUtils.i("sp name = %s, key= %s", sp.toString(), key);
            if (sp.contains(LocalCookieIO.COOKIE_KEY)) {
                accountStatus.setEnabled(true);
                accountStatus.setText(R.string.login_done);
            } else {
                accountStatus.setEnabled(false);
                accountStatus.setText(R.string.login_undone);
            }
            refreshLayout();
        };
        PreferenceManager.getDefaultSharedPreferences(requireContext())
                .registerOnSharedPreferenceChangeListener(spListener);

        boolean contains = PreferenceManager.getDefaultSharedPreferences(requireContext())
                .contains(LocalCookieIO.COOKIE_KEY);
        if (contains) {
            accountStatus.setEnabled(true);
            accountStatus.setText(R.string.login_done);
        } else {
            accountStatus.setEnabled(false);
            accountStatus.setText(R.string.login_undone);
        }
        mTitleText.setText(String.format(Locale.getDefault(), "%s\n%s",
                getString(R.string.accounts),
                getString(R.string.click_avatar)));
        setClick();
    }

    private void refreshLayout() {
        if (mViewModel.online(requireContext())) {
            btnRegister.setEnabled(false);
            btnLogin.setEnabled(false);
            btnLogout.setEnabled(true);
        } else {
            btnRegister.setEnabled(true);
            btnLogin.setEnabled(true);
            btnLogout.setEnabled(false);
        }
    }

    private void setClick() {
        btnLogin.setOnClickListener(v -> {
            LogUtils.i("");
            toLogin();
        });

        btnRegister.setOnClickListener(v -> {
            LogUtils.i("");
            toRegister();
        });

        btnLogout.setOnClickListener(v -> {
            LogUtils.v("logout...");
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager == null) {
                ToastHelper.show(requireContext(), "fm == null");
                LogUtils.e("fm == null");
                return;
            }
            LogoutFragment.newInstance().show(fragmentManager, LogoutFragment.TAG);
        });

        btnExit.setOnClickListener(v -> {
            ToastHelper.show(requireContext(), "退出程序！！！");
            disposable = Flowable.timer(1, TimeUnit.SECONDS)
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
        btnExit.setEnabled(false);
        refreshLayout();
    }

    private void loadImg() {
        disposable = mViewModel.getImageUri().subscribe(
                play -> {
                    String url = play.results.get(0).url;
                    Glide.with(requireContext())
                            .load(url)
                            .centerCrop()
                            //                    .circleCrop()
                            .into(imgAvatar);
                },
                LogUtils::e,
                () -> imgAvatar.setOnClickListener(mAvatarClickListener));
    }

    @Override
    public void onDestroyView() {
        if (spListener != null) {
            PreferenceManager.getDefaultSharedPreferences(requireContext())
                    .unregisterOnSharedPreferenceChangeListener(spListener);
        }
        super.onDestroyView();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void setImgAvatarClick(View.OnClickListener listener) {
        this.mAvatarClickListener = listener;
    }
}
