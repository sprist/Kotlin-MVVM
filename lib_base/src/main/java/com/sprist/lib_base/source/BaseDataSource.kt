package com.sprist.lib_base.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.sprist.lib_base.net.NetContants
import com.sprist.lib_base.net.NetState
import com.sprist.lib_http.bean.BaseResponse
import com.sprist.lib_http.infe.OnApiResultListener
import java.util.ArrayList
import java.util.concurrent.Executor


/**
 *
 * DESC：Paging数据获取封装的基类
 * Created by liyuhang on 2019/7/3
 *
 */

open class BaseDataSource<T>(private val retryExecutor: Executor = NetContants.getNetworkExecutor()) :
    PageKeyedDataSource<Int, T>() {


    var pageIndex = 1

    //重新调用
    var retry: (() -> Any)? = null


    val networkState = MutableLiveData<NetState>()

    val initialLoad = MutableLiveData<NetState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
        pageIndex = 1
        networkState.postValue(NetState.LOADING)
        initialLoad.postValue(NetState.LOADING)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        pageIndex = params.key
        networkState.postValue(NetState.LOADING)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {

    }

    fun onloadInitialSuccess(list: MutableList<T>, callback: LoadInitialCallback<Int, T>) {
        callback.onResult(list, pageIndex, pageIndex + 1)
        networkState.postValue(NetState.SUCCESS)
        initialLoad.postValue(NetState.SUCCESS)
    }

    fun onloadInitialError(
        params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>,
        netState: NetState
    ) {
        retry = {
            loadInitial(params, callback)
        }
        networkState.postValue(netState)
        initialLoad.postValue(netState)
    }

    fun onloadAfterSuccess(list: MutableList<T>, callback: LoadCallback<Int, T>) {
        callback.onResult(list, pageIndex + 1)
        networkState.postValue(NetState.SUCCESS)
    }

    fun onloadAfterError(params: LoadParams<Int>, callback: LoadCallback<Int, T>, netState: NetState) {
        retry = {
            loadAfter(params, callback)
        }
        networkState.postValue(netState)
    }

    fun getApiResultInitialListener(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ): OnApiResultListener<ArrayList<T>> {
        return object : OnApiResultListener<ArrayList<T>> {
            override fun onError(error: BaseResponse<ArrayList<T>>) {
                onloadInitialError(params, callback, NetState.ERROR(error.mCode, error.mMessage))
            }

            override fun onFilter(filter: BaseResponse<ArrayList<T>>) {
                onloadInitialError(params, callback, NetState.FILTER(filter.mCode, filter.mMessage))
            }

            override fun onSuccess(result: BaseResponse<ArrayList<T>>) {
                val list = ArrayList<T>()
                onloadInitialSuccess(result.mData ?: list, callback)
            }
        }
    }

    fun getApiResultAfterListener(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, T>
    ): OnApiResultListener<ArrayList<T>> {
        return object : OnApiResultListener<ArrayList<T>> {
            override fun onError(error: BaseResponse<ArrayList<T>>) {
                onloadAfterError(params, callback, NetState.ERROR(error.mCode, error.mMessage))
            }

            override fun onFilter(filter: BaseResponse<ArrayList<T>>) {
                onloadAfterError(params, callback, NetState.FILTER(filter.mCode, filter.mMessage))
            }

            override fun onSuccess(result: BaseResponse<ArrayList<T>>) {
                val list = ArrayList<T>()
                onloadAfterSuccess(result.mData ?: list, callback)
            }
        }
    }

}