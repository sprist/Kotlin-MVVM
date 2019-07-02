package com.sprist.lib_http.interceptor

import com.sprist.lib_http.HttpContants.IS_DEBUG
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor


/**
 *
 * DESC：配置日志策略
 * Created by liyuhang on 2019/7/2
 *
 */
object LoggingInterceptor {

    fun intercept(): Interceptor {
        val mLoggingInterceptor = HttpLoggingInterceptor()
        if (IS_DEBUG) {
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return mLoggingInterceptor
    }
}