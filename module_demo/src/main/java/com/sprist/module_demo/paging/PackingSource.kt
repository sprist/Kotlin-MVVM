package com.sprist.module_demo.paging

import com.sprist.lib_base.source.BaseDataSource
import com.sprist.module_demo.remote.DemoRemoteImpl

/**
 *
 * DESC：
 * Created by liyuhang on 2019/4/10
 *
 */

class PackingSource :
    BaseDataSource<String>() {
    private val mCircleRemoteImpl by lazy { DemoRemoteImpl() }
    private var pageSize = 10


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, String>) {
        super.loadInitial(params, callback)
        mCircleRemoteImpl.getPagingList(pageSize, pageIndex, getApiResultInitialListener(params, callback))


    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
        super.loadAfter(params, callback)
        mCircleRemoteImpl.getPagingList(pageSize, pageIndex, getApiResultAfterListener(params, callback))
    }


}