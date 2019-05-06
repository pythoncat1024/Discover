package com.pycat.schedule.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.apkfuns.logutils.LogUtils;
import com.pycat.schedule.R;
import com.python.cat.commonlib.utils.ToastHelper;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

public class ScheduleListFragment extends Fragment {

    private ScheduleListViewModel mViewModel;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressbar;
    private SearchView mSearchView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private Disposable disposable;

    public ScheduleListFragment() {
    }

    public static ScheduleListFragment newInstance() {

        Bundle args = new Bundle();
        ScheduleListFragment fragment = new ScheduleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = view.findViewById(R.id.schedule_refresh_layout);
        mRecyclerView = view.findViewById(R.id.schedule_rv_layout);
        mProgressbar = view.findViewById(R.id.schedule_loading_progress);
        mSearchView = view.findViewById(R.id.schedule_search);
        mToolbar = view.findViewById(R.id.schedule_toolbar);
        mTitle = view.findViewById(R.id.schedule_title);
        mToolbar.setNavigationOnClickListener(v -> {
            LogUtils.i("click back...");
            LogUtils.i("click back...");
            ToastHelper.show(requireContext(), "click back..");

        });
        mTitle.setOnClickListener(v -> {
            LogUtils.i("click title...");
            LogUtils.i("click title...");
            ToastHelper.show(requireContext(), "click title..");
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ScheduleListViewModel.class);
        LogUtils.e("prepare load schedule list from net.");
        disposable = Flowable.concat(
                mViewModel.getScheduleList(requireContext(), 0),
                mViewModel.getScheduleList(requireContext(), 1),
                mViewModel.getScheduleList(requireContext(), 2),
                mViewModel.getScheduleList(requireContext(), 3)
        )
                .doOnSubscribe(p -> LogUtils.e("start to load schedule list from net."))
                .subscribe(ss -> {
                    LogUtils.e(ss.getClass().getName() + "@" + ss.hashCode());
                    LogUtils.i(ss);
                }, e -> {
                    LogUtils.e(e);
                    LogUtils.i(e);
                }, () -> {
                    LogUtils.e("get schedule from net completed...");
                });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
