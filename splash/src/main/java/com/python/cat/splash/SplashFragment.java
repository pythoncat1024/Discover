package com.python.cat.splash;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.apkfuns.logutils.LogUtils;

import io.reactivex.disposables.Disposable;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    public static final String TAG = "SplashFragment-TAG";
    private ViewPager2 mViewPager2;
    private SplashViewModel mViewModel;

    private final int pageSize = 1 + 2 + 3;
    private Disposable disposable;

    private Runnable done;
    private PagerAdapter mAdapter;

    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment newInstance() {

        Bundle args = new Bundle();

        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager2 = view.findViewById(R.id.splash_viewpager2);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        LogUtils.d("page size %s", pageSize);
        disposable = mViewModel.getSplash(pageSize)
                .subscribe(splash -> {
                            mAdapter = new PagerAdapter(splash);
                            mViewPager2.setAdapter(mAdapter);
                            LogUtils.v(splash);
                            mAdapter.setCompleteListener(done);
                        },
                        LogUtils::e,
                        () -> {
                            LogUtils.v("get splash data done.");
                            LogUtils.i("get splash data done.");

                        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null
                && !disposable.isDisposed()) {
            disposable.dispose();
        }
        if (mViewModel != null) {
            LogUtils.v(mViewModel.hashCode());
        }
    }

    public void setCompleteListener(Runnable runnable) {
        this.done = runnable;
    }

}

