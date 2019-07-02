package com.sprist.lib_http.filter

/**
 *
 * DESC：自定义拦截器
 * Created by liyuhang on  2019/7/2
 *
 */

interface CustomResponseFilter {


    fun onResponseFilter(code: String, msg: String): Boolean

}