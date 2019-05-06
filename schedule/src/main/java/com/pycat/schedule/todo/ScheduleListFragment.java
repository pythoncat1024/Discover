package com.pycat.schedule.todo;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.apkfuns.logutils.LogUtils;
import com.pycat.schedule.R;
import com.python.cat.commonlib.net.domain.ScheduleBean;
import com.python.cat.commonlib.net.domain.ScheduleInfo;
import com.python.cat.commonlib.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

public class ScheduleListFragment extends Fragment {

    private List<ScheduleInfo> allSchedules;
    private ScheduleListViewModel mViewModel;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressbar;
    private SearchView mSearchView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private Disposable disposable;
    private View.OnClickListener mBackListener;
    private ScheduleAdapter mScheduleAdapter;

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
        setClick();
        mSwipeRefreshLayout.setOnRefreshListener(this::asyncLoadFromNet);
    }

    private void setClick() {
        mToolbar.setNavigationOnClickListener(v -> {
            LogUtils.i("click back...");
            LogUtils.i("click back...");
            ToastHelper.show(requireContext(), "click back..");
            if (mBackListener != null) {
                mBackListener.onClick(v);
            }
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
        asyncLoadFromNet();
    }

    private void asyncLoadFromNet() {
        disposable = Flowable.concat(
                mViewModel.getScheduleList(requireContext(), 0),
                mViewModel.getScheduleList(requireContext(), 1),
                mViewModel.getScheduleList(requireContext(), 2),
                mViewModel.getScheduleList(requireContext(), 3)
        )
                .doOnSubscribe(p -> {
                    LogUtils.e("start to load schedule list from net.");
                    if (allSchedules == null) {
                        allSchedules = new ArrayList<>();
                    } else {
                        allSchedules.clear();
                    }
                })
                .subscribe(ss -> {
                    LogUtils.e(ss.getClass().getName() + "@" + ss.hashCode());
                    LogUtils.i(ss);
                    allSchedules.add(ss);

                }, e -> {
                    LogUtils.e(e);
                    LogUtils.i(e);
                }, () -> {
                    LogUtils.e("get schedule from net completed...");
                    if (allSchedules == null || allSchedules.size() == 0) {
                        LogUtils.e("data empty!!!");

                    } else {
                        mProgressbar.setVisibility(View.GONE);
                        if (mScheduleAdapter == null) {
                            loadScheduleData2UI();
                        } else {
                            refreshScheduleData2UI();
                        }
                    }
                });
    }

    private void refreshScheduleData2UI() {
        List<ScheduleBean> scheduleBeans = adapterDataList(allSchedules);
        mScheduleAdapter.setScheduleList(scheduleBeans);
        mScheduleAdapter.notifyDataSetChanged();
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private void loadScheduleData2UI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mScheduleAdapter = new ScheduleAdapter();
        final List<ScheduleBean> scheduleBeans = adapterDataList(allSchedules);
        LogUtils.w(scheduleBeans);
        LogUtils.w(scheduleBeans.size() + " ,,,size,,,");
        mScheduleAdapter.setScheduleList(scheduleBeans);
        mRecyclerView.setAdapter(mScheduleAdapter);
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private List<ScheduleBean> adapterDataList(List<ScheduleInfo> allSchedules) {
        final List<ScheduleBean> scheduleBeans = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            allSchedules.forEach(scheduleInfo -> {
                if (scheduleInfo.data != null) {
                    List<ScheduleInfo.DataBean.ScheduleListBean> doneList = scheduleInfo.data.doneList;
                    if (doneList != null) {
                        doneList.forEach(bean -> {
                            List<ScheduleBean> list = bean.todoList;
                            if (list != null) {
                                scheduleBeans.addAll(list);
                            }
                        });
                    }
                    List<ScheduleInfo.DataBean.ScheduleListBean> todoList = scheduleInfo.data.todoList;
                    if (todoList != null) {
                        todoList.forEach(bean -> {
                            List<ScheduleBean> list = bean.todoList;
                            if (list != null) {
                                scheduleBeans.addAll(list);
                            }
                        });
                    }
                }
            });
        } else {
            for (ScheduleInfo info : allSchedules) {
                if (info != null && info.data != null) {
                    List<ScheduleInfo.DataBean.ScheduleListBean> doneList = info.data.doneList;
                    List<ScheduleInfo.DataBean.ScheduleListBean> todoList = info.data.todoList;
                    if (doneList != null) {
                        for (ScheduleInfo.DataBean.ScheduleListBean sb : doneList) {
                            if (sb != null && sb.todoList != null) {
                                scheduleBeans.addAll(sb.todoList);
                            }
                        }
                    }
                    if (todoList != null) {
                        for (ScheduleInfo.DataBean.ScheduleListBean sb : todoList) {
                            if (sb != null && sb.todoList != null) {
                                scheduleBeans.addAll(sb.todoList);
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(scheduleBeans, (o1, o2) -> {
            long x = o1.date;
            long y = o2.date;
            return Long.compare(y, x); // 按时间倒序
        });
        return scheduleBeans;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void setTitleLeftClick(View.OnClickListener leftClick) {
        this.mBackListener = leftClick;
    }

}
