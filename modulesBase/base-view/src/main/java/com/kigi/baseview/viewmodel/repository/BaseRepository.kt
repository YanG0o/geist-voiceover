package com.kigi.baseview.viewmodel.repository

import com.kigi.baseview.exception.AppException
import com.kigi.https.Response

/**
 * @Author: HeYan
 * @CreateTime： 2023/1/8 21:53
 * @Describe： （BaseRepository,可封装接口公共业务处理）
 */
open class BaseRepository {

    suspend fun  <T> apiCall(retryWhenTimeError:Boolean = true, func:suspend ()-> Response<T>)  : T {
        //确保服务器时间
        sureDiffTime()
        val result = func.invoke()
        //11000 代表上传接口继续上传
        return when(result.code){
            200-> result.data
            //同步服务器时间等
            11108,11120->{
                sureDiffTime(true)
                if(retryWhenTimeError){
                    return apiCall(false,func)
                }
                throw AppException(result.code,result.message)

            }
            else -> throw AppException(result.code,result.message)
        }
    }
    /*suspend fun  <T> apiCallPageList(retryWhenTimeError:Boolean = true, func:suspend ()->PageListResponse<T>)  : T {
        //确保服务器时间
        sureDiffTime()
        val result = func.invoke()
        //11000 代表上传接口继续上传
        return when(result.code){
            200-> result.data
            //同步服务器时间等
            11108,11120->{
                sureDiffTime(true)
                if(retryWhenTimeError){
                    return apiCallPageList(false,func)
                }
                throw AppException(result.code,result.message)

            }
            else -> throw AppException(result.code,result.message)
        }
    }*/

    /**
     * 确保服务器时间
     */
    private suspend fun sureDiffTime(hardUpdate:Boolean = false) {

    }

    /**
     * 更新服务器时间
     */
    suspend fun updateDiffTime(){

    }

}