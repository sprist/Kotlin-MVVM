package com.sprist.module_demo.paging

import androidx.paging.DataSource
import com.sprist.lib_base.source.BaseDataSource
import com.sprist.lib_base.source.BaseDataSourceFactory


/**
 *
 * DESC：Paging数据工厂
 * Created by liyuhang on 2019/4/10
 *
 */

class PagingSourceFactory<T>(private val dataSource: BaseDataSource<T>) :
    BaseDataSourceFactory<T>() {
    override fun create(): DataSource<Int, T> {
        val dateSource = dataSource
        sourceLiveData.postValue(dateSource)
        return dateSource
    }
}