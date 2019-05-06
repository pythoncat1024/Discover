package com.pycat.schedule.todo;

import androidx.lifecycle.ViewModel;

import com.python.cat.commonlib.net.domain.ScheduleInfo;

import io.reactivex.Flowable;

public class ScheduleListViewModel extends ViewModel {

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public Flowable<ScheduleInfo> getScheduleList(int type) {

        return null;
    }
}
