package com.sprist.lib_http.bean

import com.sprist.lib_http.HttpContants


/**
 *
 * Des :后台反馈数据基类
 * Created by liyuhang on 2019/7/2
 *
 */
open class BaseResponse<T> {

    var mCode: String = ""
    var mMessage: String = ""
    var mData: T? = null

    fun isSuccess(): Boolean {
        return HttpContants.SUCCESS_REQUEST_CODE == mCode
    }

}