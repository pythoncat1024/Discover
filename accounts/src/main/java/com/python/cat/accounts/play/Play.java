package com.python.cat.accounts.play;

import java.util.List;

public class Play {

    public boolean error;
    public List<ResultsBean> results;

    public static class ResultsBean {
        /**
         * _id : 5b6bad449d21226f45755582
         * createdAt : 2018-08-09T10:56:04.962Z
         * desc : 2018-08-09
         * publishedAt : 2018-08-09T00:00:00.0Z
         * source : web
         * type : 福利
         * url : https://ww1.sinaimg.cn/large/0065oQSqgy1fu39hosiwoj30j60qyq96.jpg
         * used : true
         * who : lijinshanmx
         */

        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }
}
