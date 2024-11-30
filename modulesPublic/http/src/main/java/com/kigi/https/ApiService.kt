package com.kigi.https

import com.kigi.https.pojo.ImageVerifyBean
import com.kigi.https.pojo.UserBean
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * todo 接口是否要拆分到模块中？
 */
interface ApiService {

    /**
     * 退出登录
     */
    @GET("user/logout/json")
    suspend fun logout(): Response<Any>

    /**
     * 退出登录
     */
    @GET("user/logout/json")
    suspend fun getMsgCode(@Query("pid") pid:Long,@Query("shop_id") shopId:Long): Response<Int>

    /**
     * 获取图片验证码
     */
    @GET("/api/v1/comm/threshold-captcha")
    suspend fun getImageCode():Response<ImageVerifyBean>

    //发送短信验证码 api/v1/comm/send-sms-captcha
    @POST("/api/v1/comm/send-sms-captcha")
    suspend fun sendSmsCode(@HeaderMap headers:Map<String,String>,@Body params: Map<String, String>):Response<Any>

    ///api/v1/comm/user/login
    //登录
    @POST("/api/v1/comm/user/login")
    suspend fun login(@Body params: Map<String, String>):Response<UserBean>
}