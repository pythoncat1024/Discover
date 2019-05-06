package com.pycat.schedule.todo;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.python.cat.commonlib.net.domain.ScheduleInfo;

import io.reactivex.Flowable;

public class ScheduleListViewModel extends ViewModel {

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public Flowable<ScheduleInfo> getScheduleList(Context context, int type) {

        return ScheduleEngine.getScheduleList(context, type);
    }
}
