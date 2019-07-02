package com.sprist.lib_http

import android.app.Application
import com.sprist.lib_http.filter.CustomResponseFilter
import com.sprist.lib_http.interceptor.CacheInterceptor
import com.sprist.lib_http.interceptor.HeaderInterceptor
import com.sprist.lib_http.interceptor.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 *
 * DESC：HttpClient管理类
 * Created by liyuhang on 2019/7/2
 *
 */
object HttpClientManager {

    private val mRetrofits = HashMap<String, Retrofit>()//Retrofit管理


    /**
     * 初始化参数
     */
    fun build(application: Application, url: String = "", isDebug: Boolean = true): HttpClientManager {
        HttpContants.mApplication = application
        HttpContants.mBaseUrl = url
        HttpContants.IS_DEBUG = isDebug
        return this
    }

    /**
     * 获取Retrofit实例
     */
    fun getRetrofit(url: String = HttpContants.mBaseUrl): Retrofit {
        return if (mRetrofits[url] != null) {
            mRetrofits[url]!!
        } else {
            val retrofit = createRetrofit(url)
            mRetrofits[url] != retrofit
            retrofit
        }
    }

    /**
     * 配置错误自定义拦截器
     */
    fun setCustomErrorFilter(customResponseFilter: CustomResponseFilter): HttpClientManager {
        HttpContants.mCustomResponseFilter = customResponseFilter
        return this
    }

    /**
     * 创建Retrofit实例
     */
    private fun createRetrofit(url: String): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(CacheInterceptor.intercept())
            .addInterceptor(LoggingInterceptor.intercept())
            .addInterceptor(HeaderInterceptor.intercept())
            .connectTimeout(10, TimeUnit.SECONDS)  //设置超时时间
            .writeTimeout(60, TimeUnit.SECONDS)     //设置写入时间
            .readTimeout(60, TimeUnit.SECONDS)     //设置读取时间
            .build()
        return Retrofit.Builder()
            .baseUrl(url)  //自己配置
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}