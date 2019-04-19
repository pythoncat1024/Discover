package com.python.cat.splash;

import java.util.List;

public class Splash {


    /**
     * error : false
     * results : [{"_id":"5b6bad449d21226f45755582","createdAt":"2018-08-09T10:56:04.962Z","desc":"2018-08-09","publishedAt":"2018-08-09T00:00:00.0Z","source":"web","type":"福利","url":"https://ww1.sinaimg.cn/large/0065oQSqgy1fu39hosiwoj30j60qyq96.jpg","used":true,"who":"lijinshanmx"},{"_id":"56cc6d29421aa95caa708118","createdAt":"2016-01-07T01:39:07.601Z","desc":"1.7","publishedAt":"2016-01-07T03:36:25.265Z","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1ezqon28qrzj20h80pamze.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d23421aa95caa707980","createdAt":"2015-11-10T01:45:59.392Z","desc":"11.10","publishedAt":"2015-11-10T05:37:51.792Z","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1exvmxmy36wj20ru114gqq.jpg","used":true,"who":"张涵宇"}]
     */

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
