package com.sprist.lib_base.net

/**
 *
 * DESC：请求状态
 * Created by liyuhang on 2019/7/3
 *
 */


data class NetState private constructor(val status: Status, val code: String? = null, val msg: String? = null) {
    companion object {
        val SUCCESS = NetState(Status.SUCCESS, "200", "成功")
        val LOADING = NetState(Status.LOADING, code = "0", msg = "加载中...")
        fun ERROR(code: String?, msg: String?) = NetState(Status.ERROR, code, msg)
        fun FILTER(code: String?, msg: String?) = NetState(Status.FILTER, code, msg)
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR,
        FILTER
    }
}