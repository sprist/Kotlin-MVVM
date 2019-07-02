package com.sprist.lib_http

import android.app.Application
import com.sprist.lib_http.filter.CustomResponseFilter


/**
 *
 * DESC：变量参数
 * Created by liyuhang on 2019/7/2
 *
 */
object HttpContants {


    var SUCCESS_REQUEST_CODE = "200"
    var IS_DEBUG = false


    var mApplication: Application? = null
    var mBaseUrl: String = ""
    var mCustomResponseFilter: CustomResponseFilter? = null


}