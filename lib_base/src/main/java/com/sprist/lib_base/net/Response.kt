package com.sprist.lib_base.net

/**
 * Des :统一处理数据状态
 * Created by liyuhang on 2019/7/3
 */

data class Response<T>(var state: NetState, var data: T?) {


    companion object {
        fun <T> success(data: T?): Response<T> {
            return Response(NetState.SUCCESS, data)
        }

        fun <T> error(code: String?, msg: String, data: T?): Response<T> {
            return Response(NetState.ERROR(code, msg), data)
        }

        fun <T> loading( ): Response<T> {
            return Response(NetState.LOADING, null)
        }

        fun <T> filter(code: String?, msg: String?, data: T?): Response<T> {
            return Response(NetState.FILTER(code, msg), data)
        }
    }

    //刷新
    lateinit var refresh: () -> Unit

    //重新请求
    lateinit var retry: () -> Unit


}