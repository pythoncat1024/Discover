package com.python.cat.commonlib.net.domain;

import java.util.List;

/**
 * a java bean class
 *
 * @see com.python.cat.commonlib.net.http.WanService#login(String, String)
 */
@SuppressWarnings("JavadocReference")
public class LoginResult {


    /**
     * data : {"chapterTops":[],"collectIds":[2864,3016,7418],"email":"","icon":"","id":6649,"password":"","token":"","type":0,"username":"pythoncat"}
     * errorCode : 0
     * errorMsg :
     */

    public DataBean data;
    public int errorCode; // --> 0 ok
    public String errorMsg;

    public static class DataBean {
        /**
         * chapterTops : []
         * collectIds : [2864,3016,7418]
         * email :
         * icon :
         * id : 6649
         * password :
         * token :
         * type : 0
         * username : pythoncat
         */

        public String email;
        public String icon;
        public int id;
        public String password;
        public String token;
        public int type;
        public String username;
        public List<?> chapterTops;
        public List<Integer> collectIds;
    }
}
