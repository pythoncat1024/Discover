package com.pycat.schedule.todo;

import android.content.Context;

import com.python.cat.commonlib.net.domain.ScheduleInfo;
import com.python.cat.commonlib.net.http.WanRequest;

import io.reactivex.Flowable;

/**
 * 目前先直接网络获取数据，到时候做一个本地数据缓存，数据库
 */
public class ScheduleEngine {

    private ScheduleEngine() {
    }


    public static Flowable<ScheduleInfo> getScheduleList(Context context, int type) {
        // todo: 到时候要根据需要先获取本地数据
        return WanRequest.getInstance().getScheduleList(context, type);
    }
}
