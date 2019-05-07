package com.python.cat.commonlib.net.domain;

import java.util.List;

public class ScheduleInfo {


    /**
     * data : {"doneList":[{"date":1544889600000,"todoList":[{"completeDate":1544889600000,"completeDateStr":"2018-12-16","content":"","date":1544889600000,"dateStr":"2018-12-16","id":5119,"priority":0,"status":1,"title":"生活是什么","type":3,"userId":6649}]}],"todoList":[],"type":3}
     * errorCode : 0
     * errorMsg :
     */

    public DataBean data;
    public int errorCode;
    public String errorMsg;

    public static class DataBean {
        /**
         * doneList : [{"date":1544889600000,"todoList":[{"completeDate":1544889600000,"completeDateStr":"2018-12-16","content":"","date":1544889600000,"dateStr":"2018-12-16","id":5119,"priority":0,"status":1,"title":"生活是什么","type":3,"userId":6649}]}]
         * todoList : []
         * type : 3
         */

        public int type;
        public List<ScheduleListBean> doneList;
        public List<ScheduleListBean> todoList;
    }
}
