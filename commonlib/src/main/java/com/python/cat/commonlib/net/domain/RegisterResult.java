package com.python.cat.commonlib.net.domain;

import java.util.List;

/**
 * a java bean class
 *
 * @see com.python.cat.commonlib.net.http.WanService#register(String, String, String)
 */
@SuppressWarnings("JavadocReference")
public class RegisterResult {


    /**
     * data : {"chapterTops":[],"collectIds":[],"email":"","icon":"","id":22381,"password":"","token":"","type":0,"username":"tom123"}
     * errorCode : 0
     * errorMsg :
     */

    public DataBean data;
    public int errorCode;
    public String errorMsg;

    public static class DataBean {
        /**
         * chapterTops : []
         * collectIds : []
         * email :
         * icon :
         * id : 22381
         * password :
         * token :
         * type : 0
         * username : tom123
         */

        public String email;
        public String icon;
        public int id;
        public String password;
        public String token;
        public int type;
        public String username;
        public List<?> chapterTops;
        public List<?> collectIds;
    }
}
