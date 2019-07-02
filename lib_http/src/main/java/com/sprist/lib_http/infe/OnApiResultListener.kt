package com.sprist.lib_http.infe

import com.sprist.lib_http.bean.BaseResponse

/**
 *
 * DESC：网络请求回调
 * Created by liyuhang on 2019/7/2
 *
 */
interface OnApiResultListener<T> {
    /**
     * 请求成功的情况
     *
     * @param result 需要解析的解析类
     */
    fun onSuccess(result: BaseResponse<T>)

    /**
     * 响应成功，但是出错的情况
     *
     * @param error    需要解析的解析类
     */
    fun onError(error: BaseResponse<T>)


    /**
     * 返回结果被过滤处理
     *
     * @param message 需要解析的解析类
     */
    fun onFilter(filter: BaseResponse<T>)
}
