package com.sprist.lib_http.interceptor

import com.sprist.lib_http.utils.DataStoreUtils
import okhttp3.Interceptor


/**
 *
 * DESC：配置头部策略
 * Created by liyuhang on 2019/7/2
 *
 */

object HeaderInterceptor {


    fun intercept(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
            requestBuilder.addHeader("Content-Type", "application/json")
            val token = DataStoreUtils.getUserToken()
            if (!token.isNullOrBlank()) {
                requestBuilder.addHeader("X-PH-TOKEN", token)
            }
            val request = requestBuilder.build()
            val response = chain.proceed(request)
//            if (response.header("refresh-token").equals("1")) {
//                token = response.header("token")
//                DataStoreUtils.saveUserToken(context,token)
//            }
            response
        }

    }
}