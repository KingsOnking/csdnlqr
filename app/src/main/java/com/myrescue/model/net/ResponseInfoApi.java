package com.myrescue.model.net;


import com.google.gson.JsonObject;
import com.myrescue.model.Bean.CarIndexInfo;
import com.myrescue.model.Bean.FindPasswordInfo;
import com.myrescue.model.Bean.GoWord;
import com.myrescue.model.Bean.LoginInfo;
import com.myrescue.model.Bean.RegisterInfo;
import com.myrescue.model.Bean.SendNumberInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by HASEE on 2017/2/17.
 */
public interface ResponseInfoApi {
    //请求方式  post
    //请求路径  url
    //请求参数  key = value
    //请求结果
    /**
     *注册
     */
    @FormUrlEncoded
    @POST("DriverUser/register")
    Call<RegisterInfo> getregister(@Field("account") String account, @Field("password") String password, @Field("code") int code, @Field("user_name") String user_name, @Field("user_type") String user_type, @Field("number") String number, @Field("idcard_1") String idcard_1, @Field("idcard_2") String idcard_2,
                                   @Field("license_1") String license_1, @Field("license_2") String license_2);
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("DriverUser/login")
    Call<LoginInfo> getlogging(@Field("account") int account, @Field("password") String password);
    /**
     * 忘记密码
     */
    @FormUrlEncoded
     @POST("Login/findPassword")
     Call<FindPasswordInfo> getfindpssword(@Field("account") int account, @Field("code") int code, @Field("password") String password);
    /**
     * 上下班状态
     * DriverUser/goWork
     */
    @FormUrlEncoded
    @POST("DriverUser/goWork")
    Call<GoWord> getgoWork(@Field("token") String token, @Field("state") int state);
    /**
     *  地址：Car/index
     */
    @FormUrlEncoded
    @POST
    Call<CarIndexInfo> index(@Field("token") String token);

    /*
    司机注册验证码
    account
     */
    @FormUrlEncoded
    @POST("DriverUser/sendNumber")
    Call<SendNumberInfo> getsend(@Field("account") int account);
    /**
     * SOS抢单(司机端)
     */
    @FormUrlEncoded
    @POST("Order/actionOrder")
    Call<JsonObject> actionOrder(@Field("token") String token, @Field("order_id") long order_id, @Field("car_id") long car_id);

    /**
     * 到达接车地点
     */
    @FormUrlEncoded
    @POST("order/actionEnd")
    Call<JsonObject> actionEnd(@Field("token") String token, @Field("order_id") int order_id);
    /**
     * 订单开始
     */
    @FormUrlEncoded
    @POST("order/actionStart")
    Call<JsonObject> actionStart(@Field("token") String token, @Field("order_id") int order_id);

    /**
     * 到达目的地
     */
    @FormUrlEncoded
    @POST("order/orderEnd")
    Call<JsonObject> getCode(@Field("token") String token, @Field("order_id") String order_id);

    /**
     *修改密码
     */
    @FormUrlEncoded
    @POST("mobile")
    Call<JsonObject> resetPassword(@Field("userName") String userName, @Field("newPwd") String newPwd);

}
