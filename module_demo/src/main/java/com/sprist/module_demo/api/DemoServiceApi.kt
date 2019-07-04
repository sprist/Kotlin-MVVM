package com.sprist.module_demo.api

import com.sprist.lib_http.bean.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/4
 *
 */
interface DemoServiceApi {


    @GET("trades")
    fun getItems(): Observable<BaseResponse<ArrayList<String>>>


}