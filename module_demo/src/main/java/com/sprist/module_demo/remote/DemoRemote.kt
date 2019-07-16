package com.sprist.module_demo.remote

import com.sprist.lib_http.infe.OnApiResultListener
import java.util.ArrayList


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/4
 *
 */

interface DemoRemote {

    fun getCommonList(resultCallBack: OnApiResultListener<ArrayList<String>>)

    fun getPagingList(pageSize: Int, pageNumber: Int, resultCallBack: OnApiResultListener<ArrayList<String>>)


}