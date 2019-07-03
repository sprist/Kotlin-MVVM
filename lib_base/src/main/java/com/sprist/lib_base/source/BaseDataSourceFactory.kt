package com.sprist.lib_base.source


import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


/**
 *
 * DESC：Paging数据工厂
 * Created by liyuhang on 2019/7/3
 *
 */

abstract class BaseDataSourceFactory<T> : DataSource.Factory<Int, T>() {
    val sourceLiveData = MutableLiveData<BaseDataSource<T>>()

}