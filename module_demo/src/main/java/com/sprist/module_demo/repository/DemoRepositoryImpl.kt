package com.sprist.module_demo.repository

import androidx.lifecycle.MutableLiveData
import com.sprist.lib_base.net.BaseRepository
import com.sprist.lib_base.net.Response
import com.sprist.module_demo.remote.DemoRemoteImpl


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/4
 *
 */

class DemoRepositoryImpl : DemoRepository, BaseRepository() {


    private val mDemoRemoteImpl by lazy { DemoRemoteImpl() }


    override fun getCommonList1(liveData: MutableLiveData<Response<ArrayList<String>>>) {
        dataConvertResponse({ mDemoRemoteImpl.getCommonList(resultCallBack(liveData)) }, liveData)
    }
}