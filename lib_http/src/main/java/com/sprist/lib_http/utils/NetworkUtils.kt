package com.sprist.lib_http.utils

import android.content.Context
import android.net.ConnectivityManager
import com.sprist.lib_http.HttpContants

/**
 * Des :网络工具类
 * Created by liyuhang on 2019/7/2
 */

object NetworkUtils {

    /**
     * 判断网络是否可用
     */
    fun isNetworkAvailable(): Boolean {
        val manager = HttpContants.mApplication?.getSystemService(Context.CONNECTIVITY_SERVICE)
        manager?.let {
            return ((it as ConnectivityManager).activeNetworkInfo != null)
        }
        return false
    }

}