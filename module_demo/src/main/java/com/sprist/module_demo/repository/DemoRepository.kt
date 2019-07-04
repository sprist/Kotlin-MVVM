package com.sprist.module_demo.repository

import androidx.lifecycle.MutableLiveData
import com.sprist.lib_base.net.Response


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/4
 *
 */
interface DemoRepository {


    fun getCommonList1(liveData: MutableLiveData<Response<ArrayList<String>>>)
}