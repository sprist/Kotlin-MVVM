package com.sprist.lib_http.interceptor

import com.sprist.lib_http.utils.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor


/**
 *
 * DESC：设置缓存策略
 * Created by liyuhang on 2019/7/2
 *
 */
object CacheInterceptor {

    fun intercept(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            //没有网络的情况下仅使用缓存
            if (!NetworkUtils.isNetworkAvailable()) {
                request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
            }
            val response = chain.proceed(request)

            if (NetworkUtils.isNetworkAvailable()) {
                //有网络的时候 不读取缓存 只对get有用，post无效
                val maxAge = 0
                response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .removeHeader("Retrofit")//清除头信息
                    .build()

            } else {
                //无网络的时候，设置超时为1周，只对get有用，post无效
                val maxSize = 60 * 60 * 24 * 7
                response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxSize")
                    .removeHeader("nyn")
                    .build()
            }
            response
        }

    }
}