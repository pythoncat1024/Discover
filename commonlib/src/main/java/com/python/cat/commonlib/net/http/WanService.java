package com.python.cat.commonlib.net.http;

import com.python.cat.commonlib.net.domain.LoginResult;
import com.python.cat.commonlib.net.domain.LogoutResult;
import com.python.cat.commonlib.net.domain.RegisterResult;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * from https://www.wanandroid.com
 */
public interface WanService {

    String BASE_URL = "https://www.wanandroid.com/";

    /**
     * username，password
     */
    @FormUrlEncoded
    @POST("user/login")
    Flowable<LoginResult> login(@Field("username") String username,
                                @Field("password") String password);

    /**
     * username,password,repassword
     */
    @FormUrlEncoded
    @POST("user/login")
    Flowable<RegisterResult> register(@Field("username") String username,
                                      @Field("password") String password,
                                      @Field("repassword") String rePassword);

    @GET("user/logout/json")
    Flowable<LogoutResult> logout();

    /**
     * 新增一条Todo
     * https://www.wanandroid.com/lg/todo/add/json
     * <p>
     * 方法：POST
     * 参数：
     * title: 新增标题
     * content: 新增详情
     * date: 2018-08-01
     * type: 0 --> 0/1/2/3
     */
    @FormUrlEncoded
    @POST("lg/todo/add/json")
    Flowable<Object> addTodo(@Field("title") String title,
                             @Field("content") String content,
                             @Field("date") String date,
                             @Field("type") int type);

    /**
     * id: 拼接在链接上，为唯一标识
     * title: 更新标题
     * content: 新增详情
     * date: 2018-08-01
     * status: 0 // 0为未完成，1为完成
     * type: 0
     */
    @FormUrlEncoded
    @POST("lg/todo/update/{id}/json")
    Flowable<Object> updateTodo(@Path("id") int id,
                                @Field("title") String title,
                                @Field("content") String content,
                                @Field("date") String date,
                                @Field("status") int status,
                                @Field("type") int type);

    /**
     * id: 拼接在链接上，为唯一标识
     */
    @FormUrlEncoded
    @POST("lg/todo/delete/{id}/json")
    Flowable<Object> deleteTodo(@Path("id") int id);

    /**
     * https://www.wanandroid.com/lg/todo/listnotdo/0/json/1
     * <p>
     * https://www.wanandroid.com/lg/todo/listnotdo/类型/json/页码
     * <p>
     * 方法：POST
     * 参数：
     * 类型：类型拼接在链接上，目前支持0,1,2,3
     * 页码: 拼接在链接上，从1开始；
     */
    @FormUrlEncoded
    @POST("lg/todo/listnotdo/{type}/json/{page}")
    Flowable<Object> todoListUndo(
            @Path("type") int type, @Path("page") int page);

    /**
     * https://www.wanandroid.com/lg/todo/listdone/0/json/1
     * <p>
     * https://www.wanandroid.com/lg/todo/listdone/类型/json/页码
     * <p>
     * 方法：POST
     * 参数：
     * 类型：类型拼接在链接上，目前支持0,1,2,3
     * 页码: 拼接在链接上，从1开始；
     */
    @FormUrlEncoded
    @POST("lg/todo/listdone/{type}/json/{page}")
    Flowable<Object> todoListDone(
            @Path("type") int type, @Path("page") int page);

    @GET("lg/todo/list/{type}/json")
    Flowable<Object> todoListDone(@Path("type") int type);
}
