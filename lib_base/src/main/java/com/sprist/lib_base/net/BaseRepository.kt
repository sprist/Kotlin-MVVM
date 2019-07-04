package com.sprist.lib_base.net

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sprist.lib_base.source.BaseDataSourceFactory
import com.sprist.lib_http.bean.BaseResponse
import com.sprist.lib_http.infe.OnApiResultListener
import java.util.concurrent.Executor
import java.util.concurrent.Executors


/**
 *
 * DESC：Repository 基类
 * Created by liyuhang on 2019/7/3
 *
 */

open class BaseRepository {


    private val PAGE_SIZE = 10

    private fun getPagedListConfig(pageSize: Int): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(pageSize)    //每页显示的词条数
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(pageSize) //首次加载的数据量
            .setPrefetchDistance(pageSize / 2)     //距离底部还有多少条数据时开始预加载
            .build()
    }


    fun <T> dataConvertResponse(
        sourceFactory: BaseDataSourceFactory<T>,
        pageSize: Int = PAGE_SIZE
    ): MediatorLiveData<Response<PagedList<T>>> {
        val livePagedList = LivePagedListBuilder(sourceFactory, getPagedListConfig(pageSize)).build()
        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        val response = MediatorLiveData<Response<PagedList<T>>>()
        response.addSource(livePagedList) {
            if (refreshState.hasObservers()) {
                response.removeSource(refreshState)
            }
            response.addSource(refreshState) { status ->
                val baseResponse = Response(status, it)
                baseResponse.refresh = { sourceFactory.sourceLiveData.value?.invalidate() }
                baseResponse.retry = { sourceFactory.sourceLiveData.value?.retryAllFailed() }
                response.value = baseResponse
            }
        }
        return response
    }


    fun <T> dataConvertResponse(unit: () -> Unit, liveData: MutableLiveData<Response<T>>) {
        val response = Response.loading<T>()
        response.refresh = unit
        response.retry = unit
        liveData.value=response
        unit()
    }


    fun <T> resultCallBack(liveData: MutableLiveData<Response<T>>): OnApiResultListener<T> {
        return object : OnApiResultListener<T> {
            override fun onError(error: BaseResponse<T>) {
                val response = Response.error(error.mCode, error.mMessage, error.mData)
                liveData.value?.let {
                    response.retry = it.retry
                    response.refresh = it.refresh
                }
                liveData.postValue(response)
            }

            override fun onFilter(filter: BaseResponse<T>) {
                val response = Response.filter(filter.mCode, filter.mMessage, filter.mData)
                liveData.value?.let {
                    response.retry = it.retry
                    response.refresh = it.refresh
                }
                liveData.postValue(response)
            }

            override fun onSuccess(result: BaseResponse<T>) {
                val response = Response.success(result.mData)
                liveData.value?.let {
                    response.retry = it.retry
                    response.refresh = it.refresh
                }
                liveData.postValue(response)
            }
        }

    }


}