package com.sprist.lib_http.interceptor

import okhttp3.Interceptor
import okhttp3.Request


/**
 *
 * DESC：配置公共参数策略
 * Created by liyuhang on 2019/7/2
 *
 */

object QueryParameterInterceptor {

    fun intercept(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request: Request
            val modifiedUrl = originalRequest.url().newBuilder()
//                .addQueryParameter("name","value")
                .build()
            request = originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)

        }

    }

}