package com.sprist.module_demo.remote

import com.sprist.lib_http.HttpClientManager
import com.sprist.lib_http.bean.BaseResponse
import com.sprist.lib_http.infe.OnApiResultListener
import com.sprist.lib_http.infe.RemoteHelper
import com.sprist.module_demo.api.DemoServiceApi
import java.util.ArrayList


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/4
 *
 */

class DemoRemoteImpl : DemoRemote, RemoteHelper() {

    private val mReto by lazy { HttpClientManager.getRetrofit() }
    //可以自定义远程地址
    private val mReto2 by lazy { HttpClientManager.getRetrofit("https://192.168.0.1:8080/") }
    private val mServiceApi by lazy { mReto.create(DemoServiceApi::class.java) }


    override fun getCommonList(resultCallBack: OnApiResultListener<ArrayList<String>>) {
//        observe(mServiceApi.getItems(), resultCallBack)
        mockData(resultCallBack)


    }

    private fun mockData(resultCallBack: OnApiResultListener<ArrayList<String>>) {
        val response = BaseResponse<ArrayList<String>>()
        response.mCode = "200"
        val data = ArrayList<String>()
        data.add("测试1")
        data.add("测试2")
        data.add("测试3")
        data.add("测试4")
        data.add("测试5")
        data.add("测试6")
        data.add("测试7")
        data.add("测试8")
        data.add("测试9")
        data.add("测试10")
        data.add("测试11")
        data.add("测试12")
        response.mData = data
        resultCallBack.onSuccess(response)
    }


}